package com.caista.birapps.etis.domain.audit.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "TAS_AUDIT")
public class TaxpayerAccountingSystemAudit implements Serializable {

	private static final long serialVersionUID = 3241647787574460278L;

	@Id
	@Column(name = "REF_AUD_ID")
	private String refAuditId;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "TRANSACTION_ID")
	private String transactionId;

	@Column(name = "NEW_VALUE", nullable = false)
	@Lob
	private byte[] newValue;
	
	@Column(name = "OLD_VALUE", nullable = false)
	@Lob
	private byte[] oldValue;
	
	public TaxpayerAccountingSystemAudit() {
		super();
		this.refAuditId = UUID.randomUUID().toString();
	}

	public TaxpayerAccountingSystemAudit(String refAuditId, String description, String transactionId, byte[] newValue,
			byte[] oldValue) {
		super();
		this.refAuditId = refAuditId;
		this.description = description;
		this.transactionId = transactionId;
		this.newValue = newValue;
		this.oldValue = oldValue;
	}

	public String getRefAuditId() {
		return refAuditId;
	}

	public void setRefAuditId(String refAuditId) {
		this.refAuditId = refAuditId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public byte[] getNewValue() {
		return newValue;
	}

	public void setNewValue(byte[] newValue) {
		this.newValue = newValue;
	}

	public byte[] getOldValue() {
		return oldValue;
	}

	public void setOldValue(byte[] oldValue) {
		this.oldValue = oldValue;
	}

	@Override
	public String toString() {
		return "TaxpayerAccountingSystemAudit [refAuditId=" + refAuditId + ", description=" + description
				+ ", transactionId=" + transactionId + ", newValue=" + Arrays.toString(newValue) + ", oldValue="
				+ Arrays.toString(oldValue) + "]";
	}

}
