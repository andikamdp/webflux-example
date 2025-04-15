package id.co.practice.webflux.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import reactor.core.publisher.Mono;

import java.util.Locale;

import static javax.swing.JComponent.getDefaultLocale;

@Configuration
public class CustomLocaleResolver extends AcceptHeaderLocaleContextResolver {

    @Override
    public LocaleContext resolveLocaleContext(ServerWebExchange exchange) {
        String acceptLanguage = exchange.getRequest().getHeaders().getFirst("Accept-Language");
        Locale locale = (acceptLanguage != null) ? Locale.forLanguageTag(acceptLanguage) : getDefaultLocale();
        return () -> locale;
    }
}
