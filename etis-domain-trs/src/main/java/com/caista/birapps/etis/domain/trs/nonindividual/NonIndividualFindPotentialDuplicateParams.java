/*
  * Modified by: bacosal
  * Last updated: Jul 5, 2018 6:16:35 PM
  */
package com.caista.birapps.etis.domain.trs.nonindividual;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class NonIndividualFindPotentialDuplicateParams {

  private String tin;

  private String registeredName;

  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
  }

}
