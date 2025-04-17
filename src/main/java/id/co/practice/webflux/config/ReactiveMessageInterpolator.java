package id.co.practice.webflux.config;

import jakarta.validation.MessageInterpolator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.i18n.LocaleContextResolver;
import reactor.core.publisher.Mono;

import java.util.Locale;

@Component
public class ReactiveMessageInterpolator implements MessageInterpolator {

    private final ResourceBundleMessageInterpolator delegate;
    private final LocaleContextResolver localeContextResolver;

    public ReactiveMessageInterpolator(LocaleContextResolver localeContextResolver) {
        this.delegate = new ResourceBundleMessageInterpolator();
        this.localeContextResolver = localeContextResolver;
    }

    @Override
    public String interpolate(String messageTemplate, Context context) {
        // Default locale fallback
        return delegate.interpolate(messageTemplate, context, Locale.getDefault());
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        return delegate.interpolate(messageTemplate, context, locale);
    }

    public Mono<String> interpolateReactive(String messageTemplate, Context context) {
        return ReactiveRequestContextHolder.getServerWebExchange()
                .map(exchange -> localeContextResolver.resolveLocaleContext(exchange).getLocale())
                .defaultIfEmpty(Locale.getDefault())
                .map(locale -> delegate.interpolate(messageTemplate, context, locale));
    }
}