/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:39:08 PM
 */
package com.caista.birapps.etis.system.module.dashboard;

import java.util.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Class DashboardModule.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "dashboard")
@PropertySource("classpath:dashboard-modules.properties")
public class DashboardModule {

  /** The modules. */
  private List<Module> modules = new ArrayList<Module>();

  public List<Module> getModules() {
    return modules;
  }

}
