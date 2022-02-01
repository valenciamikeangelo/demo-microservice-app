/*
  * Modified by: logronj
  * Last updated: 08 7, 20 6:31:19 PM
  */
package com.caista.birapps.etis.domain.sysad.util;

public class CommonSerialNumberDTO {

  private SerialNumberTypeEnum serialNumberType;
  private Long serialNumber;
  private Integer year;
  private Long officeId;
  private Object result;

  private Long rangeFrom;
  private Long rangeTo;

  private String sectionId;
  private String officeLtsGroupId;

  private String officeGroup;
  private String officeService;
  private String officeDivision;

  public SerialNumberTypeEnum getSerialNumberType() {
    return serialNumberType;
  }

  public void setSerialNumberType(SerialNumberTypeEnum serialNumberType) {
    this.serialNumberType = serialNumberType;
  }

  public Long getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(Long serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Long getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Long officeId) {
    this.officeId = officeId;
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }

  public Long getRangeFrom() {
    return rangeFrom;
  }

  public void setRangeFrom(Long rangeFrom) {
    this.rangeFrom = rangeFrom;
  }

  public Long getRangeTo() {
    return rangeTo;
  }

  public void setRangeTo(Long rangeTo) {
    this.rangeTo = rangeTo;
  }

  public String getSectionId() {
    return sectionId;
  }

  public void setSectionId(String sectionId) {
    this.sectionId = sectionId;
  }

  public String getOfficeLtsGroupId() {
    return officeLtsGroupId;
  }

  public void setOfficeLtsGroupId(String officeLtsGroupId) {
    this.officeLtsGroupId = officeLtsGroupId;
  }

  public String getOfficeGroup() {
    return officeGroup;
  }

  public void setOfficeGroup(String officeGroup) {
    this.officeGroup = officeGroup;
  }

  public String getOfficeDivision() {
    return officeDivision;
  }

  public void setOfficeDivision(String officeDivision) {
    this.officeDivision = officeDivision;
  }

  public String getOfficeService() {
    return officeService;
  }

  public void setOfficeService(String officeService) {
    this.officeService = officeService;
  }



}
