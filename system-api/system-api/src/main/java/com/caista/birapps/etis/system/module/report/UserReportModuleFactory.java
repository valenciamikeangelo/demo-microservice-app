/*
 * Modified by: fuentem
 * Last updated: Sep 5, 2018 2:50:01 PM
 */
package com.caista.birapps.etis.system.module.report;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * A factory for creating UserReportModule objects.
 */
@Component
public class UserReportModuleFactory implements ApplicationContextAware {

  /** The context. */
  private ApplicationContext context;

  /**
   * Creates the.
   *
   * @return the user report module
   */
  public UserReportModuleHolder create() {
    UserReportModuleHolder userReportModuleHolder = context.getBean(UserReportModuleHolder.class);
    return userReportModuleHolder;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
}
