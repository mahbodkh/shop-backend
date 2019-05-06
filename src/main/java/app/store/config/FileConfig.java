package app.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

@Configuration
public class FileConfig {


//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipart = new CommonsMultipartResolver();
//        multipart.setMaxUploadSize(50 * 1024 * 1024);
//        return multipart;
//    }

//    @Bean
//    public MultipartFilter multipartFilter() {
//        MultipartFilter multipartFilter = new MultipartFilter();
//        multipartFilter.setMultipartResolverBeanName("multipartReso‌​lver");
//        return multipartFilter;
//    }

//    @Bean
////    public MultipartResolver multipartResolver() {
////        return new CommonsMultipartResolver();
////    }


    //------------------
//    @Bean
//    public CommonsMultipartResolver filterMultipartResolver() {
//        return new CommonsMultipartResolver();
//    }

//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(-1);
//        return multipartResolver;
//    }


}