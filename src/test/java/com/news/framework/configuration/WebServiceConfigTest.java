package com.news.framework.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import com.news.framework.configuration.WebServiceConfig;

@ExtendWith(MockitoExtension.class)
public class WebServiceConfigTest {

  @InjectMocks
  private WebServiceConfig webServiceConfig;

  @Test
  public void whenMessageDispatcherServlet_then_ReturnServletRegistrationBean() {
    ApplicationContext applicationContext = mock(ApplicationContext.class);

    assertThat(this.webServiceConfig.messageDispatcherServlet(applicationContext))
        .isInstanceOf(ServletRegistrationBean.class);
  }

  @Test
  public void whenDefaultWsdl11Definition_then_ReturnDefaultWsdl11Definition() {
    XsdSchema xsdSchema = mock(XsdSchema.class);

    assertThat(this.webServiceConfig.defaultWsdl11Definition(xsdSchema))
        .isInstanceOf(DefaultWsdl11Definition.class);
  }

  @Test
  public void whenPokeApiSchema_then_ReturnXsdSchema() {
    assertThat(this.webServiceConfig.pokeApiSchema()).isInstanceOf(SimpleXsdSchema.class);
  }
}
