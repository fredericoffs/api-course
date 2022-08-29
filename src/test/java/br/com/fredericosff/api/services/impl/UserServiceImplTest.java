package br.com.fredericosff.api.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import br.com.fredericosff.api.domain.Users;
import br.com.fredericosff.api.domain.dto.UsersDTO;
import br.com.fredericosff.api.repositories.UserRepository;
import br.com.fredericosff.api.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

  public static final Integer ID = 1;
  public static final String NAME = "user1";
  public static final String EMAIL = "user1@mail.com";
  public static final String PASSWORD = "123";
  public static final String USER_NOT_FOUND = "User not found.";
  public static final int INDEX = 0;

  @InjectMocks
  private UserServiceImpl service;

  @Mock
  private UserRepository repository;

  @Mock
  private ModelMapper mapper;

  private Users users;
  private UsersDTO usersDTO;
  private Optional<Users> optionalUsers;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    startUser();
  }

  @Test
  void whenFindByIdThenReturnAnUser() {
    when(repository.findById(anyInt())).thenReturn(optionalUsers);

    Users response = service.findById(ID);

    assertNotNull(response);

    assertEquals(Users.class, response.getClass());
    assertEquals(ID, response.getId());
    assertEquals(NAME, response.getName());
    assertEquals(EMAIL, response.getEmail());
  }

  @Test
  void whenFindByIdThenReturnAnUserNotFoundException() {
    when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(USER_NOT_FOUND));

    try {
      service.findById(ID);
    } catch (Exception ex) {
      assertEquals(ObjectNotFoundException.class, ex.getClass());
      assertEquals(USER_NOT_FOUND, ex.getMessage());
    }
  }

  @Test
  void whenFindAllThenReturnAListOfUsers() {
    when(repository.findAll()).thenReturn(List.of(users));

    List<Users> response = service.findAll();

    assertNotNull(response);
    assertEquals(1, response.size());
    assertEquals(Users.class, response.get(INDEX).getClass());

    assertEquals(ID, response.get(INDEX).getId());
    assertEquals(NAME, response.get(INDEX).getName());
    assertEquals(EMAIL, response.get(INDEX).getEmail());
    assertEquals(PASSWORD, response.get(INDEX).getPassword());
  }

  @Test
  void create() {
  }

  @Test
  void update() {
  }

  @Test
  void delete() {
  }

  private void startUser() {
    users = new Users(ID, NAME, EMAIL, PASSWORD);
    usersDTO = new UsersDTO(ID, NAME, EMAIL, PASSWORD);
    optionalUsers = Optional.of(new Users(ID, NAME, EMAIL, PASSWORD));
  }
}