/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:38:58 PM
 */
package com.caista.birapps.etis.system.module.tcr;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * A factory for creating TcrModule objects.
 */
@Component
public class TcrModuleFactory implements ApplicationContextAware {

  /** The context. */
  private ApplicationContext context;

  /**
   * Creates the.
   *
   * @return the tcr module
   */
  public TcrModule create() {
    TcrModule cmsModule = context.getBean(TcrModule.class);
    return cmsModule;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
}
