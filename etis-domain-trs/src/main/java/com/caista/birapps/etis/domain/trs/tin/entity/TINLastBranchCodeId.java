/*
 * Modified by: fuentem
 * Last updated: Oct 16, 2018 2:15:33 PM
 */
package com.caista.birapps.etis.domain.trs.tin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The Class TINLastBranchCode.
 */
@Embeddable
public class TINLastBranchCodeId implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "TIN", columnDefinition = "VARCHAR2(9 BYTE)", nullable = false)
	private String tin;

	@Column(name = "BRANCH_TYPE", columnDefinition = "VARCHAR2(15 BYTE)", nullable = false)
	private String type;

	public TINLastBranchCodeId() {
		super();
	}

	public TINLastBranchCodeId(String tin, String type) {
		super();
		this.tin = tin;
		this.type = type;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
