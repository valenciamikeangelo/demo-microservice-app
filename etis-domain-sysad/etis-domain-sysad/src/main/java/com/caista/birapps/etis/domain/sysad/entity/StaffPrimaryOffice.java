/*
  * Modified by: obregoj
  * Last updated: 08 12, 20 6:00:15 PM
  */
package com.caista.birapps.etis.domain.sysad.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class StaffAssignment.
 */
@Entity
@Table(name = "STAFF_PRIMARY_OFFICE",
    uniqueConstraints = @UniqueConstraint(columnNames = {"USERNAME"},
        name = "UC_STAFF_PRIMARY_OFFICE_01"),
    indexes = {@Index(columnList = "OFFICE_ID", name = "idx_STAFF_PRIMARY_OFFICE_01")})
@JsonInclude(Include.NON_NULL)
public class StaffPrimaryOffice implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The staff assignment id. */
  @Id
  @GeneratedValue(generator = "STAFF_PRMRY_OFFICE_SequenceStyleGenerator")
  @GenericGenerator(name = "STAFF_PRMRY_OFFICE_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_STAFF_PRMRY_OFFICE"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "STAFF_PRIMARY_OFFICE_ID")
  private Long id;


  /** The primary office. */
  @OneToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__STAFF_PRIMARY_OFFICE_01"))
  private Office primaryOffice;

  @Column(name = "USERNAME", updatable = false, nullable = false, columnDefinition = "VARCHAR(50)")
  private String username;

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

  @Column(name = "FIRST_NAME", columnDefinition = "VARCHAR(50)", nullable = false)
  private String firstName;

  @Column(name = "LAST_NAME", columnDefinition = "VARCHAR(50)", nullable = false)
  private String lastName;

  @Column(name = "MIDDLE_NAME", columnDefinition = "VARCHAR(50)", nullable = false)
  private String middleName;

  @Column(name = "SUFFIX", columnDefinition = "VARCHAR(5)")
  private String suffix;

  @Column(name = "ELAMS_OPEN_CASES", columnDefinition = "NUMBER(2,0)")
  private Long elamsOpenCases;

  @Transient
  private String displayName;

  public StaffPrimaryOffice() {
    super();
  }

  public StaffPrimaryOffice(Long id, String username) {
    super();
    this.username = username;
    this.id = id;
  }



  public StaffPrimaryOffice(Long id, String username, Office primaryOffice) {
    super();
    this.id = id;
    this.primaryOffice = primaryOffice;
    this.username = username;
  }

  public StaffPrimaryOffice(Long id, String username, String firstName, String lastName) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public StaffPrimaryOffice(Long id, String username, String firstName, String lastName,
      String middleName, String suffix, Office primaryOffice, String createdBy, String updatedBy,
      Long elamsOpenCases) {
    super();
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.suffix = suffix;
    this.primaryOffice = new Office(primaryOffice.getId(), primaryOffice.getCode(),
        primaryOffice.getLargeTaxpayerOfficeFlag(), primaryOffice.getDescription(),
        primaryOffice.getOfficeType());
    this.createdBy = createdBy;
    this.updatedBy = updatedBy;
    this.elamsOpenCases = elamsOpenCases;
  }

  public StaffPrimaryOffice(String username) {
    super();
    this.username = username;
  }

  public StaffPrimaryOffice(Long id) {
    super();
    this.id = id;
  }

  public StaffPrimaryOffice(Office primaryOffice) {
    super();
    this.primaryOffice = new Office(primaryOffice.getId(), primaryOffice.getCode(),
        primaryOffice.getLargeTaxpayerOfficeFlag(), primaryOffice.getDescription(),
        primaryOffice.getOfficeType());
  }

  public StaffPrimaryOffice(Long id, Office primaryOffice) {
    super();
    this.id = id;
    this.primaryOffice = new Office(primaryOffice.getId(), primaryOffice.getCode(),
        primaryOffice.getLargeTaxpayerOfficeFlag(), primaryOffice.getDescription(),
        primaryOffice.getOfficeType());
  }



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public String getFirstName() {
    return Objects.toString(firstName, "");
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return Objects.toString(lastName, "");
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public Office getPrimaryOffice() {
    return primaryOffice;
  }

  public void setPrimaryOffice(Office primaryOffice) {
    this.primaryOffice = primaryOffice;
  }

  public String getMiddleName() {
    return Objects.toString(middleName, "");
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getSuffix() {
    return Objects.toString(suffix, "");
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Long getElamsOpenCases() {
    return elamsOpenCases;
  }

  public void setElamsOpenCases(Long elamsOpenCases) {
    this.elamsOpenCases = elamsOpenCases;
  }

  public String getDisplayName() {
    displayName = this.getFirstName().concat(" " + this.getMiddleName())
        .concat(" " + this.getLastName()).concat(" " + this.getSuffix());
    return displayName.toUpperCase().replace("NULL", "").trim();
  }

  @Override
  public String toString() {
    return "StaffPrimaryOffice [id=" + id + ", primaryOffice=" + primaryOffice + ", username="
        + username + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
        + updatedBy + ", updatedDate=" + updatedDate + ", firstName=" + firstName + ", lastName="
        + lastName + ", middleName=" + middleName + ", suffix=" + suffix + ", elamsOpenCases="
        + elamsOpenCases + "]";
  }

}
