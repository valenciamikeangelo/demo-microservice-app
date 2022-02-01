/*
  * Modified by: sarmier
  * Last updated: Sep 29, 2018 12:43:47 PM
  */
package com.caista.birapps.etis.reference.service.factory;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.sysad.enums.ReferenceTypeEnum;
import com.caista.birapps.etis.reference.service.ReferenceQueryService;

@Component
@SuppressWarnings("rawtypes")
public class ReferenceQueryServiceFactory {

  @Resource(name = "referenceQueryServiceMap")
  private Map<String, ReferenceQueryService<?>> referenceQueryServiceMap;

  public ReferenceQueryService getQueryService(ReferenceTypeEnum referenceType) {
    if (null != referenceType) {
      return referenceQueryServiceMap.get(referenceType.toString());
    } else {
      return null;
    }
  }

}
