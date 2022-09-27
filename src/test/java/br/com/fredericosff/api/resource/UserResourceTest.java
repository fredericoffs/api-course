package br.com.fredericosff.api.resource;

import br.com.fredericosff.api.domain.Users;
import br.com.fredericosff.api.domain.dto.UsersDTO;
import br.com.fredericosff.api.services.impl.UserServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserResourceTest {

  public static final Integer ID = 1;
  public static final String NAME = "user1";
  public static final String EMAIL = "user1@mail.com";
  public static final String PASSWORD = "123";
  public static final String USER_NOT_FOUND = "User not found.";
  public static final int INDEX = 0;
  public static final String EMAIL_ALREADY_EXISTS = "email already exists.";

  private Users users;
  private UsersDTO usersDTO;
  private Optional<Users> optionalUsers;

  @InjectMocks
  private UserResource resource;

  @Mock
  private UserServiceImpl service;

  @Mock
  private ModelMapper mapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    startUser();
  }

  @Test
  void findById() {
  }

  @Test
  void findAll() {
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