package io.pivotal.demo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean gwtDemoServletRegistration() {
        ServletRegistrationBean bean = new ServletRegistrationBean<GreetingServiceImpl>(
                new GreetingServiceImpl(), "/gwtdemo/greet");
        bean.setLoadOnStartup(1);
        return bean;
    }
}
