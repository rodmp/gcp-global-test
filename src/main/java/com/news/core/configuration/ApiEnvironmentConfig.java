package com.news.core.configuration;

import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;

@Getter
@Configuration
public class ApiEnvironmentConfig {

  @Value("${constants.languages.news}")
  private List<String> countries;

  @Value("${constants.token.gnews.api}")
  private String tokenApi;

  @Bean
  public String getLanguage() {
    Locale locale = Locale.getDefault();
    return locale.getLanguage();
  }
}
