package com.news.core.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@EnableConfigurationProperties
public class ErrorConstants {

  public enum ErrorType{
    ERROR, WARN, INVALID, FATAL;
  }
  
  @Value("${constant.error.error.resolver.genericException.message}")
  private String genericErrorMessage;
  
  @Value("${constant.error.error.resolver.dataNotFoundException.message}")
  private String dataNotFoundErrorMessage;
  
  @Value("${constant.error.error.resolver.genericException.code}")
  private String genericErrorCode;
  
}
