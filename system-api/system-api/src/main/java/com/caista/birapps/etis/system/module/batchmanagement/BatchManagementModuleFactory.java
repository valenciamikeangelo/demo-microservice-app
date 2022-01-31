/*
  * Modified by: resontf
  * Last updated: Oct 4, 2019 2:41:44 PM
  */
package com.caista.birapps.etis.system.module.batchmanagement;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;


/**
 * A factory for creating BatchModule objects.
 */
@Component
public class BatchManagementModuleFactory implements ApplicationContextAware {

  /** The context. */
  private ApplicationContext context;

  /**
   * Creates the.
   *
   * @return the task manager module
   */
  public BatchManagementModule create() {
    BatchManagementModule batchModule = context.getBean(BatchManagementModule.class);
    return batchModule;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
}
