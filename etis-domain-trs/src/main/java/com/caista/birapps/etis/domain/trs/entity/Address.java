/*
 * Modified by: santojo
 * Last updated: Apr 29, 2019 4:15:48 PM
 */
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.trs.utils.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class Address.
 */
@Entity
@Table(name = "TAXPAYER_ADDRESS")
@Check(constraints = "IS_PRIMARY IN (1, 0) AND IS_FOREIGN IN (1, 0)")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "TAXPAYER_ADDRESS_SEQUENCESTYLEGENERATOR")
	@GenericGenerator(name = "TAXPAYER_ADDRESS_SEQUENCESTYLEGENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_ADDRESS"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "ADDRESS_ID", unique = true, nullable = false)
	private Long id;

	/** The taxpayer id. */
	@Column(name = "TAXPAYER_ID")
	private Long taxpayerId;

	@Column(name = "ADDRESS_TYPE_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String addressTypeId;

	@Column(name = "ADDRESS_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String addressTypeDescription;

	@Column(name = "ADDRESS_TYPE_CODE", columnDefinition = "VARCHAR2(50 BYTE)")
	private String addressTypeCode;

	/** The is primary. */
	@Column(name = "is_primary", columnDefinition = "NUMBER(1,0) DEFAULT 0")
	private Boolean isPrimary;

	/** The town/district. */
	@Column(name = "town_district", columnDefinition = "VARCHAR2(150 BYTE)")
	private String townDistrict;

	/** The subdivision/village. */
	@Column(name = "subdivision_village", columnDefinition = "VARCHAR2(150 BYTE)")
	private String subdivisionVillage;

	/** The street. */
	@Column(name = "street", columnDefinition = "VARCHAR2(150 BYTE)")
	private String street;

	/** The lot/block/phase no. */
	@Column(name = "lot_block_phase_no", columnDefinition = "VARCHAR2(150 BYTE)")
	private String lotBlockPhaseNo;

	/** The building/tower. */
	@Column(name = "building_tower", columnDefinition = "VARCHAR2(150 BYTE)")
	private String buildingTower;

	/** The unit room/floor no. */
	@Column(name = "unit_room_floor_no", columnDefinition = "VARCHAR2(150 BYTE)")
	private String unitRoomFloorNo;

	@Column(name = "PROVINCE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
	private String provinceId;

	@Column(name = "PROVINCE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String provinceDescription;

	@Column(name = "BARANGAY_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String barangayId;

	@Column(name = "BARANGAY_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String barangayDescription;

	@Column(name = "ZIP_CODE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
	private String zipCodeId;

	@Column(name = "ZIP_CODE", columnDefinition = "VARCHAR2(30 BYTE)")
	private String zipCode;

	@Column(name = "CITY_MUNICIPALITY_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String municipalityId;

	@Column(name = "CITY_MUNICIPALITY_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String municipalityDescription;

	@Column(name = "COUNTRY_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String countryId;

	@Column(name = "COUNTRY_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String countryDescription;

	/** The is foreign. */
	@Column(name = "IS_FOREIGN", columnDefinition = "NUMBER(1,0) DEFAULT 0")
	private Boolean isForeign;

	/** The effectivity date. */
	@Column(name = "EFFECTIVITY_DATE", columnDefinition = "DATE")
	private Date effectivityDate;

	/** The expiry date. */
	@Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
	private Date expiryDate;

	@Column(name = "CANCELLATION_DATE", columnDefinition = "DATE")
	private Date cancellationDate;

	/** The data source created. */
	@Column(name = "DATA_SOURCE_CREATED", columnDefinition = "VARCHAR2(5 BYTE)")
	private String dataSourceCreated;

	/** The data source updated. */
	@Column(name = "DATA_SOURCE_UPDATED", columnDefinition = "VARCHAR2(5 BYTE)")
	private String dataSourceUpdated;

	/** The created by. */
	@Column(name = "CREATED_BY", columnDefinition = "VARCHAR2(50 BYTE)", nullable = false)
	private String createdBy;

	/** The created date. */
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE", nullable = false)
	private Date createdDate;

	/** The updated by. */
	@Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(50 BYTE)")
	private String updatedBy;

	/** The updated date. */
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Transient
	private String status;

	public Address() {
		super();
		this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTaxpayerId() {
		return taxpayerId;
	}

	public void setTaxpayerId(Long taxpayerId) {
		this.taxpayerId = taxpayerId;
	}

	public String getAddressTypeId() {
		return addressTypeId;
	}

	public void setAddressTypeId(String addressTypeId) {
		this.addressTypeId = addressTypeId;
	}

	public String getAddressTypeDescription() {
		return addressTypeDescription;
	}

	public void setAddressTypeDescription(String addressTypeDescription) {
		this.addressTypeDescription = addressTypeDescription;
	}

	public Boolean getIsPrimary() {
		if (null == this.isPrimary) {
			isPrimary = false;
		    }
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
		if (null == this.isPrimary) {
		      this.isPrimary = false;
		    }
	}

	public String getTownDistrict() {
		return townDistrict;
	}

	public void setTownDistrict(String townDistrict) {
		this.townDistrict = townDistrict;
	}

	public String getSubdivisionVillage() {
		return subdivisionVillage;
	}

	public void setSubdivisionVillage(String subdivisionVillage) {
		this.subdivisionVillage = subdivisionVillage;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLotBlockPhaseNo() {
		return lotBlockPhaseNo;
	}

	public void setLotBlockPhaseNo(String lotBlockPhaseNo) {
		this.lotBlockPhaseNo = lotBlockPhaseNo;
	}

	public String getBuildingTower() {
		return buildingTower;
	}

	public void setBuildingTower(String buildingTower) {
		this.buildingTower = buildingTower;
	}

	public String getUnitRoomFloorNo() {
		return unitRoomFloorNo;
	}

	public void setUnitRoomFloorNo(String unitRoomFloorNo) {
		this.unitRoomFloorNo = unitRoomFloorNo;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceDescription() {
		return provinceDescription;
	}

	public void setProvinceDescription(String provinceDescription) {
		this.provinceDescription = provinceDescription;
	}

	public String getBarangayId() {
		return barangayId;
	}

	public void setBarangayId(String barangayId) {
		this.barangayId = barangayId;
	}

	public String getBarangayDescription() {
		return barangayDescription;
	}

	public void setBarangayDescription(String barangayDescription) {
		this.barangayDescription = barangayDescription;
	}

	public String getZipCodeId() {
		return zipCodeId;
	}

	public void setZipCodeId(String zipCodeId) {
		this.zipCodeId = zipCodeId;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMunicipalityId() {
		return municipalityId;
	}

	public void setMunicipalityId(String municipalityId) {
		this.municipalityId = municipalityId;
	}

	public String getMunicipalityDescription() {
		return municipalityDescription;
	}

	public void setMunicipalityDescription(String municipalityDescription) {
		this.municipalityDescription = municipalityDescription;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryDescription() {
		return countryDescription;
	}

	public void setCountryDescription(String countryDescription) {
		this.countryDescription = countryDescription;
	}

	public Boolean getIsForeign() {
		if (null == this.isForeign) {
		      isForeign = false;
		    }
		return isForeign;
	}

	public void setIsForeign(Boolean isForeign) {
		this.isForeign = isForeign;
		if (null == this.isForeign) {
		      this.isForeign = false;
		    }
	}

	public Date getEffectivityDate() {
		return effectivityDate;
	}

	public void setEffectivityDate(Date effectivityDate) {
		this.effectivityDate = effectivityDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getCancellationDate() {
		return cancellationDate;
	}

	public void setCancellationDate(Date cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

	public String getDataSourceCreated() {
		return dataSourceCreated;
	}

	public void setDataSourceCreated(String dataSourceCreated) {
		this.dataSourceCreated = dataSourceCreated;
	}

	public String getDataSourceUpdated() {
		return dataSourceUpdated;
	}

	public void setDataSourceUpdated(String dataSourceUpdated) {
		this.dataSourceUpdated = dataSourceUpdated;
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

	public void setStatus(String status) {
		this.status = status;
	}

  public String getStatus() {
	    if (this.cancellationDate == null && this.expiryDate == null) {
	        this.status = String.valueOf(AddressStatusEnum.CURRENT);
	      } else if (this.cancellationDate != null && this.expiryDate == null) {
	        this.status = String.valueOf(AddressStatusEnum.CANCELLED);
	      } else if (this.expiryDate != null && this.cancellationDate == null) {
	        this.status = String.valueOf(AddressStatusEnum.PREVIOUS);
	      } else {
	        this.status = String.valueOf(AddressStatusEnum.CANCELLED);
	      }
	      return status;
	    }

	public String getAddressTypeCode() {
		return addressTypeCode;
	}

	public void setAddressTypeCode(String addressTypeCode) {
		this.addressTypeCode = addressTypeCode;
	}

  @Override
  public String toString() {
    return "Address [id=" + id + ", taxpayerId=" + taxpayerId + ", addressTypeId=" + addressTypeId
        + ", addressTypeDescription=" + addressTypeDescription + ", addressTypeCode="
        + addressTypeCode + ", isPrimary=" + isPrimary + ", townDistrict=" + townDistrict
        + ", subdivisionVillage=" + subdivisionVillage + ", street=" + street + ", lotBlockPhaseNo="
        + lotBlockPhaseNo + ", buildingTower=" + buildingTower + ", unitRoomFloorNo="
        + unitRoomFloorNo + ", provinceId=" + provinceId + ", provinceDescription="
        + provinceDescription + ", barangayId=" + barangayId + ", barangayDescription="
        + barangayDescription + ", zipCodeId=" + zipCodeId + ", zipCode=" + zipCode
        + ", municipalityId=" + municipalityId + ", municipalityDescription="
        + municipalityDescription + ", countryId=" + countryId + ", countryDescription="
        + countryDescription + ", isForeign=" + isForeign + ", effectivityDate=" + effectivityDate
        + ", expiryDate=" + expiryDate + ", cancellationDate=" + cancellationDate
        + ", dataSourceCreated=" + dataSourceCreated + ", dataSourceUpdated=" + dataSourceUpdated
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
        + ", updatedDate=" + updatedDate + ", status=" + status + "]";
  }

}
