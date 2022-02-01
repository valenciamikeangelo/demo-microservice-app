/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 2:50:09 PM
  */
package com.caista.birapps.etis.domain.sysad.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCaseEvent;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCaseType;
import com.caista.birapps.etis.domain.sysad.util.SysadAudit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// TODO: Auto-generated Javadoc
/**
 * The Class CaseOutlineHeader.
 */
@Entity
@Table(name = "CASE_OUTLINE_HEADER", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"CASE_OUTLINE_HEADER_CODE" }, name = "UC_CASE_OUTLINE_HEADER_01") }, indexes = {
				@Index(columnList = "CASE_OUTLINE_HEADER_CODE", name = "IDX_CASE_OUTLINE_HEADER_01") })
@JsonInclude(Include.NON_NULL)
public class CaseOutlineHeader implements Serializable, SysadAudit {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1415035862631529081L;

	@Id
	@GeneratedValue(generator = "CASE_OUTLINE_HEADER_SequenceStyleGenerator")
	@GenericGenerator(name = "CASE_OUTLINE_HEADER_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_CASE_OUTLINE_HEADER"),
			@Parameter(name = "optimizer", value = "hilo"), @Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "CASE_OUTLINE_HEADER_ID")
	private Long id;

	@GenericGenerator(name = "CASE_OUTLINE_DETAILS_SequenceStyleGenerator", 
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", 
			parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_CASE_OUTLINE_DETAILS"),
			@Parameter(name = "optimizer", value = "hilo"), @Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })

	@Cascade(CascadeType.SAVE_UPDATE)
	@ManyToMany
	@JoinTable(name = "CASE_OUTLINE_DETAILS", 
		joinColumns = @JoinColumn(name = "CASE_OUTLINE_HEADER_ID", 
			referencedColumnName = "CASE_OUTLINE_HEADER_ID", 
			foreignKey = @ForeignKey(
					name = "FK_CASE_OUTLINE_HEADER__CASE_OUTLINE_DETAILS_01")), 
		inverseJoinColumns = @JoinColumn(name = "CASE_EVENT_ID", 
			referencedColumnName = "CASE_EVENT_ID", 
			foreignKey = @ForeignKey(name = "FK_MAIN_CASE_EVENT__CASE_OUTLINE_DETAILS_01")), 
		uniqueConstraints = {
			@UniqueConstraint(columnNames = { "CASE_OUTLINE_HEADER_ID",
					"CASE_EVENT_ID" }, name = "UC_CASE_OUTLINE_HEADER_02") })
	private List<MaintCaseEvent> caseOutlineDetails;

	/** The code. */
	@Column(name = "CASE_OUTLINE_HEADER_CODE", length = 20, columnDefinition = "VARCHAR2(20)")
	private String code;

	@ManyToOne
	@JoinColumn(name = "CASE_TYPE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_MAIN_CASE_TYPE__CASE_OUTLINE_HEADER_01"))
	private MaintCaseType caseType;

	/** The description. */
	@Column(name = "CASE_OUTLINE_HEADER_DESCRIPTION", length = 80, columnDefinition = "VARCHAR2(80)")
	private String description;

	/** The updated by. */
	@Column(name = "UPDATED_BY", length = 20, columnDefinition = "VARCHAR2(20)")
	private String updatedBy;

	/** The created by. */
	@Column(name = "CREATED_BY", length = 20, columnDefinition = "VARCHAR2(20)", updatable = false, nullable = false)
	private String createdBy;

	/** The updated date. */
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	/** The created date. */
	@Column(name = "CREATED_DATE", updatable = false, nullable = false)
	private Date createdDate;

	/** The expiry date. */
	@Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
	private Date expiryDate;

	/** The effective date. */
	@Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE")
	private Date effectiveDate;

	/**
	 * Instantiates a new case outline header.
	 */
	public CaseOutlineHeader() {
		super();
	}

	/**
	 * Instantiates a new case outline header.
	 *
	 * @param code
	 *            the code
	 * @param description
	 *            the description
	 */
	public CaseOutlineHeader(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public CaseOutlineHeader(Long id, List<MaintCaseEvent> caseOutlineDetails, String code, MaintCaseType caseType,
			String description, String updatedBy, String createdBy, Date updatedDate, Date createdDate, Date expiryDate,
			Date effectiveDate) {
		super();
		this.id = id;
		this.caseOutlineDetails = caseOutlineDetails;
		this.code = code;
		this.caseType = caseType;
		this.description = description;
		this.updatedBy = updatedBy;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.createdDate = createdDate;
		this.expiryDate = expiryDate;
		this.effectiveDate = effectiveDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MaintCaseEvent> getCaseOutlineDetails() {
		return caseOutlineDetails;
	}

	public void setCaseOutlineDetails(List<MaintCaseEvent> caseOutlineDetails) {
		this.caseOutlineDetails = caseOutlineDetails;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public MaintCaseType getCaseType() {
		return caseType;
	}

	public void setCaseType(MaintCaseType caseType) {
		this.caseType = caseType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CaseOutlineHeader [id=" + id + ", caseOutlineDetails=" + caseOutlineDetails + ", code=" + code
				+ ", caseType=" + caseType + ", description=" + description + ", updatedBy=" + updatedBy
				+ ", createdBy=" + createdBy + ", updatedDate=" + updatedDate + ", createdDate=" + createdDate
				+ ", expiryDate=" + expiryDate + ", effectiveDate=" + effectiveDate + "]";
	}

}
