package net.samitkumar.springbootthree;

import net.samitkumar.springbootthree.clients.UserClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class SpringbootThreeApplication {
	@Value("${spring.application.jsonplaceHolder.host}")
	private String jsonplaceholderHost;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootThreeApplication.class, args);
	}

	@Bean
	HttpServiceProxyFactory httpServiceProxyFactory(WebClient.Builder builder){
		var webClientAdapter = WebClientAdapter
				.forClient(builder.baseUrl(jsonplaceholderHost).build());
		return HttpServiceProxyFactory.builder(webClientAdapter).build();
	}

	@Bean
	UserClient userClient(HttpServiceProxyFactory httpServiceProxyFactory) {
		return httpServiceProxyFactory.createClient(UserClient.class);
	}
}
