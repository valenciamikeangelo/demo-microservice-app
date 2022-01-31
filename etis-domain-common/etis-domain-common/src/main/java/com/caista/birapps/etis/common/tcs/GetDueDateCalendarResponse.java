package com.caista.birapps.etis.common.tcs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class GetDueDateCalendarResponse {

	private String returnPeriod;
	private String legislativeDueDate;
	private String systemRunDate;

	public String getReturnPeriod() {
		return returnPeriod;
	}

	public void setReturnPeriod(String returnPeriod) {
		this.returnPeriod = returnPeriod;
	}

	public String getLegislativeDueDate() {
		return legislativeDueDate;
	}

	public void setLegislativeDueDate(String legislativeDueDate) {
		this.legislativeDueDate = legislativeDueDate;
	}

	public String getSystemRunDate() {
		return systemRunDate;
	}

	public void setSystemRunDate(String systemRunDate) {
		this.systemRunDate = systemRunDate;
	}

	public GetDueDateCalendarResponse(String returnPeriod, String legislativeDueDate, String systemRunDate) {
		super();
		this.returnPeriod = returnPeriod;
		this.legislativeDueDate = legislativeDueDate;
		this.systemRunDate = systemRunDate;
	}

	public GetDueDateCalendarResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "GetDueDateCalendarResponse [returnPeriod=" + returnPeriod + ", legislativeDueDate=" + legislativeDueDate
				+ "]";
	}

}
