/*
  * Modified by: logronj
  * Last updated: 08 5, 20 11:05:19 PM
  */
package com.caista.birapps.etis.domain.sysad.util;

import java.io.Serializable;
import java.util.List;
import com.caista.birapps.etis.domain.object.User;
import com.caista.birapps.etis.domain.sysad.entity.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Office.
 */
public class ManageOffice implements Serializable, SysadAudit {

  private static final long serialVersionUID = 1L;

  /** The office details. */
  private Office officeDetails;

  private OfficeAddress officeAddress;

  private List<OfficeContactDetail> officeContactDetails;

  /** The office coverages. */
  private List<OfficeCoverage> officeCoverages;

  /** The office printers. */
  private List<OfficePrinter> officePrinters;

  /** The office subordinates. */
  private List<OfficeSubordinate> officeSubordinates;

  /** The office serial number RR. */
  private List<OfficeSerialNumberMain> officeSerialNumberMain;

  /** The office serial number RDO. */
  private List<OfficeSerialNumberDistributed> officeSerialNumberDistributed;

  private List<User> officeStaffs;

  private List<OfficeTvnNumberMain> officeTvnNumberMain;

  private List<OfficeTvnNumberDistributed> officeTvnNumberDistributed;

  private List<OfficeTccSerialNumberMain> tccSerialNumberMain;

  private List<OfficeTccSerialNumberDistributed> tccSerialNumberDistributed;

  private List<OfficeTdmSerialNumberMain> tdmSerialNumberMain;

  private List<OfficeTdmSerialNumberDistributed> tdmSerialNumberDistributed;

  /**
   * Instantiates a new office.
   */
  public ManageOffice() {
    super();
  }


  public Office getOfficeDetails() {
    return officeDetails;
  }

  public void setOfficeDetails(Office officeDetails) {
    this.officeDetails = officeDetails;
  }

  public OfficeAddress getOfficeAddress() {
    return officeAddress;
  }

  public void setOfficeAddress(OfficeAddress officeAddress) {
    this.officeAddress = officeAddress;
  }

  public List<OfficeContactDetail> getOfficeContactDetails() {
    return officeContactDetails;
  }


  public void setOfficeContactDetails(List<OfficeContactDetail> officeContactDetails) {
    this.officeContactDetails = officeContactDetails;
  }


  public List<OfficeCoverage> getOfficeCoverages() {
    return officeCoverages;
  }

  public void setOfficeCoverages(List<OfficeCoverage> officeCoverages) {
    this.officeCoverages = officeCoverages;
  }

  public List<OfficePrinter> getOfficePrinters() {
    return officePrinters;
  }

  public void setOfficePrinters(List<OfficePrinter> officePrinters) {
    this.officePrinters = officePrinters;
  }

  public List<OfficeSubordinate> getOfficeSubordinates() {
    return officeSubordinates;
  }

  public void setOfficeSubordinates(List<OfficeSubordinate> officeSubordinates) {
    this.officeSubordinates = officeSubordinates;
  }

  public List<OfficeSerialNumberMain> getOfficeSerialNumberMain() {
    return officeSerialNumberMain;
  }


  public void setOfficeSerialNumberMain(List<OfficeSerialNumberMain> officeSerialNumberMain) {
    this.officeSerialNumberMain = officeSerialNumberMain;
  }


  public List<OfficeSerialNumberDistributed> getOfficeSerialNumberDistributed() {
    return officeSerialNumberDistributed;
  }


  public void setOfficeSerialNumberDistributed(
      List<OfficeSerialNumberDistributed> officeSerialNumberDistributed) {
    this.officeSerialNumberDistributed = officeSerialNumberDistributed;
  }


  public static long getSerialversionuid() {
    return serialVersionUID;
  }


  public List<User> getOfficeStaffs() {
    return officeStaffs;
  }

  public void setOfficeStaffs(List<User> officeStaffs) {
    this.officeStaffs = officeStaffs;
  }


  public List<OfficeTvnNumberMain> getOfficeTvnNumberMain() {
    return officeTvnNumberMain;
  }


  public void setOfficeTvnNumberMain(List<OfficeTvnNumberMain> officeTvnNumberMain) {
    this.officeTvnNumberMain = officeTvnNumberMain;
  }


  public List<OfficeTccSerialNumberMain> getTccSerialNumberMain() {
    return tccSerialNumberMain;
  }


  public void setTccSerialNumberMain(List<OfficeTccSerialNumberMain> tccSerialNumberMain) {
    this.tccSerialNumberMain = tccSerialNumberMain;
  }



  public List<OfficeTccSerialNumberDistributed> getTccSerialNumberDistributed() {
    return tccSerialNumberDistributed;
  }


  public void setTccSerialNumberDistributed(
      List<OfficeTccSerialNumberDistributed> tccSerialNumberDistributed) {
    this.tccSerialNumberDistributed = tccSerialNumberDistributed;
  }


  public List<OfficeTdmSerialNumberMain> getTdmSerialNumberMain() {
    return tdmSerialNumberMain;
  }


  public void setTdmSerialNumberMain(List<OfficeTdmSerialNumberMain> tdmSerialNumberMain) {
    this.tdmSerialNumberMain = tdmSerialNumberMain;
  }


  public List<OfficeTvnNumberDistributed> getOfficeTvnNumberDistributed() {
    return officeTvnNumberDistributed;
  }


  public void setOfficeTvnNumberDistributed(
      List<OfficeTvnNumberDistributed> officeTvnNumberDistributed) {
    this.officeTvnNumberDistributed = officeTvnNumberDistributed;
  }


  public List<OfficeTdmSerialNumberDistributed> getTdmSerialNumberDistributed() {
    return tdmSerialNumberDistributed;
  }


  public void setTdmSerialNumberDistributed(
      List<OfficeTdmSerialNumberDistributed> tdmSerialNumberDistributed) {
    this.tdmSerialNumberDistributed = tdmSerialNumberDistributed;
  }


  @Override
  public Long getId() {
    return officeDetails.getId();
  }


  @Override
  public String getCreatedBy() {
    return officeDetails.getCreatedBy();
  }


  @Override
  public String getUpdatedBy() {
    return officeDetails.getUpdatedBy();
  }


}
