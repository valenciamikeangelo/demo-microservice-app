/*
  * Modified by: logronj
  * Last updated: Oct 2, 2018 11:46:31 AM
*/
package com.caista.birapps.etis.maintenance.service.factory;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.maintenance.service.AuditableMaintenanceService;

@Component
@SuppressWarnings("rawtypes")
public class AuditableMaintenanceServiceFactory {

  @Resource(name = "maintenanceServiceMap")
  private Map<String, AuditableMaintenanceService<?>> maintenanceServiceMap;

  public AuditableMaintenanceService getMaintenanceService(
      MaintenanceTypeEnum maintenanceTypeEnum) {
    if (null != maintenanceTypeEnum) {
      return maintenanceServiceMap.get(maintenanceTypeEnum.toString());
    } else {
      return null;
    }

  }

}
