/*
 * Modified by: fuentem
 * Last updated: Nov 10, 2018 10:39:14 AM
 */
package com.caista.birapps.etis.domain.trs.utils.enums;

public enum ReferenceCodesEnum {

  COMPTTYPE_EMAIL("Email"), TAXPAYERCLASSIFICATION_INDIVIDUAL(
      "INDIVIDUAL"), TAXPAYERCLASSIFICATION_NON_INDIVIDUAL("NON-INDIVIDUAL");

  private String id;

  private ReferenceCodesEnum(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

}
