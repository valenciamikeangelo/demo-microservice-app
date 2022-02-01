/*
 * Modified by: romerov
 * Last updated: Jan 15, 2019 5:07:03 PM
 */
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TAXPAYER_UPDATE_REASON_HISTORY",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"TAXPAYER_UPDATE_REASON_ID", "TAXPAYER_ID"},
        name = "UC_TAXPAYER_UPDATE_REASON_HISTORY_01"))
public class TaxPayerReasonHistory implements Serializable {


  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "TAXPAYER_UPDATE_REASON_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "TAXPAYER_UPDATE_REASON_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_UPDATE_REASON"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_UPDATE_REASON_ID", unique = true, nullable = false)
  private Long id;

  /** The reason id. */
  @Column(name = "REASON_ID", length = 30, columnDefinition = "VARCHAR2(30 BYTE)")
  private String reasonId;

  /** The reason description. */
  @Column(name = "REASON_DESCRIPTION", columnDefinition = "VARCHAR2(170 BYTE)")
  private String reasonDescription;

  /** The remarks. */
  @Column(name = "REMARKS", columnDefinition = "VARCHAR2(500 BYTE)")
  private String remarks;

  /** The created by. */
  @Column(name = "CREATED_BY", nullable = false, columnDefinition = "VARCHAR2(50 BYTE)")
  private String createdBy;

  /** The created date. */
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE", nullable = false)
  private Date createdDate;
  
  @JsonIgnore
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", foreignKey = @ForeignKey(name = "FK_TAXPAYER_UPDATE_REASON_HISTORY__TAXPAYER"))
  private TaxPayer taxPayer;
  
  public TaxPayerReasonHistory() {
    super();
  }

  public TaxPayerReasonHistory(Long id, String reasonId, String reasonDescription, String remarks,
		String createdBy, Date createdDate, TaxPayer taxPayer) {
	super();
	this.id = id;
	this.reasonId = reasonId;
	this.reasonDescription = reasonDescription;
	this.remarks = remarks;
	this.createdBy = createdBy;
	this.createdDate = createdDate;
	this.taxPayer = taxPayer;
}

public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getReasonId() {
    return reasonId;
  }

  public void setReasonId(String reasonId) {
    this.reasonId = reasonId;
  }

  public String getReasonDescription() {
    return reasonDescription;
  }

  public void setReasonDescription(String reasonDescription) {
    this.reasonDescription = reasonDescription;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
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

public TaxPayer getTaxPayer() {
	return taxPayer;
}

public void setTaxPayer(TaxPayer taxPayer) {
	this.taxPayer = taxPayer;
}


}
