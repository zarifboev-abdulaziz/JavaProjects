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
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class AttachmentController {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;



    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    private String uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        if (file != null){
            Attachment attachment = new Attachment();
            attachment.setContentType(file.getContentType());
            attachment.setFileOriginalName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            Attachment savedAttachment = attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setBytes(file.getBytes());
            attachmentContent.setAttachment(savedAttachment);
            attachmentContentRepository.save(attachmentContent);

            return "Successfully Saved";
        }

        return "Failed to save File!";
    }


    @RequestMapping(value = "/info", method = RequestMethod.GET)
    private List<Attachment> getAllAttachments(){
        return attachmentRepository.findAll();
    }

    @RequestMapping(value = "/info/{attachmentId}", method = RequestMethod.GET)
    private Attachment getOneAttachment(@PathVariable Integer attachmentId){
        Optional<Attachment> attachmentById = attachmentRepository.findById(attachmentId);
        return attachmentById.orElse(null);
    }

    @RequestMapping(value = "/download/{fileId}", method = RequestMethod.GET)
    private void downloadFile(@PathVariable Integer fileId, HttpServletResponse response) throws IOException {
        Optional<Attachment> attachmentById = attachmentRepository.findById(fileId);
        if (attachmentById.isPresent()) {
            Attachment attachment = attachmentById.get();
            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
            if (optionalAttachmentContent.isPresent()) {
                AttachmentContent attachmentContent = optionalAttachmentContent.get();
                response.setHeader("Content-Disposition", "attachment; filename\"" + attachment.getFileOriginalName() + "\"");
                response.setContentType(attachment.getContentType());

                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
            }
        }
    }

//    @RequestMapping(value = "/download/{fileId}", method = RequestMethod.GET)
//    private void downloadFile(@PathVariable Integer fileId, HttpServletResponse response) throws IOException {
//        for (Attachment attachmentDto : attachmentRepository.findAll()) {
//            Optional<Attachment> attachmentById = attachmentRepository.findById(attachmentDto.getId());
//            if (attachmentById.isPresent()) {
//                Attachment attachment = attachmentById.get();
//                Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
//                if (optionalAttachmentContent.isPresent()) {
//                    AttachmentContent attachmentContent = optionalAttachmentContent.get();
//                    response.setHeader("Content-Disposition", "attachment; filename\"" + attachment.getFileOriginalName() + "\"");
//                    response.setContentType(attachment.getContentType());
//
//                    FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
//                }
//            }
//        }
//    }


}
