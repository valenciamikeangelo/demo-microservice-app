/*
 * Modified by: tolentf
 * Last updated: Mar 2, 2020 11:02:27 AM
 */
package com.caista.birapps.etis.system.module.auditselection;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * A factory for creating AuditSelectionModule objects.
 */
@Component
public class AuditSelectionModuleFactory implements ApplicationContextAware {

  /** The context. */
  private ApplicationContext context;

  /**
   * Creates the.
   *
   * @return the audit selection module
   */
  public AuditSelectionModule create() {
    AuditSelectionModule cmsModule = context.getBean(AuditSelectionModule.class);
    return cmsModule;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
}
