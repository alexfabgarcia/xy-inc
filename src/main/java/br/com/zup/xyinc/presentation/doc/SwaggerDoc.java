package br.com.zup.xyinc.presentation.doc;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Adiciona handler para documentação Swagger 2.
 */
@EnableWebMvc
@Component
public class SwaggerDoc implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("**/**").addResourceLocations("classpath:/META-INF/resources/");
    }

}