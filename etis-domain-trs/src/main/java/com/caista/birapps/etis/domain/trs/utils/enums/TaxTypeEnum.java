/*
 * Modified by: fuentem
 * Last updated: Nov 24, 2018 3:39:17 PM
 */
package com.caista.birapps.etis.domain.trs.utils.enums;

public enum TaxTypeEnum {

  NOT_TAXABLE("NT"), REGISTRATION_FEE("REG-FEE"), INDIVIDUAL_INCOME_TAX("IIT"), CORPORATE_INCOME_TAX("CIT");

  private String id;

  TaxTypeEnum(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
