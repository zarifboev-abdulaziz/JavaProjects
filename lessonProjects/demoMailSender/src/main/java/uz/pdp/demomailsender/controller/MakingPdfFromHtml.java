package uz.pdp.demomailsender.controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/api/html-to-pdf")
public class MakingPdfFromHtml {
    @Autowired
    SpringTemplateEngine thymeleafTemplateEngine;
    @Autowired
    ServletContext servletContext;

//    @GetMapping
//    private void parseThymeleafTemplate() throws DocumentException, IOException {
//        Context context = new Context();
//        context.setVariable("plainText", "I did it. Html to Pdf done bro");
//
//        String htmlBody = thymeleafTemplateEngine.process("success.html", context);
//        generatePdfFromHtml(htmlBody);
//    }
//
//    public void generatePdfFromHtml(String html) throws IOException, DocumentException {
//        String outputFolder = "src/main/resources/static" + File.separator + "template1.pdf";
//        OutputStream outputStream = new FileOutputStream(outputFolder);
//
//        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocumentFromString(html);
//        renderer.layout();
//        renderer.createPDF(outputStream);
//
//        outputStream.close();
//    }


    //This way is very good
    @GetMapping(path = "/pdf")
    public ResponseEntity<?> getPDF(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        WebContext context = new WebContext(request, response, servletContext);
//        context.setVariable("plainText", "order is fulfillled");

        Context context = new Context();
        context.setVariable("plainText", "I did it. Html to Pdf done bro");
        String orderHtml = thymeleafTemplateEngine.process("hrtemplate", context);

        /* Setup Source and target I/O streams */
        ByteArrayOutputStream target = new ByteArrayOutputStream();

        /*Setup converter properties. */
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");

        /* Call convert method */
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();

        FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/static/template2.pdf");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
        /* Send the response as downloadable PDF */

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

}
