/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:38:59 PM
 */
package com.caista.birapps.etis.system.config;

import java.util.Collections;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /**
   * Api.
   *
   * @return the docket
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.caista.birapps.etis.system"))
        .paths(PathSelectors.any()).build().securitySchemes(Collections.singletonList(apiKey()));
  }

  /**
   * Api key.
   *
   * @return the api key
   */
  private ApiKey apiKey() {
    return new ApiKey("Bearer", "Authorization", "Header");
  }

}
