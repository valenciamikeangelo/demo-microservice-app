/*
 * Modified by: pastolc
 * Last updated: Feb 27, 2019 9:21:29 AM
 */
package com.caista.birapps.etis.system.module.tas;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Class TcsModule.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "tas")
@PropertySource("classpath:tas-modules.properties")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TasModule implements Cloneable, Serializable {

  /** The modules. */
  private List<Module> modules = new ArrayList<Module>();

  public List<Module> getModules() {
    return modules;
  }

}
