/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:39:17 PM
 */
package com.caista.birapps.etis.system.module.taskmanager;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Class TaskManagerModule.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "taskmanager")
@PropertySource("classpath:taskmanager-modules.properties")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TaskManagerModule implements Cloneable, Serializable {

  /** The modules. */
  private List<Module> modules = new ArrayList<Module>();

  public List<Module> getModules() {
    return modules;
  }

}
