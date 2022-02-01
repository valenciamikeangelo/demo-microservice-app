package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "REG_ADDRESSES", uniqueConstraints = @UniqueConstraint(columnNames = { "TIN", "BRANCH_CODE", "CODE",
		"STATUS_CODE" }, name = "UC_REG_ADDRESSES_01"))
public class ITSRegAddress implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ITSRegAddressId id;
	
	@Column(name = "CREATED_BY", columnDefinition = "VARCHAR2(12 BYTE)", nullable = false)
	private String createdBy;
	
	@Column(name = "DATE_CREATED", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE")
	private Date dateCreated;
	
	@Column(name = "SUBSTREET", columnDefinition = "VARCHAR2(30 BYTE)")
	private String substreet;
	
	@Column(name = "STREET", columnDefinition = "VARCHAR2(30 BYTE)")
	private String street;
	
	@Column(name = "BARANGAY", columnDefinition = "VARCHAR2(30 BYTE)")
	private String barangay;
	
	@Column(name = "DISTRICT", columnDefinition = "VARCHAR2(30 BYTE)")
	private String district;
	
	@Column(name = "CITY", columnDefinition = "VARCHAR2(30 BYTE)")
	private String city;
	
	@Column(name = "ZIP_CODE", columnDefinition = "VARCHAR2(12 BYTE)")
	private String zipCode;
	
	@Column(name = "REGISTER_FLAG", columnDefinition = "VARCHAR2(1 CHAR)")
	private String registerFlag;
	
	@Column(name = "MAIL_ROUTE", columnDefinition = "VARCHAR2(4 BYTE)")
	private String mailRoute;
	
	@Column(name = "PROCESS", columnDefinition = "VARCHAR2(1 BYTE)")
	private String process;
	
	@Column(name = "MODIFIED_BY", columnDefinition = "VARCHAR2(12 BYTE)")
	private String modifiedBy;
	
	@Column(name = "DATE_MODIFIED", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE")
	private Date dateModified;

	public ITSRegAddressId getId() {
		return id;
	}

	public void setId(ITSRegAddressId id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getSubstreet() {
		return substreet;
	}

	public void setSubstreet(String substreet) {
		this.substreet = substreet;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBarangay() {
		return barangay;
	}

	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getRegisterFlag() {
		if (null == this.registerFlag) {
			registerFlag = "";
		    }
		return registerFlag;
	}

	public void setRegisterFlag(String registerFlag) {
		this.registerFlag = registerFlag;
		if (null == this.registerFlag) {
		      this.registerFlag = "";
		    }
	}

	public String getMailRoute() {
		return mailRoute;
	}

	public void setMailRoute(String mailRoute) {
		this.mailRoute = mailRoute;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public ITSRegAddress(ITSRegAddressId id, String createdBy, Date dateCreated, String substreet, String street,
			String barangay, String district, String city, String zipCode, String registerFlag, String mailRoute,
			String process, String modifiedBy, Date dateModified) {
		super();
		this.id = id;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
		this.substreet = substreet;
		this.street = street;
		this.barangay = barangay;
		this.district = district;
		this.city = city;
		this.zipCode = zipCode;
		this.registerFlag = registerFlag;
		this.mailRoute = mailRoute;
		this.process = process;
		this.modifiedBy = modifiedBy;
		this.dateModified = dateModified;
	}

	public ITSRegAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
		
}
