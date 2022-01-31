/*
  * Modified by: obregoj
  * Last updated: 01 17, 20 9:31:45 AM
  */
package com.caista.birapps.etis.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * The Class SystemApiApplication.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.caista.birapps"})
@MapperScan({"com.caista.birapps.etis.system.keycloak.mapper"})
public class SystemApiApplication {

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    System.setProperty("server.servlet.context-path", "/system");
    SpringApplication.run(SystemApiApplication.class, args);
  }

  @Bean
  public CommonsRequestLoggingFilter logFilter() {
    CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
    filter.setIncludeClientInfo(true);
    filter.setIncludeQueryString(true);
    filter.setIncludePayload(true);
    filter.setMaxPayloadLength(10000);
    filter.setIncludeHeaders(false);
    filter.setAfterMessagePrefix("REQUEST DATA : ");
    return filter;
  }
}
