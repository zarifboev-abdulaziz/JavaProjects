package uz.pdp.demomailsender;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.pdp.demomailsender.controller.MailControllerToHr;
import uz.pdp.demomailsender.dto.EmailDto;

import javax.mail.MessagingException;
import java.io.IOException;

@SpringBootApplication
public class DemoMailSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMailSenderApplication.class, args);

//        EmailDto emailDto = new EmailDto();
//        emailDto.setTo("abdulaziz2000go@gmail.com");
//        emailDto.setSubject("IT специалист - Abdulaziz Zarifboyev");
//        emailDto.setText("В поисках вакансий, я нашел объявление о работе в Анорбанке на сайте anorbank.uz." +
//                " Я хотел бы присоединиться к ИТ-отделу Anorbank в качестве разработчика Java.");

//        try {
//            mailControllerToHr.sendMessageUsingFreemarkerTemplate(emailDto);
//        } catch (IOException | MessagingException | TemplateException e) {
//            e.printStackTrace();
//        }

    }

}
