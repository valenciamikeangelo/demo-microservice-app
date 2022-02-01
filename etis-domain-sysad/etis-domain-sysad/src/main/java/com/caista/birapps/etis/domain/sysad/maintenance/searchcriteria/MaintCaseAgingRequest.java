/*
  * Modified by: obregoj
  * Last updated: Nov 20, 2019 8:51:46 AM
  */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintCaseAgingRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String caseTypeId;


  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getCaseTypeId() {
    return caseTypeId;
  }

  public void setCaseTypeId(String caseTypeId) {
    this.caseTypeId = caseTypeId;
  }

  @Override
  public String toString() {
    return "MaintCaseTypeRequest [caseTypeId=" + caseTypeId + "]";
  }

}
