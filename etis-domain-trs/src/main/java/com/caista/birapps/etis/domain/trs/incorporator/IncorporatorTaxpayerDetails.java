/*
  * Modified by: bongalr
  * Last updated: Oct 30, 2018 11:16:23 AM
  */
package com.caista.birapps.etis.domain.trs.incorporator;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.caista.birapps.etis.domain.trs.utils.TaxpayerUtil;

public class IncorporatorTaxpayerDetails {

	private Long taxpayerId;
	private String tin;
	private String branchCode;
	private String registeredName;
	private String firstName;
	private String lastName;
	private String middleName;
	private String suffixId;
	private String suffixDescription;
	private Address primaryAddress;
	private String estateName;
	private String trustName;

	private List<ContactInformation> contactInformation;
	private String taxpayerClassificationId;
	private String taxpayerClassificationDescription;
	private String formattedPrimaryAddress;

	public IncorporatorTaxpayerDetails() {
		super();
	}

	public Long getTaxpayerId() {
		return taxpayerId;
	}

	public void setTaxpayerId(Long taxpayerId) {
		this.taxpayerId = taxpayerId;
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

	public String getRegisteredName() {
		return registeredName;
	}

	public void setRegisteredName(String registeredName) {
		this.registeredName = registeredName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSuffixId() {
		return suffixId;
	}

	public void setSuffixId(String suffixId) {
		this.suffixId = suffixId;
	}

	public String getSuffixDescription() {
		return suffixDescription;
	}

	public void setSuffixDescription(String suffixDescription) {
		this.suffixDescription = suffixDescription;
	}

	public Address getPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(Address primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	public List<ContactInformation> getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(List<ContactInformation> contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getTaxpayerClassificationId() {
		return taxpayerClassificationId;
	}

	public void setTaxpayerClassificationId(String taxpayerClassificationId) {
		this.taxpayerClassificationId = taxpayerClassificationId;
	}

	public String getTaxpayerClassificationDescription() {
		return taxpayerClassificationDescription;
	}

	public void setTaxpayerClassificationDescription(String taxpayerClassificationDescription) {
		this.taxpayerClassificationDescription = taxpayerClassificationDescription;
	}

	public String getFormattedPrimaryAddress() {
		if (!StringUtils.isNotBlank(formattedPrimaryAddress) && getPrimaryAddress() != null) {
			this.formattedPrimaryAddress = TaxpayerUtil.concatAddress(getPrimaryAddress());
		}
		return formattedPrimaryAddress;
	}

	public String getEstateName() {
		return estateName;
	}

	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}

	public String getTrustName() {
		return trustName;
	}

	public void setTrustName(String trustName) {
		this.trustName = trustName;
	}

}
