package com.caista.birapps.etis.system.module.audit;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Class AuditModule.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "audit")
@PropertySource("classpath:audit-modules.properties")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuditModule implements Cloneable, Serializable {

  /** The modules. */
  private List<Module> modules = new ArrayList<Module>();

  public List<Module> getModules() {
    return modules;
  }

}
