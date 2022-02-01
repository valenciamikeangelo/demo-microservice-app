package com.caista.birapps.etis.domain.trs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class RegAddressesParams {
	
	private String tin;
	private String branchCode;
	private String addressType;
	
	public String getTin() {
		return tin;
	}
	
	public void setTin(String tin) {
		this.tin = tin;
	}
	
	public String getBranchCode() {
		return branchCode;
	}
	
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	public String getAddressType() {
		return addressType;
	}
	
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	
}
