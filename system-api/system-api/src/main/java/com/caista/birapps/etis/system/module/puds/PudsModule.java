/*
 * Modified by: santojo
 * Last updated: May 2, 2019 10:09:06 AM
 */
package com.caista.birapps.etis.system.module.puds;

import java.util.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import com.caista.birapps.etis.system.module.Module;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "puds")
@PropertySource("classpath:puds-modules.properties")
// @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Scope(value = "prototype")
public class PudsModule {

  private List<Module> modules = new ArrayList<>();

  public List<Module> getModules() {
    return modules;
  }
}
