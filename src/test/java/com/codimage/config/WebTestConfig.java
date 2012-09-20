package com.codimage.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * WebConfig for test environment.
 *
 * @author Ruslan Khmelyuk
 */
@Configuration
@ComponentScan("com.codimage.web")
public class WebTestConfig {

}
