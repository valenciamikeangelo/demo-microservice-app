/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:39:25 PM
 */
package com.caista.birapps.etis.system.module.tcs;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * A factory for creating TcsModule objects.
 */
@Component
public class TcsModuleFactory implements ApplicationContextAware {

  /** The context. */
  private ApplicationContext context;

  /**
   * Creates the.
   *
   * @return the tcs module
   */
  public TcsModule create() {
    TcsModule cmsModule = context.getBean(TcsModule.class);
    return cmsModule;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
}
