package br.com.fredericosff.api.services;

import br.com.fredericosff.api.domain.Users;

public interface UserService {

  Users findById(Integer id);

}
