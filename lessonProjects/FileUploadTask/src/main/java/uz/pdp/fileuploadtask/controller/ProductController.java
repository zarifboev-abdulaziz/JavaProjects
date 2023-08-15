package uz.pdp.fileuploadtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.fileuploadtask.model.Attachment;
import uz.pdp.fileuploadtask.model.AttachmentContent;
import uz.pdp.fileuploadtask.model.Product;
import uz.pdp.fileuploadtask.repository.AttachmentContentRepository;
import uz.pdp.fileuploadtask.repository.AttachmentRepository;
import uz.pdp.fileuploadtask.repository.ProductRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    String absolutePAth = "C:\\Users\\User\\Desktop\\uploadedPhotos";


    @GetMapping("/add")
    public String getAddForm() {
        return "addProductForm";
    }

    @PostMapping("/add")
    public String saveProduct(@ModelAttribute Product product) throws IOException {
        MultipartFile file = product.getMultipartFile();

        Attachment attachment = new Attachment(file.getOriginalFilename(), file.getSize(), file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent(file.getBytes(), savedAttachment);
        attachmentContentRepository.save(attachmentContent);

        product.setAttachment(savedAttachment);
        Product savedProduct = productRepository.save(product);

        file.transferTo(new File(absolutePAth + "/" +  attachment.getName()));

        return "redirect:/product/" + savedProduct.getId();
    }

    @GetMapping("/{productId}")
    public String getAddForm(@PathVariable Integer productId, Model model) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            model.addAttribute("product", product);
            return "infoProduct";
        }


        return "addProductForm";
    }

    @GetMapping("/download/{productId}")
    @ResponseBody
    public String downloadFile(@PathVariable Integer productId, HttpServletResponse response) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            return "";
        }
        Product product = optionalProduct.get();

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(product.getAttachment().getId());
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            response.setHeader("Content-Disposition", "attachment; filename=\""  + attachment.getName() + "\"");
            response.setHeader("Content-Transfer-Encoding", "binary"  + attachment.getName());


            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());

                FileInputStream fileInputStream = new FileInputStream(absolutePAth + "/" + attachment.getName());

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
        }

        return "redirect:/" + product.getId();
    }


}

