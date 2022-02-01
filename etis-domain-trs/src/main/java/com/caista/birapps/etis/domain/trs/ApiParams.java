/*
  * Modified by: decinam
  * Last updated: Aug 9, 2018 12:58:07 AM
  */
package com.caista.birapps.etis.domain.trs;

import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ApiParams.
 */
public class ApiParams {

	// General Search Details
	private String rdo;

	private String tin;

	private String branchCode;

	private String taxpayerClassification;

	// General Search Details: Individual
	private String firstName;

	private String lastName;

	private String middleName;

	// General Search Details: Non-Individual
	private String registeredLegalName;

	// Advanced Search Details: Individual & Non-Individual
	private String businessTradeName;

	private String taxPayerType;

	private String status;

	private String tinStatus;

	// Advanced Search Details: Individual
	private String mothersMaidenName;

	private Date dateOfBirth;


	private String registeredFilerName;

	private String rdoRegTin;

	// ATP Search Details:
	private Long id;

	private String ocn;

	private String headOfficeTin;

	private Long officeId;

	private Integer flag;
	
	private List<String> subSections;

	@Override
	public String toString() {
		return "ApiParams [rdo=" + rdo + ", tin=" + tin + ", branchCode=" + branchCode + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", middleName=" + middleName + ", registeredLegalName="
				+ registeredLegalName + ", businessTradeName=" + businessTradeName + ", taxPayerType=" + taxPayerType
				+ ", status=" + status + ", mothersMaidenName=" + mothersMaidenName + ", dateOfBirth=" + dateOfBirth
				+ ", taxpayerClassification=" + taxpayerClassification + ", registeredFilerName=" + registeredFilerName
				+ ", rdoRegTin=" + rdoRegTin + ", id=" + id + ", ocn=" + ocn + "]";
	}

	public String getRdo() {
		return rdo;
	}

	public void setRdo(String rdo) {
		this.rdo = rdo;
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

	public String getTaxpayerClassification() {
		return taxpayerClassification;
	}

	public void setTaxpayerClassification(String taxpayerClassification) {
		this.taxpayerClassification = taxpayerClassification;
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

	public String getRegisteredLegalName() {
		return registeredLegalName;
	}

	public void setRegisteredLegalName(String registeredLegalName) {
		this.registeredLegalName = registeredLegalName;
	}

	public String getBusinessTradeName() {
		return businessTradeName;
	}

	public void setBusinessTradeName(String businessTradeName) {
		this.businessTradeName = businessTradeName;
	}

	public String getTaxPayerType() {
		return taxPayerType;
	}

	public void setTaxPayerType(String taxPayerType) {
		this.taxPayerType = taxPayerType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTinStatus() {
		return tinStatus;
	}

	public void setTinStatus(String tinStatus) {
		this.tinStatus = tinStatus;
	}

	public String getMothersMaidenName() {
		return mothersMaidenName;
	}

	public void setMothersMaidenName(String mothersMaidenName) {
		this.mothersMaidenName = mothersMaidenName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getRegisteredFilerName() {
		return registeredFilerName;
	}

	public void setRegisteredFilerName(String registeredFilerName) {
		this.registeredFilerName = registeredFilerName;
	}

	public String getRdoRegTin() {
		return rdoRegTin;
	}

	public void setRdoRegTin(String rdoRegTin) {
		this.rdoRegTin = rdoRegTin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOcn() {
		return ocn;
	}

	public void setOcn(String ocn) {
		this.ocn = ocn;
	}

	public String getHeadOfficeTin() {
		return headOfficeTin;
	}

	public void setHeadOfficeTin(String headOfficeTin) {
		this.headOfficeTin = headOfficeTin;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public List<String> getSubSections() {
		return subSections;
	}

	public void setSubSections(List<String> subSections) {
		this.subSections = subSections;
	}

}
