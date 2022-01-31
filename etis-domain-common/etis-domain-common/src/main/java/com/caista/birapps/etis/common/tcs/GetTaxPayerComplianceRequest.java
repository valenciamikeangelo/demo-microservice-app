package com.caista.birapps.etis.common.tcs;

public class GetTaxPayerComplianceRequest {

	private Long taxPayerId;
	private String tin;
	private String branchCode;

	public Long getTaxPayerId() {
		return taxPayerId;
	}

	public void setTaxPayerId(Long taxPayerId) {
		this.taxPayerId = taxPayerId;
	}

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

	public GetTaxPayerComplianceRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetTaxPayerComplianceRequest(Long taxPayerId, String tin, String branchCode) {
		super();
		this.taxPayerId = taxPayerId;
		this.tin = tin;
		this.branchCode = branchCode;
	}

	@Override
	public String toString() {
		return "GetTaxPayerComplianceRequest [taxPayerId=" + taxPayerId + ", tin=" + tin + ", branchCode=" + branchCode
				+ "]";
	}

}
