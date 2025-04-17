package id.co.practice.webflux.config;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.LocaleContextResolver;

import java.util.Locale;

@Component
public class CustomLocaleContextResolver implements LocaleContextResolver {

    @Override
    public LocaleContext resolveLocaleContext(ServerWebExchange exchange) {
        String acceptLanguage = exchange.getRequest().getHeaders().getFirst("Accept-Language");
        Locale locale = (acceptLanguage != null) ? Locale.forLanguageTag(acceptLanguage) : Locale.getDefault();
        return new SimpleLocaleContext(locale);
    }

    @Override
    public void setLocaleContext(ServerWebExchange exchange, LocaleContext localeContext) {
        throw new UnsupportedOperationException("Not supported");
    }
}