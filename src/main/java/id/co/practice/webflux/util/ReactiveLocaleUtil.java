package id.co.practice.webflux.util;

import id.co.practice.webflux.config.ReactiveRequestContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.i18n.LocaleContextResolver;
import reactor.core.publisher.Mono;

import java.util.Locale;

@Component
public class ReactiveLocaleUtil {

    private final LocaleContextResolver localeContextResolver;

    public ReactiveLocaleUtil(LocaleContextResolver localeContextResolver) {
        this.localeContextResolver = localeContextResolver;
    }

    public Mono<Locale> getLocale() {
        return ReactiveRequestContextHolder.getServerWebExchange()
                .map(localeContextResolver::resolveLocaleContext)
                .map(localeContext -> localeContext.getLocale())
                .defaultIfEmpty(Locale.getDefault());
    }
}