package uz.pdp.demomailsender.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

//@Configuration
//public class AppSecurity {
//
//
//
//    @Bean
//    JavaMailSender javaMailSender(){
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//
//        mailSender.setUsername("zarifboev.abdulaziz@gmail.com");
//        mailSender.setPassword("95010uzb");
//
//        Properties properties = mailSender.getJavaMailProperties();
//        properties.put("mail.transport.protocol", "smtp");
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.debug", "true");
//        return mailSender;
//    }
//
//}
