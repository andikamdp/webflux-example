package id.co.practice.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.server.i18n.LocaleContextResolver;

@Configuration
public class ValidationConfig {
    private final LocaleContextResolver localeContextResolver;

    public ValidationConfig(LocaleContextResolver localeContextResolver) {
        this.localeContextResolver = localeContextResolver;
    }

    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setMessageInterpolator(new ReactiveMessageInterpolator(localeContextResolver));
        return factoryBean;
    }
}
