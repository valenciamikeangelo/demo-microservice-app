/*
 * Modified by: santojo
 * Last updated: Apr 29, 2019 4:17:58 PM
 */
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

/**
 * The Class DocumentLocatorNumberHistory.
 */
@Entity
@Table(name = "TAXPAYER_DOCUMENT_LOCATOR_NUMBER_HISTORY", uniqueConstraints = @UniqueConstraint(columnNames = {
		"DOCUMENT_LOCATOR_NUMBER_HISTORY_ID", "TIN",
		"BRANCH_CODE" }, name = "UC_TAXPAYER_DOCUMENT_LOCATOR_NUMBER_HISTORY_01"))
public class DocumentLocatorNumberHistory implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "TAXPAYER_DOCUMENT_LOCATOR_NUMBER_HISTORY_SEQUENCESTYLEGENERATOR")
	@GenericGenerator(name = "TAXPAYER_DOCUMENT_LOCATOR_NUMBER_HISTORY_SEQUENCESTYLEGENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_DOCUMENT_LOCATOR_NUMBER_HISTORY"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "DOCUMENT_LOCATOR_NUMBER_HISTORY_ID", unique = true, nullable = false)
	private Long id;

	/** The tin. */
	@Column(name = "TIN", nullable = false, length = 9, columnDefinition = "VARCHAR2(9 BYTE)")
	private String tin;

	/** The branch code. */
	@Column(name = "BRANCH_CODE", nullable = false, length = 6, columnDefinition = "VARCHAR2(6 BYTE)")
	private String branchCode;

	/** The dln. */
	@Column(name = "DOCUMENT_LOCATOR_NUMBER", columnDefinition = "VARCHAR2(14 BYTE)")
	private String dln;

	/** The reason id. */
	@Column(name = "REASON_ID", columnDefinition = "VARCHAR2(30 BYTE)")
	private String reasonId;

	/** The reason description. */
	@Column(name = "REASON_DESCRIPTION", columnDefinition = "VARCHAR2(170 BYTE)")
	private String reasonDescription;

	/** The remarks. */
	@Column(name = "REMARKS", columnDefinition = "VARCHAR2(500 BYTE)")
	private String remarks;

	/** The event. */
	@Column(name = "EVENT", columnDefinition = "VARCHAR2(100 BYTE)")
	private String event;

	/** The date dln generated. */
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_DLN_GENERATED", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE")
	private Date dateDlnGenerated;

	/** The created by. */
	@Column(name = "CREATED_BY", nullable = false, columnDefinition = "VARCHAR2(50 BYTE)")
	private String createdBy;

	/** The created date. */
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE", nullable = false)
	private Date createdDate;

	/**
	 * Instantiates a new document locator number history.
	 */
	public DocumentLocatorNumberHistory() {
		super();
	}

	/**
	 * Instantiates a new document locator number history.
	 *
	 * @param id
	 *            the id
	 * @param tin
	 *            the tin
	 * @param branchCode
	 *            the branch code
	 * @param dln
	 *            the dln
	 * @param reasonId
	 *            the reason id
	 * @param reasonDescription
	 *            the reason description
	 * @param remarks
	 *            the remarks
	 * @param event
	 *            the event
	 * @param dateDlnGenerated
	 *            the date dln generated
	 * @param createdBy
	 *            the created by
	 * @param createdDate
	 *            the created date
	 */
	public DocumentLocatorNumberHistory(Long id, String tin, String branchCode, String dln, String reasonId,
			String reasonDescription, String remarks, String event, Date dateDlnGenerated, String createdBy,
			Date createdDate) {
		super();
		this.id = id;
		this.tin = tin;
		this.branchCode = branchCode;
		this.dln = dln;
		this.reasonId = reasonId;
		this.reasonDescription = reasonDescription;
		this.remarks = remarks;
		this.event = event;
		this.dateDlnGenerated = dateDlnGenerated;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDln() {
		return dln;
	}

	public void setDln(String dln) {
		this.dln = dln;
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

	public Date getDateDlnGenerated() {
		return dateDlnGenerated;
	}

	public void setDateDlnGenerated(Date dateDlnGenerated) {
		this.dateDlnGenerated = dateDlnGenerated;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

  @Override
  public String toString() {
    return "DocumentLocatorNumberHistory [id=" + id + ", tin=" + tin + ", branchCode=" + branchCode
        + ", dln=" + dln + ", reasonId=" + reasonId + ", reasonDescription=" + reasonDescription
        + ", remarks=" + remarks + ", event=" + event + ", dateDlnGenerated=" + dateDlnGenerated
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
  }

}
