package uz.pdp.fileuploadanddownload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.fileuploadanddownload.model.Attachment;
import uz.pdp.fileuploadanddownload.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AttachmentController {
    @Autowired
    AttachmentRepository attachmentRepository;

    private static final String uploadPath = "DownloadedFiles";



    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    private String uploadSeveralFilesToSystem(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();

        while (fileNames.hasNext()){
            MultipartFile file = request.getFile(fileNames.next());

            if (file != null){
                Attachment attachment = new Attachment();
                attachment.setSize(file.getSize());
                attachment.setContentType(file.getContentType());
                attachment.setFileOriginalName(file.getOriginalFilename());

                String[] split = file.getOriginalFilename().split("\\.");
                String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
                attachment.setName(name);
                attachmentRepository.save(attachment);

                Path path = Paths.get(uploadPath + "/" + name);

                Files.copy(file.getInputStream(), path);
            }
        }

        return "Files successfully saved";
    }


//    @RequestMapping(value = "", method = RequestMethod.POST)
//    private String uploadFileToSystem(MultipartHttpServletRequest request) throws IOException {
//        Iterator<String> fileNames = request.getFileNames();
//        MultipartFile file = request.getFile(fileNames.next());
//
//        if (file != null){
//            Attachment attachment = new Attachment();
//            attachment.setSize(file.getSize());
//            attachment.setContentType(file.getContentType());
//            attachment.setFileOriginalName(file.getOriginalFilename());
//
//            String[] split = file.getOriginalFilename().split("\\.");
//            String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
//            attachment.setName(name);
//            attachmentRepository.save(attachment);
//
//            Path path = Paths.get(uploadPath + "/" + name);
//
//            Files.copy(file.getInputStream(), path);
//
//            return "Successfully Uploaded to system";
//        }
//
//        return "Failed to Upload";
//    }

    @RequestMapping(value = "/attachment", method = RequestMethod.GET)
    public List<Attachment> getAllAttachments(){
        return attachmentRepository.findAll();
    }

    @RequestMapping(value = "/download/{fileId}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable Integer fileId, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(fileId);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();

            response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getFileOriginalName() + "\"");
            response.setContentType(attachment.getContentType());
            FileInputStream fileInputStream = new FileInputStream(uploadPath + "/" + attachment.getName());

            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
        }
    }

}
