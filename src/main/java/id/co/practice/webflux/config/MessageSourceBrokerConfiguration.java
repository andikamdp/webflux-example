package id.co.practice.webflux.config;

import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.server.i18n.LocaleContextResolver;
import reactor.core.publisher.Mono;

import java.util.Locale;

import static javax.swing.JComponent.getDefaultLocale;

@Configuration
public class MessageSourceBrokerConfiguration {

    @Bean
    public LocaleContextResolver localeResolver() {
        AcceptHeaderLocaleContextResolver localeResolver = new AcceptHeaderLocaleContextResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH); // Set default locale
        return localeResolver;
    }

    @Bean
    public MessageSource initializeErrorMessageSource(){
        ResourceBundleMessageSource message = new ResourceBundleMessageSource();
        message.setBasenames("messages");
        message.setFallbackToSystemLocale(false);
        message.setUseCodeAsDefaultMessage(true);

        return message;
    }
}
