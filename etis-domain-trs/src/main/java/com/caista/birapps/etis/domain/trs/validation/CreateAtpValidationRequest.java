/*
 * Modified by: tolentf
 * Last updated: Nov 12, 2018 8:26:08 PM
 */
package com.caista.birapps.etis.domain.trs.validation;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.caista.birapps.etis.domain.trs.atp.entity.AtpCorrespondence;
import com.caista.birapps.etis.domain.trs.entity.InvoiceDetail;

public class CreateAtpValidationRequest extends ValidationRequest<AtpCorrespondence> {

	@NotNull(message = "taxpayer.firstName.required")
	private String firstName;

	@NotNull(message = "taxpayer.lastName.required")
	private String lastName;

	@NotNull(message = "taxpayer.middleName.required")
	private String middleName;

	@Size(min = 5, max = 5, message = "taxpayer.branch.sizeLimit")
	@NotNull(message = "taxpayer.branch.required")
	private String branchCode;

	@NotNull(message = "taxpayer.registeredName.required")
	private String registeredName;

	@Size(min = 1, message = "atp.invoiceDetails.required")
	@NotNull(message = "atp.invoiceDetails.required")
	private List<InvoiceDetail> invoiceDetails;


	@NotNull(message = "atp.authorizationType.required")
	private String authorizationType;

	@NotNull(message = "atp.applicationIndicator.required")
	private String applicationIndicator;

	@Size(max = 10, message = "atp.atpStatus.sizeLimit")
	@NotNull(message = "atp.atpStatus.required")
	private String atpStatus;

	@Size(min = 9, max = 9, message = "atp.headOfficeTin.sizeLimit")
	@NotNull(message = "atp.headOfficeTin.required")
	private String headOfficeTin;

	@NotNull(message = "atp.registeredBusinessAddress.required")
	private String registeredBusinessAddress;

	@NotNull(message = "atp.tradeName.required")
	private String tradeName;

	@NotNull(message = "atp.primaryPsic.required")
	private String primaryPsic;

	@NotNull(message = "atp.vatRegistered.required")
	private Boolean vatRegistered;

	@NotNull(message = "atp.printerTin.required")
	private String printerTin;

	@NotNull(message = "atp.printerName.required")
	private String printerName;

	@NotNull(message = "atp.signatory.required")
	private String signatory;

	@NotNull(message = "atp.birRegistrationDate.required")
	private Date birRegistrationDate;

	@Past(message = "atp.atpDate.required")
	private Date atpDate;

	@Future(message = "atp.expiryDate.required")
	private Date expiryDate;

	public CreateAtpValidationRequest(AtpCorrespondence atpCorrespondence) {
		this.invoiceDetails = atpCorrespondence.getInvoiceDetails();
		this.authorizationType = atpCorrespondence.getAuthorizationTypeDescription();
		this.applicationIndicator = atpCorrespondence.getApplicationIndicatorDescription();
		this.atpStatus = atpCorrespondence.getAtpStatus();
		this.headOfficeTin = atpCorrespondence.getHeadOfficeTin();
		this.firstName = atpCorrespondence.getFirstName();
		this.lastName = atpCorrespondence.getLastName();
		this.middleName = atpCorrespondence.getMiddleName();
		this.registeredName = atpCorrespondence.getRegisteredName();
		this.branchCode = atpCorrespondence.getBranchCode();
		this.tradeName = atpCorrespondence.getTradeName();
		this.primaryPsic = atpCorrespondence.getPrimaryPsic();
		this.vatRegistered = atpCorrespondence.getVatRegistered();
		this.printerTin = atpCorrespondence.getPrinterTin();
		this.printerName = atpCorrespondence.getPrinterName();
		this.signatory = atpCorrespondence.getSignatoryDescription();
		this.birRegistrationDate = atpCorrespondence.getBirRegistrationDate();
		this.atpDate = atpCorrespondence.getAtpDate();
		this.expiryDate = atpCorrespondence.getExpiryDate();
	}
}
