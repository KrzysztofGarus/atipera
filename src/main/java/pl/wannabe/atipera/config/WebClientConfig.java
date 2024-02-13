package pl.wannabe.atipera.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for WebClient.
 */

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

}
