/*
  * Modified by: logronj
  * Last updated: 08 7, 20 10:17:10 PM
  */
package com.caista.birapps.etis.domain.sysad.util;

import com.caista.birapps.etis.domain.sysad.entity.*;

@SuppressWarnings("rawtypes")
public enum SerialNumberTypeEnum {
  LA(OfficeSerialNumberDistributed.class, "officeSerialNumberMain"), TVN_NO(
      OfficeTvnNumberDistributed.class, "officeTvnNumberMain"), TVN_RDO(OfficeTvnNumberMain.class,
          null), TDM_NO(OfficeTdmSerialNumberDistributed.class, "tdmSerialNumberMain"), TDM_RDO(
              OfficeTdmSerialNumberMain.class,
              null), TCC(OfficeTccSerialNumberDistributed.class,
                  "tccSerialNumberMain");

  private Class domainClass;

  private String parentProperty;

  SerialNumberTypeEnum(Class domainClass, String parentProperty) {
    this.domainClass = domainClass;
    this.parentProperty = parentProperty;
  }

  public Class getDomainClass() {
    return domainClass;
  }

  public String getParentProperty() {
    return parentProperty;
  }
}
