/*
  * Modified by: logronj
  * Last updated: Sep 27, 2018 5:20:22 PM
*/
package com.caista.birapps.etis.domain.audit.entity.annotation;

import java.lang.annotation.*;
import com.caista.birapps.etis.common.utils.module.*;
import com.caista.birapps.etis.domain.audit.entity.constant.LogEvent;

/**
 * The Interface EtisLog.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ETISLog {


  EtisModules etisModule();

  EtisSubmodules etisSubmodule();

  LogEvent logEvent();

}
