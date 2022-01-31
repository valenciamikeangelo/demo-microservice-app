/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:39:16 PM
 */
package com.caista.birapps.etis.system.module.dashboard;

import java.util.List;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

/**
 * The Class TasksAuthorizationSupport.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "client")
@PropertySource("classpath:task-support-config.properties")
public class TasksAuthorizationSupport {

  /** The tasks client id. */
  private String tasksClientId;

  /** The task client resources. */
  private List<TaskClientResource> taskClientResources;

  public String getTasksClientId() {
    return tasksClientId;
  }

  public void setTasksClientId(String tasksClientId) {
    this.tasksClientId = tasksClientId;
  }

  public List<TaskClientResource> getTaskClientResources() {
    return taskClientResources;
  }

  public void setTaskClientResources(List<TaskClientResource> taskClientResources) {
    this.taskClientResources = taskClientResources;
  }


}
