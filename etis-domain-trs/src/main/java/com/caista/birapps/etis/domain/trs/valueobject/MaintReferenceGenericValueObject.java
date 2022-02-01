/*
 * Modified by: fuentem
 * Last updated: Nov 24, 2018 1:17:41 PM
 */
package com.caista.birapps.etis.domain.trs.valueobject;

public class MaintReferenceGenericValueObject {

  private String id;
  private String code;
  private String description;
  private String filingFrequency;
  private Boolean displayInCOR;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getFilingFrequency() {
    return filingFrequency;
  }

  public void setFilingFrequency(String filingFrequency) {
    this.filingFrequency = filingFrequency;
  }

  public Boolean getDisplayInCOR() {
    return displayInCOR;
  }

  public void setDisplayInCOR(Boolean displayInCOR) {
    this.displayInCOR = displayInCOR;
  }

}
