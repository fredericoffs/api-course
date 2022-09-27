package br.com.fredericosff.api.resource.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import br.com.fredericosff.api.services.exceptions.DataIntegrityViolationException;
import br.com.fredericosff.api.services.exceptions.ObjectNotFoundException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

@SpringBootTest
class ResourceExceptionHandlerTest {

  public static final String OBJECT_NOT_FOUND = "Object Not Found";
  public static final String E_MAIL_ALREADY_EXISTS = "E-mail already exists.";
  @InjectMocks
  private ResourceExceptionHandler exceptionHandler;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
    ResponseEntity<StandardError> response = exceptionHandler.objectNotFound(
        new ObjectNotFoundException(OBJECT_NOT_FOUND),
        new MockHttpServletRequest());

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(StandardError.class, response.getBody().getClass());
    assertEquals(OBJECT_NOT_FOUND, response.getBody().getError());
    assertEquals(404, response.getBody().getStatus());
    assertNotEquals("/user/2", response.getBody().getPath());
    assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
  }

  @Test
  void dataIntegrityViolationException() {
    ResponseEntity<StandardError> response = exceptionHandler.dataIntegrityViolationException(
        new DataIntegrityViolationException(E_MAIL_ALREADY_EXISTS),
        new MockHttpServletRequest());

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(StandardError.class, response.getBody().getClass());
    assertEquals(E_MAIL_ALREADY_EXISTS, response.getBody().getError());
    assertEquals(400, response.getBody().getStatus());
  }
}