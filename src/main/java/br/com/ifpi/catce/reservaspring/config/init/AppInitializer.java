package br.com.ifpi.catce.reservaspring.config.init;

import br.com.ifpi.catce.reservaspring.config.WebConfig;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class} ;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }




}
