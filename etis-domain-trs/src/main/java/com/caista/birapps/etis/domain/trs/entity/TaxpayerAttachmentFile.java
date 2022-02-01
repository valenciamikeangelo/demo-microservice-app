/*
 * Modified by: santojo
 * Last updated: Apr 29, 2019 4:14:58 PM
 */
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class DocumentaryRequirement.
 */
@Entity
@Table(name = "TAXPAYER_ATTACHMENT_FILE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxpayerAttachmentFile implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "TAXPAYER_ATTACHMENT_FILE_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "TAXPAYER_ATTACHMENT_FILE_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_ATTACHMENT_FILE"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_ATTACHMENT_FILE_ID", unique = true, nullable = false)
  private Long id;

  @Column(name = "TAXPAYER_ATTACHMENT_FILE_NAME", columnDefinition = "VARCHAR2(100 BYTE)")
  private String fileName;

  /** The attachment. */
  @Column(name = "TAXPAYER_ATTACHMENT_FILE_BYTE")
  @Lob
  private byte[] fileByte;

  @Column(name = "TAXPAYER_ATTACHMENT_FILE_SIZE", columnDefinition = "VARCHAR2(50 BYTE)")
  private String fileSize;

  @Column(name = "TAXPAYER_ATTACHMENT_FILE_TYPE", columnDefinition = "VARCHAR2(50 BYTE)")
  private String fileType;

  public TaxpayerAttachmentFile() {
    super();
  }

  public TaxpayerAttachmentFile(Long id, String fileName, byte[] fileByte, String fileSize,
      String fileType) {
    super();
    this.id = id;
    this.fileName = fileName;
    this.fileByte = fileByte;
    this.fileSize = fileSize;
    this.fileType = fileType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public byte[] getAttachment() {
    return fileByte;
  }

  public void setAttachment(byte[] fileByte) {
    this.fileByte = fileByte;
  }

  public String getFileSize() {
    return fileSize;
  }

  public void setFileSize(String fileSize) {
    this.fileSize = fileSize;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "TaxpayerAttachmentFile [id=" + id + ", fileName=" + fileName + ", fileSize=" + fileSize + ", fileType=" + fileType + "]";
  }

}
