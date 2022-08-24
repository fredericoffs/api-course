package br.com.fredericosff.api.config;

import br.com.fredericosff.api.domain.Users;
import br.com.fredericosff.api.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class LocalConfig {

  @Autowired
  private UserRepository repository;

  @Bean
  public void startDB() {
    Users u1 = new Users(null, "User 1", "email1@email.com", "123");
    Users u2 = new Users(null, "User 2", "email2@email.com", "123");

    repository.saveAll(List.of(u1, u2));
  }
}
