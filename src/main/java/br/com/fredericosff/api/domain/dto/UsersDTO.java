package br.com.fredericosff.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

  private Integer id;
  private String name;
  private String email;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;
}
