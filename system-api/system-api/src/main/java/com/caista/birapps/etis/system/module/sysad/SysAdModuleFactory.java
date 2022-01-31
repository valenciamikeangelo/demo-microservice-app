/*
  * Modified by: sarmier
  * Last updated: Aug 14, 2018 8:25:02 PM
  */
package com.caista.birapps.etis.system.module.sysad;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * A factory for creating SysAdModule objects.
 */
@Component
public class SysAdModuleFactory implements ApplicationContextAware {

  /** The context. */
  private ApplicationContext context;

  /**
   * Creates the.
   *
   * @return the sys ad module
   */
  public SysAdModule create() {
    SysAdModule sysadModule = context.getBean(SysAdModule.class);
    return sysadModule;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
}
