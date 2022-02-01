/*
 * Modified by: romerov
 * Last updated: Jul 19, 2019 9:14:53 AM
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
 * The Class BookOfAccount.
 */
@Entity
@Table(name = "TAXPAYER_BOOK_OF_ACCOUNT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookOfAccount implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "TAXPAYER_BOOK_OF_ACCOUNT_SEQUENCESTYLEGENERATOR")
	@GenericGenerator(name = "TAXPAYER_BOOK_OF_ACCOUNT_SEQUENCESTYLEGENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_BOOK_OF_ACCOUNT"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "TAXPAYER_BOOK_OF_ACCOUNT_ID", unique = true, nullable = false)
	private Long id;

	/** The taxpayer id. */
	@Column(name = "TAXPAYER_ID")
	private Long taxpayerId;

	@Column(name = "BOOK_OF_ACCOUNT_TYPE_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String bookOfAccounttypeId;

	@Column(name = "BOOK_OF_ACCOUNT_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String bookOfAccounttypeDescription;

	@Column(name = "BOOK_REGISTERED_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String bookRegisteredId;

	@Column(name = "BOOK_REGISTERED_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String bookRegisteredDescription;

	/** The quantity. */
	@Column(name = "QUANTITY", length = 10)
	private Integer quantity;

	/** The volume from. */
	@Column(name = "VOLUME_FROM", length = 10)
	private Integer volumeFrom;

	/** The volume to. */
	@Column(name = "VOLUME_TO", length = 10)
	private Integer volumeTo;

	/** The date registered. */
	@Column(name = "DATE_REGISTERED", columnDefinition = "DATE")
	private Date dateRegistered;

	/** The permit no. */
	@Column(name = "PERMIT_NO", columnDefinition = "VARCHAR2(20 BYTE)")
	private String permitNo;

	/** The date issued. */
	@Column(name = "DATE_ISSUED", columnDefinition = "DATE")
	private Date dateIssued;

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
	@Column(name = "CREATED_BY", columnDefinition = "VARCHAR2(50 BYTE)")
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

	/** The taxable year from. */
	@Column(name = "TAXABLE_YEAR_FROM")
	private Date taxableYearFrom;

	/** The taxable year to. */
	@Column(name = "TAXABLE_YEAR_TO")
	private Date taxableYearTo;

	@Transient
	private String status;

	public BookOfAccount() {
		super();
		this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
	}

	public BookOfAccount(Long id, Long taxpayerId, String bookOfAccounttypeId, String bookOfAccounttypeDescription,
			String bookRegisteredId, String bookRegisteredDescription, Integer quantity, Integer volumeFrom,
			Integer volumeTo, Date dateRegistered, String permitNo, Date dateIssued, Date effectivityDate,
			Date expiryDate, String dataSourceCreated, String dataSourceUpdated, String createdBy, Date createdDate,
			String updatedBy, Date updatedDate, Date cancellationDate, Date taxableYearFrom, Date taxableYearTo,
			String status) {
		super();
		this.id = id;
		this.taxpayerId = taxpayerId;
		this.bookOfAccounttypeId = bookOfAccounttypeId;
		this.bookOfAccounttypeDescription = bookOfAccounttypeDescription;
		this.bookRegisteredId = bookRegisteredId;
		this.bookRegisteredDescription = bookRegisteredDescription;
		this.quantity = quantity;
		this.volumeFrom = volumeFrom;
		this.volumeTo = volumeTo;
		this.dateRegistered = dateRegistered;
		this.permitNo = permitNo;
		this.dateIssued = dateIssued;
		this.effectivityDate = effectivityDate;
		this.expiryDate = expiryDate;
		this.dataSourceCreated = dataSourceCreated;
		this.dataSourceUpdated = dataSourceUpdated;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.cancellationDate = cancellationDate;
		this.taxableYearFrom = taxableYearFrom;
		this.taxableYearTo = taxableYearTo;
		this.status = status;
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

	public String getBookOfAccounttypeId() {
		return bookOfAccounttypeId;
	}

	public void setBookOfAccounttypeId(String bookOfAccounttypeId) {
		this.bookOfAccounttypeId = bookOfAccounttypeId;
	}

	public String getBookOfAccounttypeDescription() {
		return bookOfAccounttypeDescription;
	}

	public void setBookOfAccounttypeDescription(String bookOfAccounttypeDescription) {
		this.bookOfAccounttypeDescription = bookOfAccounttypeDescription;
	}

	public String getBookRegisteredId() {
		return bookRegisteredId;
	}

	public void setBookRegisteredId(String bookRegisteredId) {
		this.bookRegisteredId = bookRegisteredId;
	}

	public String getBookRegisteredDescription() {
		return bookRegisteredDescription;
	}

	public void setBookRegisteredDescription(String bookRegisteredDescription) {
		this.bookRegisteredDescription = bookRegisteredDescription;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getVolumeFrom() {
		return volumeFrom;
	}

	public void setVolumeFrom(Integer volumeFrom) {
		this.volumeFrom = volumeFrom;
	}

	public Integer getVolumeTo() {
		return volumeTo;
	}

	public void setVolumeTo(Integer volumeTo) {
		this.volumeTo = volumeTo;
	}

	public Date getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public String getPermitNo() {
		return permitNo;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}

	public Date getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(Date dateIssued) {
		this.dateIssued = dateIssued;
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

	public Date getTaxableYearFrom() {
		return taxableYearFrom;
	}

	public void setTaxableYearFrom(Date taxableYearFrom) {
		this.taxableYearFrom = taxableYearFrom;
	}

	public Date getTaxableYearTo() {
		return taxableYearTo;
	}

	public void setTaxableYearTo(Date taxableYearTo) {
		this.taxableYearTo = taxableYearTo;
	}

	public String getStatus() {
		if (this.cancellationDate != null) {
			this.status = (String.valueOf(BookOfAccountsStatusEnum.CANCELLED));
		} else {
      this.status = (String.valueOf(BookOfAccountsStatusEnum.REGISTERED));
		}
		return status;
	}

  @Override
  public String toString() {
    return "BookOfAccount [id=" + id + ", taxpayerId=" + taxpayerId + ", bookOfAccounttypeId="
        + bookOfAccounttypeId + ", bookOfAccounttypeDescription=" + bookOfAccounttypeDescription
        + ", bookRegisteredId=" + bookRegisteredId + ", bookRegisteredDescription="
        + bookRegisteredDescription + ", quantity=" + quantity + ", volumeFrom=" + volumeFrom
        + ", volumeTo=" + volumeTo + ", dateRegistered=" + dateRegistered + ", permitNo=" + permitNo
        + ", dateIssued=" + dateIssued + ", effectivityDate=" + effectivityDate + ", expiryDate="
        + expiryDate + ", dataSourceCreated=" + dataSourceCreated + ", dataSourceUpdated="
        + dataSourceUpdated + ", createdBy=" + createdBy + ", createdDate=" + createdDate
        + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", cancellationDate="
        + cancellationDate + ", taxableYearFrom=" + taxableYearFrom + ", taxableYearTo="
        + taxableYearTo + ", status=" + status + "]";
  }

}
