package com.example.gateway;

import java.net.URI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@SpringBootApplication
public class GatewayApplication {

    @Bean
    public RouterFunction<ServerResponse> myRouteLocator() {
        return route().GET("/**", req ->
                        ServerResponse.permanentRedirect(URI.create("http://localhost:8010" + req.path())).build())
                .after((req, res) -> {System.out.println("got it! " + res.statusCode()); return res;})
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
