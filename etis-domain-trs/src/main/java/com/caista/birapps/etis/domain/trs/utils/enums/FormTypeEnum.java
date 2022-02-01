/*
 * Modified by: fuentem
 * Last updated: Nov 29, 2018 10:01:31 AM
 */
package com.caista.birapps.etis.domain.trs.utils.enums;

public enum FormTypeEnum {

  PAYMENT_FORM_0605("0605"), QUARTERLY_PERCENTAGE_TAX_RETURN("2551Q");

  private String id;

  FormTypeEnum(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
