package br.com.fredericosff.api.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fredericosff.api.domain.Users;
import br.com.fredericosff.api.domain.dto.UsersDTO;
import br.com.fredericosff.api.services.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserResourceTest {

  public static final Integer ID = 1;
  public static final String NAME = "user1";
  public static final String EMAIL = "user1@mail.com";
  public static final String PASSWORD = "123";
  public static final String USER_NOT_FOUND = "User not found.";
  public static final int INDEX = 0;
  public static final String EMAIL_ALREADY_EXISTS = "email already exists.";

  private Users users = new Users();
  private UsersDTO usersDTO = new UsersDTO();
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
  void whenFindByIdThenReturnSuccess() {
    when(service.findById(anyInt())).thenReturn(users);
    when(mapper.map(any(), any())).thenReturn(usersDTO);

    ResponseEntity<UsersDTO> response = resource.findById(ID);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(UsersDTO.class, response.getBody().getClass());

    assertEquals(ID, response.getBody().getId());
    assertEquals(NAME, response.getBody().getName());
    assertEquals(EMAIL, response.getBody().getEmail());
    assertEquals(PASSWORD, response.getBody().getPassword());
  }

  @Test
  void whenFindAllThenReturnAListOfUsersDTO() {
    when(service.findAll()).thenReturn(List.of(users));
    when(mapper.map(any(), any())).thenReturn(usersDTO);

    ResponseEntity<List<UsersDTO>> response = resource.findAll();

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(ArrayList.class, response.getBody().getClass());
    assertEquals(UsersDTO.class, response.getBody().get(INDEX).getClass());

    assertEquals(ID, response.getBody().get(INDEX).getId());
    assertEquals(NAME, response.getBody().get(INDEX).getName());
    assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
    assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
  }

  @Test
  void whenCreateThenReturnCreated() {
    when(service.create(any())).thenReturn(users);

    ResponseEntity<UsersDTO> response = resource.create(usersDTO);

    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getHeaders().get("Location"));
  }

  @Test
  void whenUpdateThenReturnSuccess() {
    when(service.update(usersDTO)).thenReturn(users);
    when(mapper.map(any(), any())).thenReturn(usersDTO);

    ResponseEntity<UsersDTO> response = resource.update(ID, usersDTO);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(UsersDTO.class, response.getBody().getClass());

    assertEquals(ID, response.getBody().getId());
    assertEquals(NAME, response.getBody().getName());
    assertEquals(EMAIL, response.getBody().getEmail());
  }

  @Test
  void whenDeleteReturnNoContent() {
    doNothing().when(service).delete(anyInt());

    ResponseEntity<UsersDTO> response = resource.delete(ID);

    assertNotNull(response);
    assertEquals(ResponseEntity.class, response.getClass());
    verify(service, times(1)).delete(anyInt());
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  private void startUser() {
    users = new Users(ID, NAME, EMAIL, PASSWORD);
    usersDTO = new UsersDTO(ID, NAME, EMAIL, PASSWORD);
    optionalUsers = Optional.of(new Users(ID, NAME, EMAIL, PASSWORD));
  }
}