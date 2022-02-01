/*
 * Modified by: santojo
 * Last updated: Jun 25, 2018 4:39:48 PM
 */
package com.caista.birapps.etis.main.configuration;

import java.util.Collections;
import org.springframework.context.annotation.*;
import com.google.common.base.Predicates;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class RefSwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(Predicates.or(
            RequestHandlerSelectors.basePackage("com.caista.birapps.etis.reference.api"),
            RequestHandlerSelectors.basePackage("com.caista.birapps.etis.maintenance.api")))
        .paths(PathSelectors.any()).build().securitySchemes(Collections.singletonList(apiKey()));
  }

  private ApiKey apiKey() {
    return new ApiKey("Authorization", "Authorization", "header");
  }
}
