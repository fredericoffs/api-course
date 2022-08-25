package br.com.fredericosff.api.resource;

import br.com.fredericosff.api.domain.dto.UsersDTO;
import br.com.fredericosff.api.services.UserService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private UserService service;

  @GetMapping(value = "/{id}")
  public ResponseEntity<UsersDTO> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(mapper.map(service.findById(id), UsersDTO.class));
  }

  @GetMapping
  public ResponseEntity<List<UsersDTO>> findAll() {
    return ResponseEntity.ok()
        .body(service.findAll().stream().map(x -> mapper.map(x, UsersDTO.class))
            .collect(Collectors.toList()));
  }

  @PostMapping
  public ResponseEntity<UsersDTO> create(@RequestBody UsersDTO obj) {
    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
        .buildAndExpand(service.create(obj).getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<UsersDTO> update(@PathVariable Integer id, @RequestBody UsersDTO obj) {
    obj.setId(id);
    return ResponseEntity.ok().body(mapper.map(service.update(obj), UsersDTO.class));
  }
}