package net.samitkumar.springbootthree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringbootThreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootThreeApplication.class, args);
	}

	WebClient webClient() {
		return WebClient.builder()
				.baseUrl("https://jsonplaceholder.typicode.com/")
				.build();
	}
}
