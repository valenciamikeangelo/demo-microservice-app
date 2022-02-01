/*
  * Modified by: romerov
  * Last updated: 05 20, 20 6:16:35 PM
*/
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.trs.utils.enums.*;
import com.fasterxml.jackson.annotation.*;

/**
 * The Class DocumentaryRequirement.
 */
@Entity
@Table(name = "TAXPAYER_ATTACHMENT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxpayerAttachment implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "TAXPAYER_ATTACHMENT_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "TAXPAYER_ATTACHMENT_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_ATTACHMENT"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_ATTACHMENT_ID", unique = true, nullable = false)
  private Long id;

  /** The date received. */
  @Column(name = "TAXPAYER_ATTACHMENT_DATE_RECEIVED", columnDefinition = "DATE")
  private Date dateReceived;

  @Column(name = "DOCUMENT_TYPE_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String documentTypeId;

  @Column(name = "DOCUMENT_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(260 BYTE)")
  private String documentTypeDescription;

  /** The others. */
  @Column(name = "TAXPAYER_ATTACHMENT_OTHERS", columnDefinition = "VARCHAR2(100 BYTE)")
  private String others;

  /** The reference. */
  @Column(name = "TAXPAYER_ATTACHMENT_REFERENCE", columnDefinition = "VARCHAR2(100 BYTE)")
  private String reference;

  /** The tp attachmet files. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ATTACHMENT_ID",
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_ATTACHMENT_FILE__TAXPAYER_ATTACHMENT_01"))
  private List<TaxpayerAttachmentFile> attachmentFiles;

  /** The remarks. */
  @Column(name = "REMARKS", columnDefinition = "VARCHAR2(500 BYTE)")
  private String remarks;

  /** The effectivity date. */
  @Column(name = "START_DATE", columnDefinition = "DATE")
  private Date startDate;

  /** The expiry date. */
  @Column(name = "END_DATE", columnDefinition = "DATE")
  private Date endDate;

  @Column(name = "CANCELLATION_DATE", columnDefinition = "DATE")
  private Date cancellationDate;

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
  @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE",
      nullable = false)
  private Date createdDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(50 BYTE)")
  private String updatedBy;

  /** The updated date. */
  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID",
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_ATTACHMENT__TAXPAYER_01"))
  private TaxPayer taxpayer;

  @Transient
  private String status;

  public TaxpayerAttachment() {
    super();
    this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
  }

  public TaxpayerAttachment(Long id, Date dateReceived, String documentTypeId,
      String documentTypeDescription, String others, String reference,
      List<TaxpayerAttachmentFile> attachmentFiles, String remarks, Date startDate, Date endDate,
      Date cancellationDate, String dataSourceCreated, String dataSourceUpdated, String createdBy,
      Date createdDate, String updatedBy, Date updatedDate, String status) {
    super();
    this.id = id;
    this.dateReceived = dateReceived;
    this.documentTypeId = documentTypeId;
    this.documentTypeDescription = documentTypeDescription;
    this.others = others;
    this.reference = reference;
    this.attachmentFiles = attachmentFiles;
    this.remarks = remarks;
    this.startDate = startDate;
    this.endDate = endDate;
    this.cancellationDate = cancellationDate;
    this.dataSourceCreated = dataSourceCreated;
    this.dataSourceUpdated = dataSourceUpdated;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getDateReceived() {
    return dateReceived;
  }

  public void setDateReceived(Date dateReceived) {
    this.dateReceived = dateReceived;
  }

  public String getDocumentTypeId() {
    return documentTypeId;
  }

  public void setDocumentTypeId(String documentTypeId) {
    this.documentTypeId = documentTypeId;
  }

  public String getDocumentTypeDescription() {
    return documentTypeDescription;
  }

  public void setDocumentTypeDescription(String documentTypeDescription) {
    this.documentTypeDescription = documentTypeDescription;
  }

  public String getOthers() {
    return others;
  }

  public void setOthers(String others) {
    this.others = others;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public List<TaxpayerAttachmentFile> getAttachmentFiles() {
    return attachmentFiles;
  }

  public void setAttachmentFiles(List<TaxpayerAttachmentFile> attachmentFiles) {
    this.attachmentFiles = attachmentFiles;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public TaxPayer getTaxpayer() {
    return taxpayer;
  }

  public void setTaxpayer(TaxPayer taxpayer) {
    this.taxpayer = taxpayer;
  }

  public String getStatus() {
    if (this.cancellationDate != null) {
      this.status = String.valueOf(TaxpayerAttachmentStatusEnum.CANCELLED);
    } else {
      this.status = String.valueOf(TaxpayerAttachmentStatusEnum.SUBMITTED);
    }
    return status;
  }

}
