/*
 * Modified by: romerov
 * Last updated: Feb 15, 2019 5:10:16 PM
 */
package com.caista.birapps.etis.domain.trs.utils.enums;

public enum AlphanumericTaxCodeEnum {

  FINES_PEN_REG_FEE("MC180");

  private String id;

  AlphanumericTaxCodeEnum(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

}
