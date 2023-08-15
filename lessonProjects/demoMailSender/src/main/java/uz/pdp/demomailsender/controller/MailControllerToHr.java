package uz.pdp.demomailsender.controller;

import freemarker.template.TemplateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import uz.pdp.demomailsender.dto.EmailDto;


import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hr")
public class MailControllerToHr {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    SpringTemplateEngine thymeleafTemplateEngine;

    //    @Override
    @PostMapping("/send")
    public void sendMessageUsingFreemarkerTemplate(@RequestBody EmailDto emailDto)
            throws IOException, TemplateException, MessagingException {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("plainText", emailDto.getText());

        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        String htmlBody = "";
        try {
             htmlBody = thymeleafTemplateEngine.process("hrtemplate.html", thymeleafContext);
        } catch (Exception e){
            e.printStackTrace();
        }

        sendHtmlMessage(emailDto.getTo(), emailDto.getSubject(), htmlBody);
    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);

        File file = new File("src/main/resources/Abdulaziz's_Resume1.pdf");
        helper.addAttachment("Abdulaziz's Resume", file);

        helper.setText(htmlBody, true);
        mailSender.send(message);
    }


//    @GetMapping("/send")
//    private void sendEmail() throws MessagingException {
//        final String username = "zarifboev.abdulaziz@gmail.com";
//        final String password = "95010uzb";
//
////        final String username = "emailsendermailer@gmail.com";
////        final String password = "AB5447316";
//
//
//        Properties props = new Properties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//        session.getProperties().put("mail.smtp.starttls.enable", true);
//        session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
//
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("no-reply@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("abdulaziz2000go@gmail.com"));
//            message.setSubject("subject test");
//            message.setText("qwertyuiop");
//
//            Transport.send(message);
//            System.out.println("Done");
//
//        } catch (Exception e) {
//        }
//
//    }

    public void sendEmailToUser() {

//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setFrom("testpdp@gmail.com");
//            mailMessage.setTo("abdulaziz2000go@gmail.com");
//            mailMessage.setSubject("Confirmation");
//            mailMessage.setText("some plain text");
//            mailSender.send(mailMessage);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, true);
            helper.setText(buildEmail("Abdulaziz", "http://localhost:8080/api/auth/verifyEmail?emailCode=1234&email=abdulaziz2000go@gmail.com"), true);
            helper.setTo("abdulaziz2000go@gmail.com");
            helper.setSubject("Confirm your email");
            helper.setFrom("hello@amigoscode.com");

            File file = ResourceUtils.getFile("classpath:static/images/HTML.png");
            helper.addAttachment("Invoice", file);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }


}
