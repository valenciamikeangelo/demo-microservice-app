/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:38:56 PM
 */
package com.caista.birapps.etis.system.module.crr;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * A factory for creating CrrModule objects.
 */
@Component
public class CrrModuleFactory implements ApplicationContextAware {

  /** The context. */
  private ApplicationContext context;

  /**
   * Creates the.
   *
   * @return the crr module
   */
  public CrrModule create() {
    CrrModule crrModule = context.getBean(CrrModule.class);
    return crrModule;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
}
