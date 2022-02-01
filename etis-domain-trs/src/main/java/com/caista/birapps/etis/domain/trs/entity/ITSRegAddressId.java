package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ITSRegAddressId implements Serializable{

	private static final long serialVersionUID = 1L;
		
	@Column(name = "TIN", length = 9, columnDefinition = "VARCHAR2(9 BYTE)", nullable = false)
	private String tin;
	
	@Column(name = "BRANCH_CODE", columnDefinition = "VARCHAR2(6 BYTE)", nullable = false)
	private String branchCode;
	
	@Column(name = "CODE", columnDefinition = "VARCHAR2(1 BYTE)", nullable = false)
	private String code;
	
	@Column(name = "STATUS_CODE", columnDefinition = "VARCHAR2(1 BYTE)", nullable = false)
	private String statusCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
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

	public ITSRegAddressId(String tin, String branchCode, String code, String statusCode) {
		super();
		this.tin = tin;
		this.branchCode = branchCode;
		this.code = code;
		this.statusCode = statusCode;
	}

	public ITSRegAddressId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
