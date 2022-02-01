package com.caista.birapps.etis.domain.trs.utils.enums;

public enum IntegrationPointEnum {

	DEREGISTER_COMPLIANCE("DEREGISTER COMPLIANCE MATRIX RETURN"), MANAGE_ACCOUNTING_PERIOD(
			"MANAGE ACCOUNTING PERIOD"), UPDATE_TAXPAYER("UPDATE TAXPAYER"), REREGISTER_REACTIVATE("REREG/REACTIVATE");

	private String id;

	IntegrationPointEnum(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
