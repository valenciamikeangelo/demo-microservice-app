/*
  * Modified by: obregoj
  * Last updated: Oct 9, 2018 4:05:16 PM
  */
package com.caista.birapps.etis.domain.sysad.util;

import java.io.Serializable;
import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.*;

public class ManageUserAssignment implements Serializable, SysadAudit {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private StaffPrimaryOffice primaryOffice;

  private List<StaffSecondaryOffice> staffSecondaryOffices;

  private List<StaffCaseAccess> staffCaseAccess;



  public ManageUserAssignment() {
    super();
  }



  public ManageUserAssignment(StaffPrimaryOffice primaryOffice,
      List<StaffSecondaryOffice> staffSecondaryOffices, List<StaffCaseAccess> staffCaseAccess) {
    super();
    this.primaryOffice = primaryOffice;
    this.staffSecondaryOffices = staffSecondaryOffices;
    this.staffCaseAccess = staffCaseAccess;
  }



  public StaffPrimaryOffice getPrimaryOffice() {
    return primaryOffice;
  }



  public void setPrimaryOffice(StaffPrimaryOffice primaryOffice) {
    this.primaryOffice = primaryOffice;
  }



  public List<StaffSecondaryOffice> getStaffSecondaryOffices() {
    return staffSecondaryOffices;
  }



  public void setStaffSecondaryOffices(List<StaffSecondaryOffice> staffSecondaryOffices) {
    this.staffSecondaryOffices = staffSecondaryOffices;
  }



  public List<StaffCaseAccess> getStaffCaseAccess() {
    return staffCaseAccess;
  }



  public void setStaffCaseAccess(List<StaffCaseAccess> staffCaseAccess) {
    this.staffCaseAccess = staffCaseAccess;
  }



  public static long getSerialversionuid() {
    return serialVersionUID;
  }



  @Override
  public String toString() {
    return "ManageUserAssignment [primaryOffice=" + primaryOffice + ", staffSecondaryOffices="
        + staffSecondaryOffices + ", staffCaseAccess=" + staffCaseAccess + "]";
  }



  @Override
  public Long getId() {
    return primaryOffice.getId();
  }



  @Override
  public String getCreatedBy() {
    return primaryOffice.getCreatedBy();
  }



  @Override
  public String getUpdatedBy() {
    return primaryOffice.getUpdatedBy();
  }

}
