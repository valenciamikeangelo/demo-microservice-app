/*
  * Modified by: romerov
  * Last updated: 03 31, 20 2:31:35 PM
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
 * The Class TaxPayerSubTaxType.
 */
@Entity
@Table(name = "TAXPAYER_SUB_TAX_TYPE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxPayerSubTaxType implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "TP_SUB_TAX_TYPE_SEQUENCESTYLEGENERATOR")
	@GenericGenerator(name = "TP_SUB_TAX_TYPE_SEQUENCESTYLEGENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_SUB_TAX_TYPE"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@Column(name = "TAXPAYER_SUB_TAX_TYPE_ID", unique = true, nullable = false)
	private Long id;

	/** The tp tax type id. */
	@Column(name = "TAXPAYER_TAX_TYPE_ID")
	private Long tpTaxTypeId;

	/** The code. */
	@Column(name = "SUB_TAX_TYPE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
	private String subTaxTypeId;

	/** The code. */
	@Column(name = "SUB_TAX_TYPE_CODE", columnDefinition = "VARCHAR2(30 BYTE)")
	private String code;

	/** The description. */
	@Column(name = "SUB_TAX_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(400 BYTE)")
	private String description;

	/** The effectivity date. */
	@Column(name = "EFFECTIVITY_DATE", columnDefinition = "DATE")
	private Date effectivityDate;

	/** The expiry date. */
	@Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
	private Date expiryDate;

	/** The data source created. */
	@Column(name = "DATA_SOURCE_CREATED", columnDefinition = "VARCHAR2(5 BYTE)")
	private String dataSourceCreated;

	/** The data source updated. */
	@Column(name = "DATA_SOURCE_UPDATED", columnDefinition = "VARCHAR2(5 BYTE)")
	private String dataSourceUpdated;

	/** The created by. */
	@Column(name = "CREATED_BY", nullable = false, columnDefinition = "VARCHAR2(50 BYTE)")
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

	/** The cancellation date. */
	@Column(name = "CANCELLATION_DATE", columnDefinition = "DATE")
	private Date cancellationDate;

	@Transient
	private String status;

	public TaxPayerSubTaxType() {
		super();
		this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
	}

	public TaxPayerSubTaxType(Long tpTaxTypeId, String subTaxTypeId, String code, String description,
			Date effectivityDate, Date expiryDate, String dataSourceCreated, String dataSourceUpdated, String createdBy,
			Date createdDate, String updatedBy, Date updatedDate, Date cancellationDate) {
		super();
		this.tpTaxTypeId = tpTaxTypeId;
		this.subTaxTypeId = subTaxTypeId;
		this.code = code;
		this.description = description;
		this.effectivityDate = effectivityDate;
		this.expiryDate = expiryDate;
		this.dataSourceCreated = dataSourceCreated;
		this.dataSourceUpdated = dataSourceUpdated;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.cancellationDate = cancellationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTpTaxTypeId() {
		return tpTaxTypeId;
	}

	public void setTpTaxTypeId(Long tpTaxTypeId) {
		this.tpTaxTypeId = tpTaxTypeId;
	}

	public String getSubTaxTypeId() {
		return subTaxTypeId;
	}

	public void setSubTaxTypeId(String subTaxTypeId) {
		this.subTaxTypeId = subTaxTypeId;
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

	public Date getCancellationDate() {
		return cancellationDate;
	}

	public void setCancellationDate(Date cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		if (getCancellationDate() != null) {
			this.status = (String.valueOf(TaxSubTaxTypeStatusEnum.CANCELLED));
		} else {
			
			if (getEffectivityDate() != null && getCancellationDate() == null) {
				this.status = (String.valueOf(TaxSubTaxTypeStatusEnum.REGISTERED));
			}
		}
		return status;
	}

}
