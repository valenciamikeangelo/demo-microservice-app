/*
  * Modified by: sarmier
  * Last updated: Jul 31, 2019 5:51:18 PM
  */
package com.caista.birapps.etis.maintenance.service;

import com.caista.birapps.etis.domain.sysad.entity.maintenance.wrapper.TaskEmailGroup;

public interface TaskEmailGroupService {

  public TaskEmailGroup updateTaskEmailGroup(TaskEmailGroup taskEmailGroup);

  public TaskEmailGroup saveTaskEmailGroup(TaskEmailGroup taskEmailGroup);


}
