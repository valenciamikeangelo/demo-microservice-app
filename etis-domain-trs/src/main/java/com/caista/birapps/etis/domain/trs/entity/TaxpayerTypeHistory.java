/*
 * Modified by: santojo
 * Last updated: Apr 29, 2019 4:17:40 PM
 */
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "TAXPAYER_TYPE_HISTORY")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxpayerTypeHistory implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "TP_TAXPAYER_TYPE_HISTORY_SequenceStyleGenerator")
	@GenericGenerator(name = "TP_TAXPAYER_TYPE_HISTORY_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_TP_TAXPAYER_TYPE_HISTORY"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "TAXPAYER_TYPE_HISTORY_ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "OLD_TAXPAYER_TYPE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
	private String oldTaxpayerTypeId;

	@Column(name = "OLD_TAXPAYER_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(120 BYTE)")
	private String oldTaxpayerTypeDescription;

	@Column(name = "NEW_TAXPAYER_TYPE_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String newTaxpayerTypeId;

	@Column(name = "NEW_TAXPAYER_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(200 BYTE)")
	private String newTaxpayerTypeDescription;

	/** The created by. */
	@Column(name = "CREATED_BY", nullable = false, columnDefinition = "VARCHAR2(50 BYTE)")
	private String createdBy;

	/** The created date. */
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE", nullable = false)
	private Date createdDate;

	/** The taxpayer. */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TAXPAYER_ID", foreignKey = @ForeignKey(name = "FK_TAXPAYER_TYPE_HISTORY__TAXPAYER_01"))
	private TaxPayer taxpayer;

	public TaxpayerTypeHistory() {
		super();
	}

    public TaxpayerTypeHistory(Long id, String oldTaxpayerTypeId,
        String oldTaxpayerTypeDescription, String newTaxpayerTypeId,
        String newTaxpayerTypeDescription, String createdBy, Date createdDate) {
      super();
      this.id = id;
      this.oldTaxpayerTypeId = oldTaxpayerTypeId;
      this.oldTaxpayerTypeDescription = oldTaxpayerTypeDescription;
      this.newTaxpayerTypeId = newTaxpayerTypeId;
      this.newTaxpayerTypeDescription = newTaxpayerTypeDescription;
      this.createdBy = createdBy;
      this.createdDate = createdDate;
    }

    public TaxpayerTypeHistory(TaxPayer taxPayer) {
      super();
      this.newTaxpayerTypeId = taxPayer.getTaxpayerTypeId();
      this.newTaxpayerTypeDescription = taxPayer.getTaxpayerTypeDescription();
      this.createdBy = taxPayer.getCreatedBy();
      this.createdDate = taxPayer.getCreatedDate();
      this.taxpayer = taxPayer;
    }

    public Long getId() {
  		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOldTaxpayerTypeId() {
		return oldTaxpayerTypeId;
	}

	public void setOldTaxpayerTypeId(String oldTaxpayerTypeId) {
		this.oldTaxpayerTypeId = oldTaxpayerTypeId;
	}

	public String getOldTaxpayerTypeDescription() {
		return oldTaxpayerTypeDescription;
	}

	public void setOldTaxpayerTypeDescription(String oldTaxpayerTypeDescription) {
		this.oldTaxpayerTypeDescription = oldTaxpayerTypeDescription;
	}

	public String getNewTaxpayerTypeId() {
		return newTaxpayerTypeId;
	}

	public void setNewTaxpayerTypeId(String newTaxpayerTypeId) {
		this.newTaxpayerTypeId = newTaxpayerTypeId;
	}

	public String getNewTaxpayerTypeDescription() {
		return newTaxpayerTypeDescription;
	}

	public void setNewTaxpayerTypeDescription(String newTaxpayerTypeDescription) {
		this.newTaxpayerTypeDescription = newTaxpayerTypeDescription;
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

    public TaxPayer getTaxpayer() {
      return taxpayer;
    }

    public void setTaxpayer(TaxPayer taxpayer) {
      this.taxpayer = taxpayer;
    }

  @Override
  public String toString() {
    return "TaxpayerTypeHistory [id=" + id + ", oldTaxpayerTypeId=" + oldTaxpayerTypeId
        + ", oldTaxpayerTypeDescription=" + oldTaxpayerTypeDescription + ", newTaxpayerTypeId="
        + newTaxpayerTypeId + ", newTaxpayerTypeDescription=" + newTaxpayerTypeDescription
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
  }

}
