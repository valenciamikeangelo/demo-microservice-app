package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTasAdjustmentType;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_TAS_ADJUSTMENT_REASON", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"TAS_ADJUSTMENT_REASON_CODE" }, name = "UC_MAIN_TAS_ADJUSTMENT_REASON_01") }, indexes = {
				@Index(columnList = "TAS_ADJUSTMENT_REASON_CODE", name = "IDX_MAIN_TAS_ADJUSTMENT_REASON_01") })
@JsonInclude(Include.NON_NULL)
public class MaintTasAdjustmentReason implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@IrisIdGenerator(name = "TAR", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS, uniqueProperty = "none")
	@Column(name = "TAS_ADJUSTMENT_REASON_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
	private String id;

	@Column(name = "TAS_ADJUSTMENT_REASON_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
	private String code;

	@Column(name = "TAS_ADJUSTMENT_REASON_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(80)")
	private String description;

	@ManyToOne
	@JoinColumn(name = "TAS_ADJUSTMENT_TYPE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_REF_TAS_ADJUSTMENT_TYPE__MAIN_TAS_ADJUSTMENT_REASON_01"))
	private ReferenceTasAdjustmentType tasAdjustmentType;

	@Column(name = "DEBIT_CREDIT", nullable = false, columnDefinition = "VARCHAR2(1)")
	private String debitCredit;

	@Column(name = "AMT_VALUE", nullable = false, columnDefinition = "VARCHAR2(1)")
	private String amountValue;

	@Column(name = "APPROVAL", columnDefinition = "NUMBER(1,0) default '1'", nullable = false)
	private boolean isApproval;

	@Column(name = "EDITABLE_AMT", columnDefinition = "NUMBER(1,0) default '1'", nullable = false)
	private boolean isEditableAmount;

	@Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
	private Date effectiveDate;

	@Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
	private Date expiryDate;

	@Column(name = "CREATED_BY", nullable = false, updatable = false, columnDefinition = "VARCHAR2(20)")
	private String createdBy;

	@Column(name = "CREATED_DATE", nullable = false, updatable = false)
	private Date createdDate;

	@Column(name = "BASIC_TAX", columnDefinition = "NUMBER(1,0) default '1'", nullable = false)
	private boolean basicTax;

	@Column(name = "SURCHARGE", columnDefinition = "NUMBER(1,0) default '1'", nullable = false)
	private boolean surcharge;

	@Column(name = "INTEREST", columnDefinition = "NUMBER(1,0) default '1'", nullable = false)
	private boolean interest;

	@Column(name = "COMPROMISE_PENALTY", columnDefinition = "NUMBER(1,0) default '1'", nullable = false)
	private boolean compromisePenalty;

	@Column(name = "AMOUNT_PAID", columnDefinition = "NUMBER(1,0) default '1'", nullable = false)
	private boolean amountPaid;

	@Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	public MaintTasAdjustmentReason() {
		super();
	}

	public MaintTasAdjustmentReason(String id, String code, String description, String debitCredit, String amountValue,
			boolean basicTax, boolean surcharge, boolean interest, boolean compromisePenalty, boolean amountPaid) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.debitCredit = debitCredit;
		this.amountValue = amountValue;
		this.basicTax = basicTax;
		this.surcharge = surcharge;
		this.interest = interest;
		this.compromisePenalty = compromisePenalty;
		this.amountPaid = amountPaid;
	}

	public MaintTasAdjustmentReason(String id, String code, String description,
			ReferenceTasAdjustmentType tasAdjustmentType, String debitCredit, String amountValue, boolean isApproval,
			boolean isEditableAmount, Date effectiveDate, Date expiryDate, String createdBy, Date createdDate,
			boolean basicTax, boolean surcharge, boolean interest, boolean compromisePenalty, boolean amountPaid,
			String updatedBy, Date updatedDate) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.tasAdjustmentType = tasAdjustmentType;
		this.debitCredit = debitCredit;
		this.amountValue = amountValue;
		this.isApproval = isApproval;
		this.isEditableAmount = isEditableAmount;
		this.effectiveDate = effectiveDate;
		this.expiryDate = expiryDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.basicTax = basicTax;
		this.surcharge = surcharge;
		this.interest = interest;
		this.compromisePenalty = compromisePenalty;
		this.amountPaid = amountPaid;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public ReferenceTasAdjustmentType getTasAdjustmentType() {
		return tasAdjustmentType;
	}

	public void setTasAdjustmentType(ReferenceTasAdjustmentType tasAdjustmentType) {
		this.tasAdjustmentType = tasAdjustmentType;
	}

	public String getDebitCredit() {
		return debitCredit;
	}

	public void setDebitCredit(String debitCredit) {
		this.debitCredit = debitCredit;
	}

	public String getAmountValue() {
		return amountValue;
	}

	public void setAmountValue(String amountValue) {
		this.amountValue = amountValue;
	}

	public boolean isApproval() {
		return isApproval;
	}

	public void setApproval(boolean isApproval) {
		this.isApproval = isApproval;
	}

	public boolean isEditableAmount() {
		return isEditableAmount;
	}

	public void setEditableAmount(boolean isEditableAmount) {
		this.isEditableAmount = isEditableAmount;
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

	public boolean isBasicTax() {
		return basicTax;
	}

	public void setBasicTax(boolean basicTax) {
		this.basicTax = basicTax;
	}

	public boolean isSurcharge() {
		return surcharge;
	}

	public void setSurcharge(boolean surcharge) {
		this.surcharge = surcharge;
	}

	public boolean isInterest() {
		return interest;
	}

	public void setInterest(boolean interest) {
		this.interest = interest;
	}

	public boolean isCompromisePenalty() {
		return compromisePenalty;
	}

	public void setCompromisePenalty(boolean compromisePenalty) {
		this.compromisePenalty = compromisePenalty;
	}

	public boolean isAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(boolean amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "MaintTasAdjustmentReason [id=" + id + ", code=" + code + ", description=" + description
				+ ", tasAdjustmentType=" + tasAdjustmentType + ", debitCredit=" + debitCredit + ", amountValue="
				+ amountValue + ", isApproval=" + isApproval + ", isEditableAmount=" + isEditableAmount
				+ ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", basicTax=" + basicTax + ", surcharge=" + surcharge + ", interest="
				+ interest + ", compromisePenalty=" + compromisePenalty + ", amountPaid=" + amountPaid + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + "]";
	}

}
