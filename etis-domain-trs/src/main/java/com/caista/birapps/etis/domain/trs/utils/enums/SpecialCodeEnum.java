/*
 * Modified by: fuentem
 * Last updated: Nov 24, 2018 6:09:07 PM
 */
package com.caista.birapps.etis.domain.trs.utils.enums;

public enum SpecialCodeEnum {

  INCOME_TAX_RATE("8%ITR"), ACCREDITED_PRINTER("PRNTR");

  private String id;

  SpecialCodeEnum(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

}
