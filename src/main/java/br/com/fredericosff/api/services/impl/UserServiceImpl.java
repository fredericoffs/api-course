package br.com.fredericosff.api.services.impl;

import br.com.fredericosff.api.domain.Users;
import br.com.fredericosff.api.domain.dto.UsersDTO;
import br.com.fredericosff.api.repositories.UserRepository;
import br.com.fredericosff.api.services.UserService;
import br.com.fredericosff.api.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository repository;

  @Autowired
  private ModelMapper mapper;

  @Override
  public Users findById(Integer id) {
    Optional<Users> obj = repository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("User not found."));
  }

  public List<Users> findAll() {
    return repository.findAll();
  }

  @Override
  public Users create(UsersDTO obj) {
    return repository.save(mapper.map(obj, Users.class));
  }


}
