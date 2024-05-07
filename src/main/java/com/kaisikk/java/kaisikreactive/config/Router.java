package com.kaisikk.java.kaisikreactive.config;

import com.kaisikk.java.kaisikreactive.handlers.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler){

        RequestPredicate route = RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN));

        return RouterFunctions
                .route(route, greetingHandler::hello)
                .andRoute(
                        RequestPredicates.GET("/"),
                        serverRequest -> {
                            return ServerResponse
                                    .ok()
                                    .contentType(MediaType.TEXT_PLAIN)
                                    .body(
                                            BodyInserters.fromValue("Main Page")
                                    );
                        }
                );
    }

}
