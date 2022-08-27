package com.evo.springboot.app;

import com.evo.springboot.db.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication()
@EnableSwagger2
@ComponentScan({"com"})
@EntityScan("com")
@EnableJpaRepositories("com")
public class WebServer {

    @Autowired
    private UserRepository userRepo;

    public static void main(String[] args) {
        SpringApplication.run(WebServer.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000") // not wildcard! on prod replace with real domain
                        .allowCredentials(true)
                        .allowedHeaders("Content-Type") // not wildcard!
                        .allowedMethods("POST", "DELETE", "GET", "OPTIONS"); // not wildcard!

            }
        };
    }
}
