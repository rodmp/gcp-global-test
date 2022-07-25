package com.news.core.exception.model;

import java.util.List;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final List<String> badFields;
  
  public BadRequestException(String message, List<String> badFields) {
    super(message);
    this.badFields = badFields;
  }
  
}
