/*
 * Modified by: fuentem
 * Last updated: Nov 25, 2018 4:56:19 PM
 */
package com.caista.birapps.etis.domain.trs.utils.enums;

public enum ContactTypeEnum {
  EMAIL("EMAIL"), PHONE("PHONE"), FAX("FAX"), MOBILE("MOBILE");

  private String id;

  ContactTypeEnum(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
