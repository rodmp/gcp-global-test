package com.news.core.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ApiEnvironmentConfigTest {

  @InjectMocks
  private ApiEnvironmentConfig apiEnvironmentConfig;
  
  @Test
  public void whenGetLanguage_then_returnLocalCountryCode() {
    assertThat(apiEnvironmentConfig.getLanguage()).isEqualTo("es");
  }
}
