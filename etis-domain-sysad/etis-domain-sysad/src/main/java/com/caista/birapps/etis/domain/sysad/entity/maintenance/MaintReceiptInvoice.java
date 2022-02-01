/*
 * Modified by: santojo
 * Last updated: Feb 1, 2019 3:54:06 PM
 */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceReceiptInvoiceKind;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_RECEIPT_INVOICE", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "RECEIPT_INVOICE_CODE" }, name = "UC_MAIN_RECEIPT_INVOICE_01") }, indexes = {
				@Index(columnList = "RECEIPT_INVOICE_CODE", name = "IDX_MAIN_RECEIPT_INVOICE_01") })
@JsonInclude(Include.NON_NULL)

public class MaintReceiptInvoice implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@IrisIdGenerator(name = "RCPTINV", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS, uniqueProperty = "none")
	@Column(name = "RECEIPT_INVOICE_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
	private String id;

	@ManyToOne
	@JoinColumn(name = "RECEIPT_INVOICE_KIND_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_REF_RECEIPT_INVOICE_KIND__MAIN_RECEIPT_INVOICE_01"))
	private ReferenceReceiptInvoiceKind kindReceiptInvoice;

	@Column(name = "RECEIPT_INVOICE_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
	private String code;

	@Column(name = "RECEIPT_INVOICE_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(80)")
	private String description;

	@Column(name = "CREATED_BY", nullable = false, updatable = false, columnDefinition = "VARCHAR2(20)")
	private String createdBy;

	@Column(name = "CREATED_DATE", nullable = false, updatable = false)
	private Date createdDate;

	@Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
	private Date effectiveDate;

	@Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
	private Date expiryDate;

	@Transient
	private String status;

	public MaintReceiptInvoice() {
		super();
	}

	public MaintReceiptInvoice(String id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
	}

	public MaintReceiptInvoice(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public MaintReceiptInvoice(String id, String code, String description, String createdBy) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.createdBy = createdBy;
	}

	public MaintReceiptInvoice(String id, String code, String description, String createdBy, Date createdDate,
			String updatedBy, Date updatedDate, ReferenceReceiptInvoiceKind kindReceiptInvoice) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.kindReceiptInvoice = kindReceiptInvoice;
	}

	@Override
	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
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

	@Override
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public ReferenceReceiptInvoiceKind getKindReceiptInvoice() {
		return kindReceiptInvoice;
	}

	public void setKindReceiptInvoice(ReferenceReceiptInvoiceKind kindReceiptInvoice) {
		this.kindReceiptInvoice = kindReceiptInvoice;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MaintReceiptInvoice [id=" + id + ", kindReceiptInvoice=" + kindReceiptInvoice + ", code=" + code
				+ ", description=" + description + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", effectiveDate=" + effectiveDate
				+ ", expiryDate=" + expiryDate + "]";
	}

}
