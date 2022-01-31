/*
 * Modified by: tolentf
 * Last updated: Mar 2, 2020 11:02:58 AM
 */
package com.caista.birapps.etis.system.module.auditselection;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Class AuditSelectionModule.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "auditselection")
@PropertySource("classpath:auditselection-modules.properties")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuditSelectionModule implements Cloneable, Serializable {

  /** The modules. */
  private List<Module> modules = new ArrayList<>();

  public List<Module> getModules() {
    return modules;
  }

}
