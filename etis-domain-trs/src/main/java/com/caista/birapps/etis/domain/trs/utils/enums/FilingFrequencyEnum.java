package com.caista.birapps.etis.domain.trs.utils.enums;

public enum FilingFrequencyEnum {

	ANNUALLY("ANNUALLY"), ANNUAL("ANNUAL"), QUARTERLY("QUARTERLY"), MONTHLY("MONTHLY");

	private String code;

	private FilingFrequencyEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
