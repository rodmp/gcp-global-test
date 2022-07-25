package com.news.framework.configuration;

import org.springframework.stereotype.Component;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

/**
 * Configutation class to receive XML request.
 * 
 * @author rodo.
 *
 */
@EnableWs
@Component
public class WebServiceConfig extends WsConfigurerAdapter {


}
