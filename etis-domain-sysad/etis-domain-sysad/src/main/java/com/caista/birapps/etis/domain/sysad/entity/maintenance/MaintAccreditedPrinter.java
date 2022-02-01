/*
  * Modified by: obregoj
  * Last updated: Nov 20, 2019 9:11:33 AM
  */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.entity.Office;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_ACCREDITED_PRINTER")
@JsonInclude(Include.NON_NULL)
public class MaintAccreditedPrinter implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "ACCP", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "ACCREDITED_PRINTER_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @ManyToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE_MAIN_ACCREDITED_PRINTER_01"))
  private Office office;

  @Column(name = "PRINTER_TIN", nullable = false, columnDefinition = "VARCHAR2(20)")
  private String printerTin;

  @Column(name = "PRINTER_NAME", columnDefinition = "VARCHAR2(80)")
  private String printerName;

  @Column(name = "BUSINESS_NAME", columnDefinition = "VARCHAR2(80)")
  private String businessName;

  @Column(name = "ADDRESS", columnDefinition = "VARCHAR2(255)")
  private String address;

  @Column(name = "ACCREDITATION_NUMBER", columnDefinition = "VARCHAR2(19)")
  private String accreditationNumber;

  @Column(name = "ACCREDITATION_DATE", columnDefinition = "DATE")
  private Date accreditationDate;

  @Column(name = "CREATED_BY", columnDefinition = "VARCHAR2(20)")
  private String createdBy;

  @Column(name = "UPDATED_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date updatedDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE")
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
  private Date expiryDate;

  @Column(name = "EMAIL", columnDefinition = "VARCHAR2(80)")
  private String email;

  @Column(name = "CONTACT_NUMBER", columnDefinition = "VARCHAR2(80)")
  private String contactNumber;

  public MaintAccreditedPrinter() {
    super();
  }



  public MaintAccreditedPrinter(String id, String printerTin, String printerName,
      String businessName, String address, String accreditationNumber, Date accreditationDate) {
    super();
    this.id = id;
    this.printerTin = printerTin;
    this.printerName = printerName;
    this.businessName = businessName;
    this.address = address;
    this.accreditationNumber = accreditationNumber;
    this.accreditationDate = accreditationDate;
  }


  @Override
  public String getId() {
    return id;
  }

  public String getPrinterTin() {
    return printerTin;
  }

  public void setPrinterTin(String printerTin) {
    this.printerTin = printerTin;
  }

  public String getPrinterName() {
    return printerName;
  }

  public void setPrinterName(String printerName) {
    this.printerName = printerName;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAccreditationNumber() {
    return accreditationNumber;
  }

  public void setAccreditationNumber(String accreditationNumber) {
    this.accreditationNumber = accreditationNumber;
  }

  public Date getAccreditationDate() {
    return accreditationDate;
  }

  public void setAccreditationDate(Date accreditationDate) {
    this.accreditationDate = accreditationDate;
  }

  @Override
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  @Override
  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public Office getOffice() {
    return office;
  }

  public void setOffice(Office office) {
    this.office = office;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintAccreditedPrinter [id=" + id + ", printerTin=" + printerTin + ", printerName="
        + printerName + ", businessName=" + businessName + ", address=" + address
        + ", accreditationNumber=" + accreditationNumber + ", accreditationDate="
        + accreditationDate + ", createdBy=" + createdBy + ", updatedDate=" + updatedDate
        + ", updatedBy=" + updatedBy + ", effectiveDate=" + effectiveDate + ", expiryDate="
        + expiryDate + ", email=" + email + ", contactNumber=" + contactNumber + ", office="
        + office + "]";
  }


}
