/*
  * Modified by: logronj
  * Last updated: Nov 27, 2018 2:55:11 PM
*/

package com.caista.birapps.etis.domain.sysad.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.sysad.util.SysadAudit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "USER_SIGNATURE",
    uniqueConstraints = @UniqueConstraint(columnNames = {"UPLOAD_SIGNATURE_ID"},
        name = "UC_USER_SIGNATURE_01"),
    indexes = {@Index(columnList = "USER_SIGNATURE_ID", name = "IDX_USER_SIGNATURE_01")})
@JsonInclude(Include.NON_NULL)
public class UserSignature implements Serializable, SysadAudit {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "USER_SIGNATURE_SequenceStyleGenerator")
  @GenericGenerator(name = "USER_SIGNATURE_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_USER_SIGNATURE"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "USER_SIGNATURE_ID", nullable = false, updatable = false)
  private Long id;

  @OneToOne
  @JoinColumn(name = "UPLOAD_SIGNATURE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_UPLOAD_SIGNATURE__USER_SIGNATURE_01"))
  private UploadSignature uploadSignature;

  @Column(name = "CREATED_BY", updatable = false, columnDefinition = "VARCHAR2(20)", nullable = false)
  private String createdBy;

  @Column(name = "CREATED_DATE", updatable = false, nullable = false)
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  @Transient
  private String signatoryName;

  public UserSignature() {
    super();
  }

  public UserSignature(Long id, String createdBy, UploadSignature uploadSignature) {
    this.id = id;
    this.createdBy = createdBy;
    this.uploadSignature = new UploadSignature(uploadSignature.getId(),
        uploadSignature.getFileName(), uploadSignature.getPrimaryOffice());
  }

  public UserSignature(Long id, UploadSignature uploadSignature) {
    this.id = id;
    this.uploadSignature = new UploadSignature(uploadSignature.getId(),
        uploadSignature.getFileName(), uploadSignature.getFileImage(),
        uploadSignature.getUploaderFirstName(), uploadSignature.getUploaderLastName(),
        uploadSignature.getUploadedDate(), uploadSignature.getPrimaryOffice());
  }

  public UserSignature(Long id, UploadSignature uploadSignature, String createdBy, Date createdDate,
      String updatedBy, Date updatedDate) {
    this.id = id;
    this.uploadSignature = new UploadSignature(uploadSignature.getId(),
            uploadSignature.getFileName(), uploadSignature.getFileImage(),
            uploadSignature.getUploaderFirstName(), uploadSignature.getUploaderLastName(),
            uploadSignature.getUploadedDate(), uploadSignature.getPrimaryOffice());
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
  }


  @Override
  public Long getId() {
    return id;
  }

  @Override
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

  @Override
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

  public UploadSignature getUploadSignature() {
    return uploadSignature;
  }

  public void setUploadSignature(UploadSignature uploadSignature) {
    this.uploadSignature = uploadSignature;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getSignatoryName() {
    return signatoryName;
  }

  public void setSignatoryName(String signatoryName) {
    this.signatoryName = signatoryName;
  }

  @Override
  public String toString() {
    return "UserSignature [id=" + id + ", uploadSignature=" + uploadSignature + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + ", signatoryName=" + signatoryName + "]";
  }

}
