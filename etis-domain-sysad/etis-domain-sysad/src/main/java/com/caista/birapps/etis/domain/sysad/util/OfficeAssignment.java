/*
  * Modified by: obregoj
  * Last updated: Nov 5, 2018 6:19:02 PM
  */
package com.caista.birapps.etis.domain.sysad.util;

import java.io.Serializable;
import com.caista.birapps.etis.domain.sysad.entity.Office;

public class OfficeAssignment implements Serializable {

  private static final long serialVersionUID = 1L;

  private Office office;

  private boolean isPrimaryOffice;

  public OfficeAssignment() {
    super();
  }

  public OfficeAssignment(Office office, boolean isPrimaryOffice) {
    super();
    this.office = new Office(office.getId(), office.getCode(), office.getDescription(),
        office.getOfficeType(), office.getLargeTaxpayerOfficeFlag());
    this.isPrimaryOffice = isPrimaryOffice;
  }

  public Office getOffice() {
    return office;
  }

  public void setOffice(Office office) {
    this.office = new Office(office.getId(), office.getCode(), office.getDescription(),
        office.getOfficeType(), office.getLargeTaxpayerOfficeFlag());
  }

  public boolean isPrimaryOffice() {
    return isPrimaryOffice;
  }

  public void setPrimaryOffice(boolean isPrimaryOffice) {
    this.isPrimaryOffice = isPrimaryOffice;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "OfficeAssignment [office=" + office + ", isPrimaryOffice=" + isPrimaryOffice + "]";
  }

}
