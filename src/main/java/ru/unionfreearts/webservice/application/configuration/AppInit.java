package ru.unionfreearts.webservice.application.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author M.Dolgov
 * @date 02.07.2017.
 */
public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                WebConfig.class,
                AppConfiguration.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {
                WebConfig.class,
                AppConfiguration.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
