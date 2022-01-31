/*
 * Modified by: santojo
 * Last updated: May 2, 2019 9:48:45 AM
 */
package com.caista.birapps.etis.system.module.puds;

import org.springframework.context.*;
import org.springframework.stereotype.Component;

@Component
public class PudsModuleFactory implements ApplicationContextAware {

  private ApplicationContext context;

  public PudsModule create() {
    return context.getBean(PudsModule.class);
  }

  @Override
  public void setApplicationContext(ApplicationContext context) {
    this.context = context;
  }
}
