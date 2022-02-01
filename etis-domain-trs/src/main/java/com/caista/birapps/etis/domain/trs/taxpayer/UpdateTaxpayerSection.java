/*
  * Modified by: romerov
  * Last updated: 02 28, 20 6:51:39 PM
*/
package com.caista.birapps.etis.domain.trs.taxpayer;

import java.util.List;

public class UpdateTaxpayerSection {

  private String section;

  private List<Integer> id;

  public String getSection() {
    return section;
  }

  public void setSection(String section) {
    this.section = section;
  }

  public List<Integer> getId() {
    return id;
  }

  public void setId(List<Integer> id) {
    this.id = id;
  }
}
