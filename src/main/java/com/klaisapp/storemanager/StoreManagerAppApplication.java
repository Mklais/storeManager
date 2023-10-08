package com.klaisapp.storemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class StoreManagerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreManagerAppApplication.class, args);
    }

    @Bean
    public TomcatServletWebServerFactory servletWebServerContainerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setContextPath("/store");

        return factory;
    }
}
