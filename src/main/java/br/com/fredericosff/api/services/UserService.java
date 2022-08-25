package br.com.fredericosff.api.services;

import br.com.fredericosff.api.domain.Users;
import java.util.List;

public interface UserService {

  Users findById(Integer id);

  List<Users> findAll();
}
