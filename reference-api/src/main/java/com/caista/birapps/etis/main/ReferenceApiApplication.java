/*
  * Modified by: sarmier
  * Last updated: Oct 15, 2018 2:07:07 AM
  */
package com.caista.birapps.etis.main;

import org.slf4j.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@ComponentScan({"com.caista.birapps.etis"})
@EntityScan(basePackages = {"com.caista.birapps.etis.domain.sysad.entity"})
@EnableJpaRepositories({"com.caista.birapps.etis.reference.repository",
    "com.caista.birapps.etis.maintenance.repository"})
@PropertySource("classpath:kafka.properties")
@PropertySource("classpath:application.properties")
@ImportResource({"classpath:aspect-config-reference.xml", "classpath:service-factory.xml"})
public class ReferenceApiApplication extends SpringBootServletInitializer
    implements WebApplicationInitializer {
  private static final Logger LOG = LoggerFactory.getLogger(ReferenceApiApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ReferenceApiApplication.class, args);
    LOG.info("Reference API has been started...");
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(ReferenceApiApplication.class);
  }
}
