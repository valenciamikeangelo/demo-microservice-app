/*
 * Modified by: fuentem
 * Last updated: Nov 26, 2018 4:28:45 PM
 */
package com.caista.birapps.etis.domain.trs.utils.enums;

public enum AddressTypeEnum {
  PLACE_OF_BUSINESS("BUS"), DEFAULT("DEF"), RESIDENCE("RES"), FOREIGN_BUSINESS(
      "FBA"), LOCAL_BUSINESS(
          "LBA"), LOCAL_RESIDENCE("LRA"), MAILING("MAIL"), FOREIGN_RESIDENCE("FRA");

  private String type;

  AddressTypeEnum(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

}
