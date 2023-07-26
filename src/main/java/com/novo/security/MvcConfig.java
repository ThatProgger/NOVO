package com.novo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The web application is based on Spring MVC. As a result, you need to configure Spring MVC and set up view controllers to expose these templates.
 *
 * @author Mikahil Dedyukhin
 * @see <a href="https://spring.io/guides/gs/securing-web/">Securing a Web Application</a>
 * @since 1.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("page");
        registry.addViewController("/login").setViewName("login");
    }
}
