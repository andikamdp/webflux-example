package id.co.practice.webflux.config;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class ReactiveRequestContextHolder {
    private static final Class<ServerWebExchange> CONTEXT_KEY = ServerWebExchange.class;

    public static Mono<ServerWebExchange> getServerWebExchange() {
        return Mono.deferContextual(ctx -> Mono.justOrEmpty(ctx.get(CONTEXT_KEY)));
    }
}
