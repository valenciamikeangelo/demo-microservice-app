/*
 * Modified by: romeror
 * Last updated: Dec 9, 2018 12:41:51 AM
 */
package com.caista.birapps.etis.domain.trs.tin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

/**
 * The Class TINLastBranchCode.
 */
@Entity
@Table(name = "REG_TAXPAYERS")
@Check(constraints = "BRANCH_TYPE IN ('BRANCH', 'FACILITY')")
public class TINLastBranchCode implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TINLastBranchCodeId id;

	/** The tin. */
	@Column(name = "LAST_BRANCH_SEQ", length = 5, nullable = false)
	private Integer lastBranchSeq;

	public TINLastBranchCode() {
		super();
	}

	public TINLastBranchCode(TINLastBranchCodeId id, Integer lastBranchSeq) {
		super();
		this.id = id;
		this.lastBranchSeq = lastBranchSeq;
	}

	public TINLastBranchCodeId getId() {
		return id;
	}

	public void setId(TINLastBranchCodeId id) {
		this.id = id;
	}

	public Integer getLastBranchSeq() {
		return lastBranchSeq;
	}

	public void setLastBranchSeq(Integer lastBranchSeq) {
		this.lastBranchSeq = lastBranchSeq;
	}

	@Override
	public String toString() {
		return "TINLastBranchCode [id=" + id + ", lastBranchSeq=" + lastBranchSeq + "]";
	}

}
