/*
  * Modified by: resontf
  * Last updated: Oct 4, 2019 4:51:54 PM
  */
package com.caista.birapps.etis.system.module.batchmanagement;

import java.util.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import com.caista.birapps.etis.system.module.Module;


/**
 * The Class BatchModule.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "batchmanagement")
@PropertySource("classpath:batchmanagement-modules.properties")
@Scope(value = "prototype")
public class BatchManagementModule {

  /** The modules. */
  private List<Module> modules = new ArrayList<>();

  public List<Module> getModules() {
    return modules;
  }

}
