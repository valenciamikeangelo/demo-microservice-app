/*
 * Modified by: fuentem
 * Last updated: Oct 16, 2018 10:21:19 AM
 */
package com.caista.birapps.etis.domain.trs.validation;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.caista.birapps.etis.domain.trs.entity.TaxPayer;

/**
 * @author fuentem
 *
 */
public class RegisterNonIndividualValidationRequest extends ValidationRequest<TaxPayer> {

	@NotNull(message = "taxpayer.legalStatusType.required")
	private String taxpayerTypeId;

	@NotNull(message = "taxpayer.taxpayerGroup.required")
	private String taxpayerGroupId;

	@NotNull(message = "taxpayer.registeredName.required")
	private String registeredName;

	@NotNull(message = "taxpayer.incorporationDate.required")
	private Date incorporationDate;


	@NotNull(message = "taxpayer.accountingEffectiveDate.required")
	private Date accountingEffectiveDate;

	@NotNull(message = "taxpayer.regulatoryBody.required")
	private String regulatoryBodyId;

	@NotNull(message = "taxpayer.registrationNumber.required")
	private String registrationNumber;

	@NotNull(message = "taxpayer.submissionDate.required")
	private Date submissionDate;


	public RegisterNonIndividualValidationRequest(TaxPayer taxPayer) {
		this.taxpayerTypeId = taxPayer.getTaxpayerTypeId();
		this.taxpayerGroupId = taxPayer.getTaxpayerGroupId();
		this.registeredName = taxPayer.getRegisteredName();
		this.incorporationDate = taxPayer.getIncorporationDate();
		this.accountingEffectiveDate = taxPayer.getAccountingEffectiveDate();
		this.regulatoryBodyId = taxPayer.getRegulatoryBodyId();
		this.registrationNumber = taxPayer.getRegistrationNumber();
		this.submissionDate = taxPayer.getSubmissionDate();
		
	}

}
