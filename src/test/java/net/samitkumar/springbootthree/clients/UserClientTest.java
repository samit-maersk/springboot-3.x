package net.samitkumar.springbootthree.clients;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
public class UserClientTest {
    @Autowired
    UserClient userClient;

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.application.jsonplaceHolder.host", () -> "http://localhost:${wiremock.server.port}");
    }

    @BeforeEach
    void setUp() {
        stubFor(get(urlEqualTo("/users"))
                .willReturn(
                        aResponse()
                                .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                                .withBody("""
                                        [
                                                {
                                                    "id": 1,
                                                    "name": "Leanne Graham",
                                                    "email": "Sincere@april.biz",
                                                    "address": {
                                                        "street": "Kulas Light",
                                                        "suite": "Apt. 556",
                                                        "city": "Gwenborough",
                                                        "zipcode": "92998-3874",
                                                        "geo": {
                                                            "lat": "-37.3159",
                                                            "lng": "81.1496"
                                                        }
                                                    },
                                                    "phone": "1-770-736-8031 x56442",
                                                    "website": "hildegard.org",
                                                    "company": {
                                                        "name": "Romaguera-Crona",
                                                        "catchPhrase": "Multi-layered client-server neural-net",
                                                        "bs": "harness real-time e-markets"
                                                    }
                                                }
                                        ]
                                        """)));
    }

    @Test
    @DisplayName("/users proxy test")
    void all() {
        userClient
                .getAll()
                .as(StepVerifier::create)
                .consumeNextWith(response -> {
                    assertEquals(1,response.id());
                }).verifyComplete();
    }
}
