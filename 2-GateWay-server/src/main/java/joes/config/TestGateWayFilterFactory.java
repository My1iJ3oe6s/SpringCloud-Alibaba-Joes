package joes.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class TestGateWayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    @Override
    public GatewayFilter apply(Object config) {
        return new TestGateWayFilter();
    }

    class TestGateWayFilter implements GatewayFilter, Ordered {
        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            System.out.println("局部过滤器---前置拦截器---pre");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("局部过滤器---后置拦截器---post");
            }));
        }

        @Override
        public int getOrder() {
            return 0;
        }
    }
}
