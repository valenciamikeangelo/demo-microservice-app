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
import com.caista.birapps.etis.system.module.UserReportModule;

/**
 * The Class UserReportModuleHolder.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "user-report")
@PropertySource("classpath:report-modules.properties")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserReportModuleHolder implements Cloneable, Serializable {

  /** The modules. */
  private List<UserReportModule> modules = new ArrayList<UserReportModule>();

  public List<UserReportModule> getModules() {
    return modules;
  }

}
