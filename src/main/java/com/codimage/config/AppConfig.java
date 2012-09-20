package com.codimage.config;

import com.codimage.AppConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Spring app configuration.
 *
 * @author Ruslan Khmelyuk
 */
@EnableCaching
@EnableTransactionManagement
@PropertySource({"classpath:queries.properties", "classpath:dataSource.properties"})
@ComponentScan("com.codimage.image")
@Configuration
public class AppConfig {

    @Value("${dataSource.url}") private String url;
    @Value("${dataSource.username}") private String username;
    @Value("${dataSource.password}") private String password;
    @Value("${dataSource.driverClassName}") private String driverClassName;

    @Bean public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean public DataSource dataSource() {
        final DriverManagerDataSource result = new DriverManagerDataSource(url, username, password);
        result.setDriverClassName(driverClassName);
        return result;
    }

    @Bean public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(AppConstant.CACHE_NEXT_IMAGE, AppConstant.CACHE_PREV_IMAGE);
    }

}
