/*
 * Modified by: tolentf
 * Last updated: Nov 20, 2018 11:50:52 AM
 */
package com.caista.birapps.etis.domain.trs.utils.enums;

public enum TaxpayerClassificationTypeEnum {

  INDIVIDUAL("INDIVIDUAL"),

  NONINVIDIUAL("NON-INDIVIDUAL");

  private String code;

  private TaxpayerClassificationTypeEnum(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

}
