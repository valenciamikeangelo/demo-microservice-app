/*
* Modified by: gamboam
* Last updated: Jul 3, 2018 5:50:04 PM
*/
package com.caista.birapps.etis.system.module.report;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Class TrsModule.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "report")
@PropertySource("classpath:batch-report-modules.properties")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReportModule implements Cloneable, Serializable {

  /** The modules. */
  private List<Module> modules = new ArrayList<Module>();

  public List<Module> getModules() {
    return modules;
  }

}
