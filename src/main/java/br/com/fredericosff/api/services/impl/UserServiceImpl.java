package br.com.fredericosff.api.services.impl;

import br.com.fredericosff.api.domain.Users;
import br.com.fredericosff.api.repositories.UserRepository;
import br.com.fredericosff.api.services.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository repository;

  @Override
  public Users findById(Integer id) {
    Optional<Users> obj = repository.findById(id);
    return obj.orElse(null);
  }
}
