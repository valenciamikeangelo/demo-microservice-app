/*
* Modified by: gamboam
* Last updated: Jul 3, 2018 5:53:47 PM
*/
package com.caista.birapps.etis.system.module.report;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * A factory for creating ReportModule objects.
 */
@Component
public class ReportModuleFactory implements ApplicationContextAware {

  /** The context. */
  private ApplicationContext context;

  /**
   * Creates the.
   *
   * @return the report module
   */
  public ReportModule create() {
    ReportModule trsModule = context.getBean(ReportModule.class);
    return trsModule;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
}
