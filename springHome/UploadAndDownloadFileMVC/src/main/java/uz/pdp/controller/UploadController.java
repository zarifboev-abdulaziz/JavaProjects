package uz.pdp.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;

@Controller
public class UploadController {

    @RequestMapping(value = "/savefile", method = RequestMethod.GET)
    public String getForm(Model model) throws IOException {
        return "form";
    }

    @RequestMapping(value="/savefile",method= RequestMethod.POST)
    public String upload(@RequestParam(name = "file") CommonsMultipartFile file, Model model) throws IOException {

        String path= "C:\\Users\\User\\Desktop\\springHome\\UploadAndDownloadFileMVC\\src\\main\\resources";
        String filename=file.getOriginalFilename();

        System.out.println(path+" "+filename);

        try{
            byte barr[]=file.getBytes();

            BufferedOutputStream bout=new BufferedOutputStream(
                    new FileOutputStream(path+"/"+filename));
            bout.write(barr);
            bout.flush();
            bout.close();

        } catch (Exception e){}


        BufferedImage image =  ImageIO.read(new File(path + "/" + filename));

        ByteArrayOutputStream base = new ByteArrayOutputStream();
        ImageIO.write(image,"png",base);
        base.flush();
        byte[] imageInByteArray = base.toByteArray();
        base.close();

        String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);

        model.addAttribute("base64", b64);

        return "image";
    }


}

