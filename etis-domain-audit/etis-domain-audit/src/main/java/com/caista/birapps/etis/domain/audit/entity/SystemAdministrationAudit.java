/*
  * Modified by: obregoj
  * Last updated: Sep 24, 2019 7:15:59 PM
  */
package com.caista.birapps.etis.domain.audit.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

/**
 * @author sarmier
 *
 */
@Entity
@Table(name = "SYSAD_AUDIT")
public class SystemAdministrationAudit implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 3241647787574460278L;

  @Id
  @Column(name = "REF_AUD_ID")
  private String refAuditId;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "NEW_VALUE", nullable = false)
  @Lob
  private byte[] newValue;

  @Column(name = "OLD_VALUE", nullable = true)
  @Lob
  private byte[] oldValue;

  @Column(name = "OTHER_DETAILS", columnDefinition = "VARCHAR2(4000)")
  private String otherDetails;

  public SystemAdministrationAudit() {
    super();
    this.refAuditId = UUID.randomUUID().toString();
  }

  public SystemAdministrationAudit(String refAuditId, String description, byte[] newValue,
      byte[] oldValue, String otherDetails) {
    super();
    this.refAuditId = refAuditId;
    this.description = description;
    this.newValue = newValue;
    this.oldValue = oldValue;
    this.otherDetails = otherDetails;
  }


  public String getRefAuditId() {
    return refAuditId;
  }

  public void setRefAuditId(String refAuditId) {
    this.refAuditId = refAuditId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public byte[] getNewValue() {
    return newValue;
  }

  public void setNewValue(byte[] newValue) {
    this.newValue = newValue;
  }

  public byte[] getOldValue() {
    return oldValue;
  }

  public void setOldValue(byte[] oldValue) {
    this.oldValue = oldValue;
  }

  public String getOtherDetails() {
    return otherDetails;
  }

  public void setOtherDetails(String otherDetails) {
    this.otherDetails = otherDetails;
  }


  @Override
  public String toString() {
    return "SystemAdministrationAudit [refAuditId=" + refAuditId + ", description=" + description
        + ", newValue=" + newValue + ", oldValue=" + oldValue + ", otherDetails=" + otherDetails
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((newValue == null)
        ? 0
        : newValue.hashCode());
    result = prime * result + ((oldValue == null)
        ? 0
        : oldValue.hashCode());
    result = prime * result + ((otherDetails == null)
        ? 0
        : otherDetails.hashCode());
    result = prime * result + ((refAuditId == null)
        ? 0
        : refAuditId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SystemAdministrationAudit other = (SystemAdministrationAudit) obj;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (newValue == null) {
      if (other.newValue != null)
        return false;
    } else if (!newValue.equals(other.newValue))
      return false;
    if (oldValue == null) {
      if (other.oldValue != null)
        return false;
    } else if (!oldValue.equals(other.oldValue))
      return false;
    if (otherDetails == null) {
      if (other.otherDetails != null)
        return false;
    } else if (!otherDetails.equals(other.otherDetails))
      return false;
    if (refAuditId == null) {
      if (other.refAuditId != null)
        return false;
    } else if (!refAuditId.equals(other.refAuditId))
      return false;
    return true;
  }



}
