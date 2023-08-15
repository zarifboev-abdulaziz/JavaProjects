package uz.pdp.fileuploadanddownload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.fileuploadanddownload.model.Attachment;
import uz.pdp.fileuploadanddownload.model.AttachmentContent;
import uz.pdp.fileuploadanddownload.repository.AttachmentContentRepository;
import uz.pdp.fileuploadanddownload.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    private static final String uploadPath = "yuklanganlar";


    @PostMapping("/uploadDB")
    public String uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        if (file != null){
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = new Attachment(originalFilename, size, contentType);
            Attachment savedAttachment = attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setBytes(file.getBytes());
            attachmentContent.setAttachment(savedAttachment);
            attachmentContentRepository.save(attachmentContent);

            return "Successfully Saved";
        }

        return "Xatolik";
    }

    @PostMapping("/uploadSystem")
    public String uploadFileToSystem(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        if (file != null){
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = new Attachment(originalFilename, size, contentType);

            String[] split = originalFilename.split("\\.");
            String name = UUID.randomUUID().toString()  + "." +  split[split.length - 1];
            attachment.setName(name);

            Attachment savedAttachment = attachmentRepository.save(attachment);

            Path path = Paths.get(uploadPath + "/" + name);
            Files.copy(file.getInputStream(), path);

            return "Successfully Saved, Id = " + savedAttachment.getId();
        }

        return "Xatolik";
    }

    @GetMapping("/getFile/{fileId}")
    public void downloadFile(@PathVariable Integer fileId, HttpServletResponse response) throws IOException {
        Optional<Attachment> attachmentById = attachmentRepository.findById(fileId);
        if (attachmentById.isPresent()) {
            Attachment attachment = attachmentById.get();

            Optional<AttachmentContent> byAttachmentId = attachmentContentRepository.findByAttachmentId(attachment.getId());
            if (byAttachmentId.isPresent()){
                AttachmentContent attachmentContent = byAttachmentId.get();

                response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getFileOriginalName() + "\"");

                response.setContentType(attachment.getContentType());

                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
            }
        }
    }


    @GetMapping("/getFileFromSystem/{fileId}")
    public void getFileFromSystem(@PathVariable Integer fileId, HttpServletResponse response) throws IOException {
        Optional<Attachment> attachmentById = attachmentRepository.findById(fileId);
        if (attachmentById.isPresent()) {
            Attachment attachment = attachmentById.get();

            response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getFileOriginalName() + "\"");

            response.setContentType(attachment.getContentType());

            FileInputStream fileInputStream = new FileInputStream(uploadPath + "/" + attachment.getName());

            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
        }
    }

}
