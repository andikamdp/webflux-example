package com.example.webflux.webfluxexample.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceBrokerConfiguration {

    @Bean
    public MessageSource initializeErrorMessageSource(){
        ResourceBundleMessageSource message = new ResourceBundleMessageSource();
        message.setBasenames("error-message");
        message.setFallbackToSystemLocale(false);
        message.setUseCodeAsDefaultMessage(true);

        return message;
    }
}
