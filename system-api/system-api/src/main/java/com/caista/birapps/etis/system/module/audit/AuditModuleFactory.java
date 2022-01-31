package com.caista.birapps.etis.system.module.audit;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * A factory for creating AuditModule objects.
 */
@Component
public class AuditModuleFactory implements ApplicationContextAware {

  /** The context. */
  private ApplicationContext context;

  /**
   * Creates the.
   *
   * @return the audit module
   */
  public AuditModule create() {
	  AuditModule cmsModule = context.getBean(AuditModule.class);
    return cmsModule;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
}
