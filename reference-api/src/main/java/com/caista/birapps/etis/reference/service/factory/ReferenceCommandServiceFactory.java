/*
  * Modified by: sarmier
  * Last updated: Sep 29, 2018 12:44:09 PM
  */
package com.caista.birapps.etis.reference.service.factory;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.sysad.enums.ReferenceTypeEnum;
import com.caista.birapps.etis.reference.service.ReferenceCommandService;

@Component
@SuppressWarnings("rawtypes")
public class ReferenceCommandServiceFactory {

  @Resource(name = "referenceCommandServiceMap")
  private Map<String, ReferenceCommandService<?>> referenceCommandServiceMap;

  public ReferenceCommandService getCommandService(ReferenceTypeEnum referenceType) {

    if (null != referenceType) {
      return referenceCommandServiceMap.get(referenceType.toString());
    } else {
      return null;
    }
  }

}
