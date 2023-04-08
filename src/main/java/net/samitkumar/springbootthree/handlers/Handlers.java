package net.samitkumar.springbootthree.handlers;

import lombok.RequiredArgsConstructor;
import net.samitkumar.springbootthree.clients.UserClient;
import net.samitkumar.springbootthree.models.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class Handlers {
    private final UserClient userClient;

    public Mono<ServerResponse> allUser(ServerRequest request) {
        return ServerResponse.ok().body(userClient.getAll(), Flux.class);
    }

    public Mono<ServerResponse> userById(ServerRequest request) {
        var id = request.pathVariable("id");
        return ServerResponse.ok().body(userClient.getById(id), User.class);
    }

}
