package br.com.fredericosff.api.services;

import br.com.fredericosff.api.domain.Users;
import br.com.fredericosff.api.domain.dto.UsersDTO;
import java.util.List;

public interface UserService {

  Users findById(Integer id);

  List<Users> findAll();

  Users create(UsersDTO obj);

  Users update(UsersDTO obj);

  void delete(Integer id);
}
