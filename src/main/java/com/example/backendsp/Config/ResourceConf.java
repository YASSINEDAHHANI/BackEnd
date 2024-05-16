package com.example.backendsp.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConf implements WebMvcConfigurer {
    @Override
    public void  addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/content/**")
                .addResourceLocations("file:///C:/Users/dahan/Downloads/Compressed/test/controle/BackendSP/src/main/resources/static/public/*");
        ;
    }
}
