/*
  * Modified by: obregoj
  * Last updated: 08 12, 20 12:42:22 PM
  */
package com.caista.birapps.etis.domain.object;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Immutable;
import org.springframework.util.ObjectUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */

@JsonInclude(Include.NON_NULL)
@Entity
@Immutable
@Table(name = "VW_USER_ENTITY_SUMMARY")
public class User implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "USER_ID", columnDefinition = "VARCHAR2(36)")
  private String id;

  /** The user name. */
  @Column(name = "USERNAME", columnDefinition = "NVARCHAR2(255)")
  private String username;

  /** The first name. */
  @Column(name = "FIRST_NAME", columnDefinition = "NVARCHAR2(255)")
  private String firstName;

  /** The last name. */
  @Column(name = "LAST_NAME", columnDefinition = "NVARCHAR2(255)")
  private String lastName;

  @Column(name = "MIDDLE_NAME", columnDefinition = "NVARCHAR2(255)")
  private String middleName;

  /** The display name. */
  @Transient
  private String displayName;

  @Column(name = "EMAIL", columnDefinition = "VARCHAR2(36)")
  private String email;

  @Transient
  private OfficeWrapper primaryOffice;

  @Column(name = "SUFFIX", columnDefinition = "NVARCHAR2(255)")
  private String suffix;

  @Column(name = "CREATED_DATE", columnDefinition = "VARCHAR2(40)")
  private Date createdDate;

  @Column(name = "OFFICE_ID", columnDefinition = "NUMBER(19)")
  private Long officeId;

  @Column(name = "OFFICE_CODE", columnDefinition = "VARCHAR2(20)")
  private String officeCode;

  @Column(name = "OFFICE_DESCRIPTION", columnDefinition = "VARCHAR2(80)")
  private String officeDescription;

  @Column(name = "STAFF_PRIMARY_OFFICE_ID", columnDefinition = "NUMBER(19)")
  private Long staffPrimaryOfficeId;

  @Column(name = "OPEN_CASES", columnDefinition = "NUMBER(19,0)")
  private Long openCases;

  /**
   * Instantiates a new user.
   */
  public User() {
    super();
  }

  public User(String id, String username, String firstName, String lastName, String middleName,
      String email, String suffix, Date createdDate) {
    super();
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.email = email;
    this.suffix = suffix;
    this.createdDate = createdDate;
  }



  public User(String username, String email) {
    this.username = username;
    this.email = email;
  }

  public String getId() {
    if (StringUtils.isEmpty(id))
      id = "";
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    if (StringUtils.isEmpty(username))
      username = "";
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    if (StringUtils.isEmpty(firstName))
      firstName = "";
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    if (StringUtils.isEmpty(lastName))
      lastName = "";
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleName() {
    if (StringUtils.isEmpty(middleName))
      middleName = "";
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getDisplayName() {
    displayName = firstName.concat(" " + this.getMiddleName()).concat(" " + this.getLastName())
        .concat(" " + this.getSuffix());
    return displayName.toUpperCase().replace("NULL", "").trim();
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getEmail() {
    if (StringUtils.isEmpty(email))
      email = "";
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public OfficeWrapper getPrimaryOffice() {
    if (!ObjectUtils.isEmpty(officeId)) {
      OfficeWrapper newOfficeWrapper = new OfficeWrapper();
      newOfficeWrapper.setId(officeId);
      newOfficeWrapper.setCode(officeCode);
      newOfficeWrapper.setDescription(officeDescription);
      newOfficeWrapper.setPrimaryOfficeId(staffPrimaryOfficeId);
      officeId = null;
      officeCode = null;
      officeDescription = null;
      staffPrimaryOfficeId = null;
      return newOfficeWrapper;
    } else {
      return primaryOffice;
    }
  }

  public void setPrimaryOffice(OfficeWrapper primaryOffice) {
    this.primaryOffice = primaryOffice;
  }

  public String getSuffix() {
    if (StringUtils.isEmpty(suffix))
      suffix = "";
    return suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Long getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Long officeId) {
    this.officeId = officeId;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getOfficeDescription() {
    return officeDescription;
  }

  public void setOfficeDescription(String officeDescription) {
    this.officeDescription = officeDescription;
  }

  public Long getStaffPrimaryOfficeId() {
    return staffPrimaryOfficeId;
  }

  public void setStaffPrimaryOfficeId(Long staffPrimaryOfficeId) {
    this.staffPrimaryOfficeId = staffPrimaryOfficeId;
  }

  public Long getOpenCases() {
    return openCases;
  }

  public void setOpenCases(Long openCases) {
    this.openCases = openCases;
  }



}
