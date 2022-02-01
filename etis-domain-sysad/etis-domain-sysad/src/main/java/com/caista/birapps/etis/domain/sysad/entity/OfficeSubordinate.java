/*
  * Modified by: obregoj
  * Last updated: Nov 20, 2019 9:16:03 AM
  */
package com.caista.birapps.etis.domain.sysad.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// TODO: Auto-generated Javadoc
/**
 * The Class OfficeSubordinate.
 */
@Entity
@Table(name = "OFFICE_SUBORDINATE",
    uniqueConstraints = @UniqueConstraint(columnNames = {"OFFICE_ID", "SUB_OFFICE_ID"},
        name = "UC_OFFICE_SUBORDINATE_01"),
    indexes = {@Index(columnList = "OFFICE_ID", name = "idx_OFFICE_SUBORDINATE_01")})
@JsonInclude(Include.NON_NULL)
public class OfficeSubordinate implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "OFFICE_SUBORDINATE_SequenceStyleGenerator")
  @GenericGenerator(name = "OFFICE_SUBORDINATE_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_OFFICE_SUBORDINATE"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "OFFICE_SUBORDINATE_ID", nullable = false, updatable = false)
  private Long id;

  /** The ref office. */
  @ManyToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__OFFICE_SUBORDINATE_01"), updatable = false)
  private Office office;

  /** The sub office. */
  @ManyToOne
  @JoinColumn(name = "SUB_OFFICE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__OFFICE_SUBORDINATE_02"), updatable = false)
  private Office subOffice;

  /** The created by. */
  @Column(name = "CREATED_BY", updatable = false, nullable = false,
      columnDefinition = "VARCHAR(20)")
  private String createdBy;

  /** The created date. */
  @Column(name = "CREATED_DATE", updatable = false, nullable = false,
      columnDefinition = "TIMESTAMP(6)")
  private Date createdDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR(20)")
  private String updatedBy;

  /** The updated date. */
  @Column(name = "UPDATED_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date updatedDate;

  /**
   * Instantiates a new office subordinate.
   */
  public OfficeSubordinate() {
    super();
  }



  /**
   * Instantiates a new office subordinate.
   *
   * @param id the id
   * @param subOffice the sub office
   */
  public OfficeSubordinate(Long id, Office subOffice) {
    super();
    this.id = id;
    this.subOffice = new Office(subOffice.getId(), subOffice.getCode(),
        subOffice.getCaseOfficeFlag(), subOffice.getLargeTaxpayerOfficeFlag(),
        subOffice.getDescription(), subOffice.getRestrictRegAddFlag());
  }



  public Long getId() {
    return id;
  }



  public void setId(Long id) {
    this.id = id;
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



  public Office getOffice() {
    return office;
  }



  public void setOffice(Office office) {
    this.office = office;
  }



  public Office getSubOffice() {
    return subOffice;
  }



  public void setSubOffice(Office subOffice) {
    this.subOffice = subOffice;
  }



  public static long getSerialversionuid() {
    return serialVersionUID;
  }



  @Override
  public String toString() {
    return "OfficeSubordinate [id=" + id + ", office=" + office + ", subOffice=" + subOffice
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
        + ", updatedDate=" + updatedDate + "]";
  }


}
