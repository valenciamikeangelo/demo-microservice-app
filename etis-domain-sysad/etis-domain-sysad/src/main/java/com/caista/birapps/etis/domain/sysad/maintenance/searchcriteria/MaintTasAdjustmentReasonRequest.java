package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;

import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTasAdjustmentType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintTasAdjustmentReasonRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String description;
	private String tasAdjustmentTypeCode;
	private ReferenceTasAdjustmentType tasAdjustmentType;
	private String debitCredit;
	private String amountValue;
	private boolean isApproval;
	private boolean isEditableAmount;

	public MaintTasAdjustmentReasonRequest() {
		super();
	}

	public MaintTasAdjustmentReasonRequest(String code, String description, String tasAdjustmentTypeCode,
			ReferenceTasAdjustmentType tasAdjustmentType, String debitCredit, String amountValue, boolean isApproval,
			boolean isEditableAmount) {
		super();
		this.code = code;
		this.description = description;
		this.tasAdjustmentTypeCode = tasAdjustmentTypeCode;
		this.tasAdjustmentType = tasAdjustmentType;
		this.debitCredit = debitCredit;
		this.amountValue = amountValue;
		this.isApproval = isApproval;
		this.isEditableAmount = isEditableAmount;
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

	public String getTasAdjustmentTypeCode() {
		return tasAdjustmentTypeCode;
	}

	public void setTasAdjustmentTypeCode(String tasAdjustmentTypeCode) {
		this.tasAdjustmentTypeCode = tasAdjustmentTypeCode;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amountValue == null) ? 0 : amountValue.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((debitCredit == null) ? 0 : debitCredit.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (isApproval ? 1231 : 1237);
		result = prime * result + (isEditableAmount ? 1231 : 1237);
		result = prime * result + ((tasAdjustmentType == null) ? 0 : tasAdjustmentType.hashCode());
		result = prime * result + ((tasAdjustmentTypeCode == null) ? 0 : tasAdjustmentTypeCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaintTasAdjustmentReasonRequest other = (MaintTasAdjustmentReasonRequest) obj;
		if (amountValue == null) {
			if (other.amountValue != null)
				return false;
		} else if (!amountValue.equals(other.amountValue))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (debitCredit == null) {
			if (other.debitCredit != null)
				return false;
		} else if (!debitCredit.equals(other.debitCredit))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (isApproval != other.isApproval)
			return false;
		if (isEditableAmount != other.isEditableAmount)
			return false;
		if (tasAdjustmentType == null) {
			if (other.tasAdjustmentType != null)
				return false;
		} else if (!tasAdjustmentType.equals(other.tasAdjustmentType))
			return false;
		if (tasAdjustmentTypeCode == null) {
			if (other.tasAdjustmentTypeCode != null)
				return false;
		} else if (!tasAdjustmentTypeCode.equals(other.tasAdjustmentTypeCode))
			return false;
		return true;
	}

}
