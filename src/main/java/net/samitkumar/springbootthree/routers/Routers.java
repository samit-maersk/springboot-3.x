package net.samitkumar.springbootthree.routers;

import net.samitkumar.springbootthree.handlers.Handlers;
import net.samitkumar.springbootthree.models.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class Routers {

    @Bean
    public RouterFunction route(Handlers handlers) {
        return RouterFunctions
                .route()
                .GET("/message", handlers::message)
                .path("/user", builder -> builder
                        .GET("", handlers::allUser)
                        .GET("/{id}", handlers::userById))
                .after(((serverRequest, serverResponse) -> serverResponse))
                .build();
    }
}
