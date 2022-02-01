/*
  * Modified by: obregoj
  * Last updated: Nov 20, 2019 9:15:21 AM
  */
package com.caista.birapps.etis.domain.sysad.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceContactType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "OFFICE_CONTACT_DETAIL")
@JsonInclude(Include.NON_NULL)
public class OfficeContactDetail implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "OFFC_CNTCT_DTL_SequenceStyleGenerator")
  @GenericGenerator(name = "OFFC_CNTCT_DTL_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_OFFC_CNTCT_DTL"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "OFFICE_CONTACT_DETAIL_ID", nullable = false, updatable = false)
  private Long id;


  @ManyToOne
  @JoinColumn(name = "CONTACT_TYPE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_REF_CONTACT_TYPE__OFFICE_CONTACT_DETAIL_01"))
  private ReferenceContactType contactType;

  @ManyToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__OFFICE_CONTACT_DETAIL_01"))
  private Office office;

  @Column(name = "CONTACT_DETAIL", nullable = false, columnDefinition = "VARCHAR(60)")
  private String details;

  @Column(name = "CREATED_BY", updatable = false, nullable = false,
      columnDefinition = "VARCHAR(20)")
  private String createdBy;

  @Column(name = "CREATED_DATE", updatable = false, nullable = false,
      columnDefinition = "TIMESTAMP(6)")
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date updatedDate;



  public OfficeContactDetail() {
    super();
  }


  public OfficeContactDetail(Long id, ReferenceContactType contactType, String details) {
    super();
    this.id = id;
    this.contactType = new ReferenceContactType(contactType.getId(), contactType.getCode(),
        contactType.getDescription());
    this.details = details;
  }


  public OfficeContactDetail(Long id, String details) {
    super();
    this.id = id;
    this.details = details;
  }



  public Long getId() {
    return id;
  }



  public void setId(Long id) {
    this.id = id;
  }



  public String getDetails() {
    return details;
  }



  public void setDetails(String details) {
    this.details = details;
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



  public ReferenceContactType getContactType() {
    return contactType;
  }



  public void setContactType(ReferenceContactType contactType) {
    this.contactType = contactType;
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
    return "OfficeContactDetails [id=" + id + ", details=" + details + ", createdBy=" + createdBy
        + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + ", contactType=" + contactType + ", office=" + office + "]";
  }


}
