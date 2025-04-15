package id.co.practice.webflux.config;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.propagation.Propagator;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class TraceIdFilter implements WebFilter {

    private final Tracer tracer;
    private final Propagator propagator;

    public TraceIdFilter(Tracer tracer, Propagator propagator) {
        this.tracer = tracer;
        this.propagator = propagator;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // Extract trace ID from the incoming request
        String clientTraceId = request.getHeaders().getFirst("X-Trace-Id");

        Span span;
        if (clientTraceId != null) {
            // Create a new span with the provided trace ID
            Span.Builder spanBuilder = propagator.extract(request, new Propagator.Getter<ServerHttpRequest>() {
                @Override
                public String get(ServerHttpRequest carrier, String key) {
                    List<String> values = carrier.getHeaders().get(key);
                    return (values != null && !values.isEmpty()) ? values.get(0) : null;
                }
            });
            span = spanBuilder.name("client-trace").start();
        } else {
            // Start a new trace if no trace ID is provided
            span = tracer.nextSpan().name("new-trace").start();
            clientTraceId = span.context().traceId();
        }

        // Add the trace ID to the response headers
        response.getHeaders().add("X-Trace-Id", clientTraceId);

        try (Tracer.SpanInScope spanInScope = tracer.withSpan(span)) {
            return chain.filter(exchange)
                    .doFinally(signalType -> span.end());
        }
    }
}