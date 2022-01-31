/*
 * Modified by: pastolc
 * Last updated: Feb 27, 2019 9:21:50 AM
 */
package com.caista.birapps.etis.system.module.tas;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * A factory for creating TcsModule objects.
 */
@Component
public class TasModuleFactory implements ApplicationContextAware {

  /** The context. */
  private ApplicationContext context;

  /**
   * Creates the.
   *
   * @return the tas module
   */
  public TasModule create() {
    TasModule tasModule = context.getBean(TasModule.class);
    return tasModule;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
}
