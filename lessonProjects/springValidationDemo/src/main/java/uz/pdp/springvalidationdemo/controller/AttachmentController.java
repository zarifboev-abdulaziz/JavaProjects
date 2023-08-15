package uz.pdp.springvalidationdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.springvalidationdemo.model.Attachment;
import uz.pdp.springvalidationdemo.model.AttachmentContent;
import uz.pdp.springvalidationdemo.repository.AttachmentContentRepository;
import uz.pdp.springvalidationdemo.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class AttachmentController {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    String absolutePAth = "C:\\Users\\User\\Desktop\\uploadedPhotos";


    @PostMapping("/upload")
    public String upload(@RequestParam("fileName")MultipartFile file) throws IOException {

        try {
            Attachment savedAttachment = attachmentRepository.save(new Attachment(file.getOriginalFilename(), file.getSize(), file.getContentType()));
            attachmentContentRepository.save(new AttachmentContent(file.getBytes(), savedAttachment));
        } catch (Exception e){
            e.printStackTrace();
        }

        String originalFilename = file.getOriginalFilename();

        try {
            file.transferTo(new File(absolutePAth + "/" +  originalFilename));

            return "redirect:/getFiles";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "index";
    }

    @GetMapping("/getFiles")
    public String getFiles(Model model){
        File folder = new File(absolutePAth);
        File[] files = folder.listFiles();

        model.addAttribute("fileList", files);
        return "files";
    }


    @GetMapping("/files/{fileName}")
    @ResponseBody
    public String downloadFile(@PathVariable String fileName, HttpServletResponse response){

        response.setHeader("Content-Disposition", "attachment; filename="  + fileName);
        response.setHeader("Content-Transfer-Encoding", "binary"  + fileName);

        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());

            FileInputStream fileInputStream = new FileInputStream(absolutePAth + "/" + fileName);

            byte[] bytes = new byte[1024];
            int len;


            while ((len = fileInputStream.read(bytes))  >  0){
                bufferedOutputStream.write(bytes, 0, len);

            }

            bufferedOutputStream.close();
            response.flushBuffer();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/getFiles";
    }

}
