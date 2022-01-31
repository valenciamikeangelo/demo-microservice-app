/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:39:09 PM
 */
package com.caista.birapps.etis.system.module.taskmanager;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * A factory for creating TaskManagerModule objects.
 */
@Component
public class TaskManagerModuleFactory implements ApplicationContextAware {

  /** The context. */
  private ApplicationContext context;

  /**
   * Creates the.
   *
   * @return the task manager module
   */
  public TaskManagerModule create() {
    TaskManagerModule taskManagerModule = context.getBean(TaskManagerModule.class);
    return taskManagerModule;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
}
