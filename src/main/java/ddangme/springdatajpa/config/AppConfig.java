package ddangme.springdatajpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ddangme.springdatajpa.repository")
public class AppConfig { }
