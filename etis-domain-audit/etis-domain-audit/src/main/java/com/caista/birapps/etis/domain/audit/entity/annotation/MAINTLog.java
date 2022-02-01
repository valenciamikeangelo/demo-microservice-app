/*
  * Modified by: obregoj
  * Last updated: Oct 23, 2018 5:22:51 PM
  */
package com.caista.birapps.etis.domain.audit.entity.annotation;

import java.lang.annotation.*;
import com.caista.birapps.etis.common.utils.module.*;
import com.caista.birapps.etis.domain.audit.entity.constant.LogEvent;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MAINTLog {


  EtisModules etisModule();

  EtisSubmodules etisSubmodule();

  LogEvent logEvent();

  MaintenanceTypeEnum maintenanceTypeEnum();



}

