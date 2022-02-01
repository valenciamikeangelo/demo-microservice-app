/*
  * Modified by: obregoj
  * Last updated: 06 2, 20 5:58:56 PM
  */
package com.caista.birapps.etis.domain.sysad.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.sysad.util.SysadAudit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "UPLOAD_SIGNATURE")
@JsonInclude(Include.NON_NULL)
public class UploadSignature implements Serializable, SysadAudit {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "UPLOAD_SIGNATURE_SequenceStyleGenerator")
  @GenericGenerator(name = "UPLOAD_SIGNATURE_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_UPLOAD_SIGNATURE"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "UPLOAD_SIGNATURE_ID")
  private Long id;

  @OneToOne
  @JoinColumn(name = "STAFF_PRIMARY_OFFICE_ID", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "FK_STAFF_PRIMARY_OFFICE__UPLOAD_SIGNATURE_01"))
  private StaffPrimaryOffice primaryOffice;

  @Column(name = "FILE_NAME", columnDefinition = "VARCHAR(80)", nullable = false)
  private String fileName;

  @Column(name = "FILE_IMAGE", nullable = false)
  @Lob
  private byte[] fileImage;

  @Column(name = "UPLOADER_FIRST_NAME", columnDefinition = "VARCHAR(20)", nullable = false)
  private String uploaderFirstName;

  @Column(name = "UPLOADER_LAST_NAME", columnDefinition = "VARCHAR(20)", nullable = false)
  private String uploaderLastName;

  @Column(name = "UPLOADED_DATE", columnDefinition = "TIMESTAMP(6)", nullable = false)
  private Date uploadedDate;

  @Transient
  private String signatoryName;


  public UploadSignature() {
    super();
  }

  public UploadSignature(Long id, String fileName, StaffPrimaryOffice primaryOffice) {
    this.id = id;
    this.fileName = fileName;
    this.primaryOffice = primaryOffice;
  }

  public UploadSignature(StaffPrimaryOffice primaryOffice) {
    super();
    this.primaryOffice = new StaffPrimaryOffice(primaryOffice.getId(), primaryOffice.getUsername(),
        primaryOffice.getFirstName(), primaryOffice.getLastName(), primaryOffice.getMiddleName(),
        primaryOffice.getSuffix(), primaryOffice.getPrimaryOffice(), primaryOffice.getCreatedBy(),
        primaryOffice.getUpdatedBy(), primaryOffice.getElamsOpenCases());
  }

  public UploadSignature(Long id, String fileName, byte[] fileImage, String uploaderFirstName,
      String uploaderLastName, Date uploadedDate, StaffPrimaryOffice primaryOffice) {
    super();
    this.id = id;
    this.fileName = fileName;
    this.fileImage = fileImage;
    this.uploaderFirstName = uploaderFirstName;
    this.uploaderLastName = uploaderLastName;
    this.uploadedDate = uploadedDate;
    this.primaryOffice = new StaffPrimaryOffice(primaryOffice.getId(), primaryOffice.getUsername(),
        primaryOffice.getFirstName(), primaryOffice.getLastName(), primaryOffice.getMiddleName(),
        primaryOffice.getSuffix(), primaryOffice.getPrimaryOffice(), primaryOffice.getCreatedBy(),
        primaryOffice.getUpdatedBy(), primaryOffice.getElamsOpenCases());
  }

  public UploadSignature(Long id, String fileName, byte[] fileImage, String uploaderFirstName,
      String uploaderLastName, Date uploadedDate) {
    this.id = id;
    this.fileName = fileName;
    this.fileImage = fileImage;
    this.uploaderFirstName = uploaderFirstName;
    this.uploaderLastName = uploaderLastName;
    this.uploadedDate = uploadedDate;
  }

  public UploadSignature(Long id, String fileName, String uploaderFirstName,
      String uploaderLastName, Date uploadedDate, StaffPrimaryOffice primaryOffice) {
    this.id = id;
    this.fileName = fileName;
    this.uploaderFirstName = uploaderFirstName;
    this.uploaderLastName = uploaderLastName;
    this.uploadedDate = uploadedDate;
    this.primaryOffice = new StaffPrimaryOffice(primaryOffice.getId(), primaryOffice.getUsername(),
        primaryOffice.getFirstName(), primaryOffice.getLastName());
  }


  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public byte[] getFileImage() {
    return fileImage;
  }

  public void setFileImage(byte[] fileImage) {
    this.fileImage = fileImage;
  }


  public StaffPrimaryOffice getPrimaryOffice() {
    return primaryOffice;
  }

  public void setPrimaryOffice(StaffPrimaryOffice primaryOffice) {
    this.primaryOffice = primaryOffice;
  }

  public String getUploaderFirstName() {
    return uploaderFirstName;
  }

  public void setUploaderFirstName(String uploaderFirstName) {
    this.uploaderFirstName = uploaderFirstName;
  }

  public String getUploaderLastName() {
    return uploaderLastName;
  }

  public void setUploaderLastName(String uploaderLastName) {
    this.uploaderLastName = uploaderLastName;
  }

  public Date getUploadedDate() {
    return uploadedDate;
  }

  public void setUploadedDate(Date uploadedDate) {
    this.uploadedDate = uploadedDate;
  }

  @Override
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSignatoryName() {
    return signatoryName;
  }

  public void setSignatoryName(String signatoryName) {
    this.signatoryName = signatoryName;
  }


  @Override
  public String getCreatedBy() {
    return this.uploaderFirstName + " " + this.uploaderLastName;
  }

  @Override
  public String getUpdatedBy() {
    return this.uploaderFirstName + " " + this.uploaderLastName;
  }

  @Override
  public String toString() {
    return "UploadSignature [id=" + id + ", primaryOffice=" + primaryOffice + ", fileName="
        + fileName + ", fileImage=" + Arrays.toString(fileImage) + ", uploaderFirstName="
        + uploaderFirstName + ", uploaderLastName=" + uploaderLastName + ", uploadedDate="
        + uploadedDate + ", signatoryName=" + signatoryName + "]";
  }



}
