package net.samitkumar.springbootthree.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.samitkumar.springbootthree.clients.UserClient;
import net.samitkumar.springbootthree.models.Message;
import net.samitkumar.springbootthree.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class Handlers {
    @Value("classpath:message.json")
    private Resource resource;

    private final UserClient userClient;

    public Mono<ServerResponse> allUser(ServerRequest request) {
        return ServerResponse.ok().body(userClient.getAll(), Flux.class);
    }

    public Mono<ServerResponse> userById(ServerRequest request) {
        var id = request.pathVariable("id");
        return ServerResponse.ok().body(userClient.getById(id), User.class);
    }
    @SneakyThrows
    //TODO Fix java.io.FileNotFoundException: class path resource [message.json] cannot be opened because it does not exist while running the native executable
    public Mono<ServerResponse> message(ServerRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        var msg = objectMapper.readValue(resource.getInputStream(), Message.class);
        return ServerResponse.ok().body(Mono.just(msg), Message.class);
    }
}
