package net.samitkumar.springbootthree.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class Routers {
    @Bean
    public RouterFunction route() {
        return RouterFunctions
                .route()
                .path("/user", builder -> builder
                        .GET("", request -> ServerResponse.noContent().build()))
                .after(((serverRequest, serverResponse) -> serverResponse))
                .build();
    }
}
