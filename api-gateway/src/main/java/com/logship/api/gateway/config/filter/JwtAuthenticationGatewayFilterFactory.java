package com.logship.api.gateway.config.filter;

import com.logship.api.gateway.service.JwtService;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Optional;

@Component
public class JwtAuthenticationGatewayFilterFactory  extends AbstractGatewayFilterFactory<JwtAuthenticationGatewayFilterFactory.Config> {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationGatewayFilterFactory(JwtService jwtService, UserDetailsService userDetailsService) {
        super(Config.class);
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                    .header("X-User-Agent", exchange.getRequest().getHeaders().getFirst("User-Agent"))
                    .header("X-Client-IP", Optional.ofNullable(exchange.getRequest().getRemoteAddress()).map(InetSocketAddress::getAddress).map(InetAddress::getHostAddress).orElse(null))
                    .build();
            String path = exchange.getRequest().getPath().toString();
            if (path.contains("/auth/login") || path.contains("/auth/signup")) {
                return chain.filter(exchange.mutate().request(modifiedRequest).build());
            }

            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String jwt = authHeader.substring(7).strip();
            String username = jwtService.extractUsername(jwt);
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                String ip = Optional.ofNullable(exchange.getRequest().getRemoteAddress()).map(InetSocketAddress::getAddress).map(InetAddress::getHostAddress).orElse("");
                String userAgent = Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("User-Agent")).orElse("");
                if (!jwtService.isTokenValid(jwt, userDetails, ip, userAgent)) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        };
    }

    public static class Config {
        // If you want to pass config params from YAML later, add fields here
    }
}
