/*
  * Modified by: sarmier
  * Last updated: Aug 14, 2018 8:24:48 PM
  */
package com.caista.birapps.etis.system.module.sysad;

import java.util.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Class SysAdModule.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "sysad")
@PropertySource("classpath:sysad-modules.properties")
@Scope(value = "prototype")
public class SysAdModule {

  /** The modules. */
  private List<Module> modules = new ArrayList<>();

  public List<Module> getModules() {
    return modules;
  }
}
