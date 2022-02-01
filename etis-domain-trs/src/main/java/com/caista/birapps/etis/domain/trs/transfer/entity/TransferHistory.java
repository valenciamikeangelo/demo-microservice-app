/*
 * Modified by: santojo
 * Last updated: Apr 29, 2019 4:16:33 PM
 */
package com.caista.birapps.etis.domain.trs.transfer.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "TAXPAYER_TRANSFER_HISTORY", uniqueConstraints = @UniqueConstraint(columnNames = {
		"TAXPAYER_TRANSFER_HISTORY_ID", "TAXPAYER_ID" }, name = "UC_TAXPAYER_TRANSFER_HISTORY_01"))
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferHistory implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "TAXPAYER_TRANSFER_HISTORY_SEQUENCESTYLEGENERATOR")
	@GenericGenerator(name = "TAXPAYER_TRANSFER_HISTORY_SEQUENCESTYLEGENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_TRANSFER_HISTORY"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "TAXPAYER_TRANSFER_HISTORY_ID", unique = true, nullable = false)
	private Long id;

	/** The taxpayer id. */
	@Column(name = "TAXPAYER_ID")
	private Long taxpayerId;

	@Column(name = "NEW_OFFICE_ID")
	private Long newOfficeId;

	@Column(name = "NEW_OFFICE_CODE", columnDefinition = "VARCHAR2(20 BYTE)")
	private String newOfficeCode;

	@Column(name = "NEW_OFFICE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String newOfficeDescription;

	@Column(name = "OLD_OFFICE_ID")
	private Long oldOfficeId;

	@Column(name = "OLD_OFFICE_CODE", columnDefinition = "VARCHAR2(20 BYTE)")
	private String oldOfficeCode;

	@Column(name = "OLD_OFFICE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String oldOfficeDescription;

	@Column(name = "TRANSFER_COMMITMENT_FORM", columnDefinition = "VARCHAR2(150 BYTE)")
	private String transferCommitmentFormRefNo;

	@Column(name = "ACCESS_EXPIRE_DATE", columnDefinition = "DATE")
	private Date accessExpireDate;

	@Column(name = "REASON_ID", columnDefinition = "VARCHAR2(30 BYTE)")
	private String reasonId;

	@Column(name = "REASON_DESCRIPTION", columnDefinition = "VARCHAR2(170 BYTE)")
	private String reasonDescription;

	@Column(name = "TRANSFER_DATE")
	private Date transferDate;

	@Column(name = "TRANSFER_BY", columnDefinition = "VARCHAR2(50 BYTE)")
	private String transferBy;

	@Column(name = "REMARKS", columnDefinition = "VARCHAR2(500 BYTE)")
	private String remarks;

	@Column(name = "EVENT", columnDefinition = "VARCHAR2(100 BYTE)")
	private String event;

	public TransferHistory() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTaxpayerId() {
		return taxpayerId;
	}

	public void setTaxpayerId(Long taxpayerId) {
		this.taxpayerId = taxpayerId;
	}

	public Long getNewOfficeId() {
		return newOfficeId;
	}

	public void setNewOfficeId(Long newOfficeId) {
		this.newOfficeId = newOfficeId;
	}

	public String getNewOfficeCode() {
		return newOfficeCode;
	}

	public void setNewOfficeCode(String newOfficeCode) {
		this.newOfficeCode = newOfficeCode;
	}

	public String getNewOfficeDescription() {
		return newOfficeDescription;
	}

	public void setNewOfficeDescription(String newOfficeDescription) {
		this.newOfficeDescription = newOfficeDescription;
	}

	public Long getOldOfficeId() {
		return oldOfficeId;
	}

	public void setOldOfficeId(Long oldOfficeId) {
		this.oldOfficeId = oldOfficeId;
	}

	public String getOldOfficeCode() {
		return oldOfficeCode;
	}

	public void setOldOfficeCode(String oldOfficeCode) {
		this.oldOfficeCode = oldOfficeCode;
	}

	public String getOldOfficeDescription() {
		return oldOfficeDescription;
	}

	public void setOldOfficeDescription(String oldOfficeDescription) {
		this.oldOfficeDescription = oldOfficeDescription;
	}

	public String getTransferCommitmentFormRefNo() {
		return transferCommitmentFormRefNo;
	}

	public void setTransferCommitmentFormRefNo(String transferCommitmentFormRefNo) {
		this.transferCommitmentFormRefNo = transferCommitmentFormRefNo;
	}

	public Date getAccessExpireDate() {
		return accessExpireDate;
	}

	public void setAccessExpireDate(Date accessExpireDate) {
		this.accessExpireDate = accessExpireDate;
	}

	public String getReasonId() {
		return reasonId;
	}

	public void setReasonId(String reasonId) {
		this.reasonId = reasonId;
	}

	public String getReasonDescription() {
		return reasonDescription;
	}

	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public String getTransferBy() {
		return transferBy;
	}

	public void setTransferBy(String transferBy) {
		this.transferBy = transferBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

  @Override
  public String toString() {
    return "TransferHistory [id=" + id + ", taxpayerId=" + taxpayerId + ", newOfficeId="
        + newOfficeId + ", newOfficeCode=" + newOfficeCode + ", newOfficeDescription="
        + newOfficeDescription + ", oldOfficeId=" + oldOfficeId + ", oldOfficeCode=" + oldOfficeCode
        + ", oldOfficeDescription=" + oldOfficeDescription + ", transferCommitmentFormRefNo="
        + transferCommitmentFormRefNo + ", accessExpireDate=" + accessExpireDate + ", reasonId="
        + reasonId + ", reasonDescription=" + reasonDescription + ", transferDate=" + transferDate
        + ", transferBy=" + transferBy + ", remarks=" + remarks + ", event=" + event + "]";
  }

}
