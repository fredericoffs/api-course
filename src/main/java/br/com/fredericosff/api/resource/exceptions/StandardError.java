package br.com.fredericosff.api.resource.exceptions;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StandardError {

  private LocalDateTime timestamp;
  private Integer status;
  private String error;
  private String path;
}