package br.com.fredericosff.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
            @Getter
@Data       @Setter
            @EqualsAndHashCode
            @ToString
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;

  @Column(unique = true)
  private String email;
  private String password;

}
