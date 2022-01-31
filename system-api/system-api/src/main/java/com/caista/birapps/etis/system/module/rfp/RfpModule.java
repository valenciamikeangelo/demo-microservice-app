/*
  * Modified by: logronj
  * Last updated: Sep 10, 2018 4:14:10 PM
*/
package com.caista.birapps.etis.system.module.rfp;

import java.util.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Class RfpModule.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "rfp")
@PropertySource("classpath:rfp-modules.properties")
@Scope(value = "prototype")
public class RfpModule {

  /** The modules. */
  private List<Module> modules = new ArrayList<Module>();

  public List<Module> getModules() {
    return modules;
  }

}
