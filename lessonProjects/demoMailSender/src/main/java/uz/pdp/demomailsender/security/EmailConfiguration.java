package uz.pdp.demomailsender.security;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
//import freemarker.template.Configuration;
import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import uz.pdp.demomailsender.dto.EmailDto;

@Configuration
public class EmailConfiguration {


//    @Bean
//    public ITemplateResolver thymeleafTemplateResolver() {
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setPrefix("mail-templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML");
//        templateResolver.setCharacterEncoding("UTF-8");
//        return templateResolver;
//    }


    @Bean
    public FreeMarkerConfigurer freemarkerClassLoaderConfig() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_27);
        TemplateLoader templateLoader = new ClassTemplateLoader(this.getClass(), "/mail-templates");
        configuration.setTemplateLoader(templateLoader);
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setConfiguration(configuration);
        return freeMarkerConfigurer;
    }


}
