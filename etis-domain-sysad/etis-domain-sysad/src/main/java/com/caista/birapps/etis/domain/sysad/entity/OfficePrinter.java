/*
  * Modified by: obregoj
  * Last updated: Nov 20, 2019 9:15:38 AM
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
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferencePrinter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// TODO: Auto-generated Javadoc
/**
 * The Class OfficePrinter.
 */
@Entity
@Table(name = "OFFICE_PRINTER",
    uniqueConstraints = @UniqueConstraint(columnNames = {"OFFICE_ID", "PRINTER_ID"},
        name = "UC_OFFICE_PRINTER_01"),
    indexes = {@Index(columnList = "PRINTER_ID", name = "idx_OFFICE_PRINTER_01"),
        @Index(columnList = "OFFICE_ID", name = "idx_OFFICE_PRINTER_02")})
@JsonInclude(Include.NON_NULL)
public class OfficePrinter implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The office printer id. */
  @Id
  @GeneratedValue(generator = "OFFICE_PRINTER_SequenceStyleGenerator")
  @GenericGenerator(name = "OFFICE_PRINTER_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_OFFICE_PRINTER"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "OFFICE_PRINTER_ID")
  private Long id;

  /** The ref office. */
  @ManyToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__OFFICE_PRINTER_01"), updatable = false)
  private Office office;

  /** The ref printer. */
  @ManyToOne
  @JoinColumn(name = "PRINTER_ID", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "FK_REF_PRINTER__OFFICE_PRINTER_01"))
  private ReferencePrinter refPrinter;

  /** The created by. */
  @Column(name = "CREATED_BY", updatable = false, nullable = false,
      columnDefinition = "VARCHAR(50)")
  private String createdBy;

  /** The date created. */
  @Column(name = "CREATED_DATE", updatable = false, nullable = false,
      columnDefinition = "TIMESTAMP(6)")
  private Date createdDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR(50)")
  private String updatedBy;

  /** The date updated. */
  @Column(name = "UPDATED_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date updatedDate;

  /** The effectivity date. */
  @Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE")
  private Date effectiveDate;

  /** The expiry date. */
  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
  private Date expiryDate;


  /**
   * Instantiates a new office printer.
   */
  public OfficePrinter() {
    super();
  }

  /**
   * Instantiates a new office printer.
   *
   * @param id the office printer id
   * @param office the office
   * @param refPrinter the ref printer
   */
  public OfficePrinter(Long id, Office office, ReferencePrinter refPrinter) {
    super();
    this.id = id;
    this.office = office;
    this.refPrinter = refPrinter;
  }



  public OfficePrinter(Long id, ReferencePrinter refPrinter) {
    super();
    this.id = id;
    this.refPrinter = new ReferencePrinter(refPrinter.getId(), refPrinter.getCode(),
        refPrinter.getDescription());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Office getOffice() {
    return office;
  }

  public void setOffice(Office office) {
    this.office = office;
  }

  public ReferencePrinter getRefPrinter() {
    return refPrinter;
  }

  public void setRefPrinter(ReferencePrinter refPrinter) {
    this.refPrinter = refPrinter;
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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "OfficePrinter [id=" + id + ", office=" + office + ", refPrinter=" + refPrinter
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
        + ", updatedDate=" + updatedDate + ", effectiveDate=" + effectiveDate + ", expiryDate="
        + expiryDate + "]";
  }

}
