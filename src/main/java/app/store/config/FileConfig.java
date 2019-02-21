package app.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.MultipartFilter;

@Configuration
public class FileConfig {

    //    @RequestParam("data") MultipartFile data,

//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipart = new CommonsMultipartResolver();
//        multipart.setMaxUploadSize(50 * 1024 * 1024);
//        return multipart;
//    }

    @Bean
    public MultipartFilter multipartFilter() {
        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartReso‌​lver");
        return multipartFilter;
    }

    //------------------
//    @Bean
//    public CommonsMultipartResolver filterMultipartResolver() {
//        return new CommonsMultipartResolver();
//    }
//
//    @Bean
//    @Cart(0)
//    public MultipartFilter multipartFilter() {
//        return new MultipartFilter();
//    }

}