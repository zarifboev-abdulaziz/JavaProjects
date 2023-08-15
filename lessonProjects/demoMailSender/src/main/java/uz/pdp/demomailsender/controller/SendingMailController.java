package uz.pdp.demomailsender.controller;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SendingMailController {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private FreeMarkerConfigurer freemarkerConfigurer;

    //    @Override
    @GetMapping("/send")
    public void sendMessageUsingFreemarkerTemplate(
            String to, String subject, Map<String, Object> templateModel)
            throws IOException, TemplateException, MessagingException {
        templateModel.put("senderName", "Nodirbek");
        templateModel.put("recipientName", "Abdulaziz");
        templateModel.put("text", "Some plain text");

        Template freemarkerTemplate = freemarkerConfigurer.getConfiguration()
                .getTemplate("template-freemarker.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);

//        sendHtmlMessage(to, subject, htmlBody);
        sendHtmlMessage("abdulaziz2000go@gmail.com", "Confirmation", htmlBody);
    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);

        File file = new File("src/main/resources/HTML.png");
        helper.addAttachment("file1", file);
        helper.addAttachment("file2", file);

        helper.setText(htmlBody, true);
        mailSender.send(message);
    }


}
