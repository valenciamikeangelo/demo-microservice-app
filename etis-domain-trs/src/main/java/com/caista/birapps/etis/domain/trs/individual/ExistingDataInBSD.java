/*
  * Modified by: bacosal
  * Last updated: Sep 27, 2018 4:31:34 PM
  */
package com.caista.birapps.etis.domain.trs.individual;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ExistingDataInBSD {

  private Boolean registrationNumberDoNotExist;
  private Boolean singleBusinessNumberDoNotExist;

  public Boolean getRegistrationNumberDoNotExist() {
    return registrationNumberDoNotExist;
  }

  public void setRegistrationNumberDoNotExist(Boolean registrationNumberDoNotExist) {
    this.registrationNumberDoNotExist = registrationNumberDoNotExist;
  }

  public Boolean getSingleBusinessNumberDoNotExist() {
    return singleBusinessNumberDoNotExist;
  }

  public void setSingleBusinessNumberDoNotExist(Boolean singleBusinessNumberDoNotExist) {
    this.singleBusinessNumberDoNotExist = singleBusinessNumberDoNotExist;
  }

}
