package com.codimage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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

    /** The cache period for static resources (js, css, static images) */
    private static final int STATIC_CACHE_PERIOD = 1209600; // 14 days

    @Override public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);

        registry.addViewController("/404");
        registry.addViewController("/500");
    }

    @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);

        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(STATIC_CACHE_PERIOD);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(STATIC_CACHE_PERIOD);
        registry.addResourceHandler("/image/**").addResourceLocations("/image/").setCachePeriod(STATIC_CACHE_PERIOD);
    }

    @Bean public ViewResolver viewResolver() {
        final InternalResourceViewResolver result = new InternalResourceViewResolver();

        result.setViewClass(JstlView.class);
        result.setPrefix("/WEB-INF/views/");
        result.setSuffix(".jsp");

        return result;
    }
}
