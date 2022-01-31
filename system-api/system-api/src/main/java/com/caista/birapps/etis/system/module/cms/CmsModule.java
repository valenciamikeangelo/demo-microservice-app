/*
  * Modified by: obregoj
  * Last updated: 06 2, 20 12:32:01 PM
  */
package com.caista.birapps.etis.system.module.cms;

import java.util.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Class CmsModule.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cms")
@PropertySource("classpath:cms-modules.properties")
@Scope(value = "prototype")
public class CmsModule {

  /** The modules. */
  private List<Module> modules = new ArrayList<>();

  public List<Module> getModules() {
    return modules;
  }

}
