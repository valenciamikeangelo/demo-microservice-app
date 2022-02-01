/*
  * Modified by: mercadk
  * Last updated: Mar 12, 2020 8:33:59 PM
  */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintNoticeGracePeriodRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String caseTypeId;
	private String notice;


  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getCaseTypeId() {
    return caseTypeId;
  }

  public void setCaseTypeId(String caseTypeId) {
    this.caseTypeId = caseTypeId;
  }

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	@Override
  public String toString() {
		return "MaintNoticeGracePeriodRequest [caseTypeId=" + caseTypeId + ", notice=" + notice + "]";
  }

}
