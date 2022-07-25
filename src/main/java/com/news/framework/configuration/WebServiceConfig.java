package com.news.framework.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Configutation class to receive XML request.
 * 
 * @author rodo.
 *
 */
@EnableWs
@Component
public class WebServiceConfig extends WsConfigurerAdapter {

  @Bean
  public ServletRegistrationBean messageDispatcherServlet(
      ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean(servlet, "/soap/*");
  }

  @Bean(name = "pokeapi")
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema pokeApiSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("PokeApiPort");
    wsdl11Definition.setLocationUri("/soap");
    wsdl11Definition.setTargetNamespace("http://www.pokeapi.com/soap/generation");
    wsdl11Definition.setSchema(pokeApiSchema);
    return wsdl11Definition;
  }

  @Bean
  public XsdSchema pokeApiSchema() {
    return new SimpleXsdSchema(new ClassPathResource("pokeapi.xsd"));
  }

}
