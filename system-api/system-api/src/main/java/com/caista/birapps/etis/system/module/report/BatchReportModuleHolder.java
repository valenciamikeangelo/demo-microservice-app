/*
 * Modified by: fuentem
 * Last updated: Sep 5, 2018 2:12:49 PM
 */
package com.caista.birapps.etis.system.module.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Class BatchReportModuleHolder.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "batch-report")
@PropertySource("classpath:batch-report-modules.properties")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BatchReportModuleHolder implements Cloneable, Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -682592116055328402L;
  
  /** The modules. */
  private List<Module> modules = new ArrayList<Module>();

  public List<Module> getModules() {
    return modules;
  }

}
