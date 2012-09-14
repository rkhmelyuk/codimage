package com.codimage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Web MVC configuration.
 *
 * @author Ruslan Khmelyuk
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.codimage.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);

        registry.addViewController("/index.html");
    }

    @Bean public ViewResolver viewResolver() {
        final InternalResourceViewResolver result = new InternalResourceViewResolver();

        result.setViewClass(JstlView.class);
        result.setPrefix("/WEB-INF/views/");
        result.setSuffix(".jsp");

        return result;
    }
}
