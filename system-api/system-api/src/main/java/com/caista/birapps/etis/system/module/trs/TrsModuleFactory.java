/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:39:06 PM
 */
package com.caista.birapps.etis.system.module.trs;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * A factory for creating TrsModule objects.
 */
@Component
public class TrsModuleFactory implements ApplicationContextAware {

  /** The context. */
  private ApplicationContext context;

  /**
   * Creates the.
   *
   * @return the trs module
   */
  public TrsModule create() {
    TrsModule trsModule = context.getBean(TrsModule.class);
    return trsModule;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
}
