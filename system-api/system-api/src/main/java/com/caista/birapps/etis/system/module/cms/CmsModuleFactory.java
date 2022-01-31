/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:39:04 PM
 */
package com.caista.birapps.etis.system.module.cms;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * A factory for creating CmsModule objects.
 */
@Component
public class CmsModuleFactory implements ApplicationContextAware {

  /** The context. */
  private ApplicationContext context;

  /**
   * Creates the.
   *
   * @return the cms module
   */
  public CmsModule create() {
    CmsModule cmsModule = context.getBean(CmsModule.class);
    return cmsModule;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
}
