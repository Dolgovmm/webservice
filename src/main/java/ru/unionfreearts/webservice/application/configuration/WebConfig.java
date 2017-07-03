package ru.unionfreearts.webservice.application.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author M.Dolgov
 * @date 02.07.2017.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true);
        configurer.favorPathExtension(false);
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
