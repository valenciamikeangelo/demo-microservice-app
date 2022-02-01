package com.caista.birapps.etis.domain.trs.integration;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class TCSGetDueDateCalendarParam {

	private String filingMode;
	private Long officeId;
	private String formType;
	private String accountingPeriod;
	private Date registrationDate;

	public String getFilingMode() {
		return filingMode;
	}

	public void setFilingMode(String filingMode) {
		this.filingMode = filingMode;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getAccountingPeriod() {
		return accountingPeriod;
	}

	public void setAccountingPeriod(String accountingPeriod) {
		this.accountingPeriod = accountingPeriod;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public TCSGetDueDateCalendarParam(String filingMode, Long officeId, String formType, String accountingPeriod,
			Date registrationDate) {
		super();
		this.filingMode = filingMode;
		this.officeId = officeId;
		this.formType = formType;
		this.accountingPeriod = accountingPeriod;
		this.registrationDate = registrationDate;
	}

	public TCSGetDueDateCalendarParam() {
		super();
	}

	@Override
	public String toString() {
		return "TCSGetDueDateCalendarParam [filingMode=" + filingMode + ", officeId=" + officeId + ", formType="
				+ formType + ", accountingPeriod=" + accountingPeriod + ", registrationDate=" + registrationDate + "]";
	}

}
