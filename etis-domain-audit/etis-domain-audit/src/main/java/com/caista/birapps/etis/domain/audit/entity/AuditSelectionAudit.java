/*
 * Modified by: tolentf Last updated: Mar 24, 2020 6:22:24 PM
 */
package com.caista.birapps.etis.domain.audit.entity;

import java.util.*;
import javax.persistence.*;

// TODO: Auto-generated Javadoc
/**
 * The Class AuditSelectionAudit.
 */
@Entity
@Table(name = "AUDITSELECTION_AUDIT")
public class AuditSelectionAudit {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1216063325739471726L;

  /** The ref audit id. */
  @Id
  @Column(name = "REF_AUD_ID")
  private String refAuditId;

  /** The description. */
  @Column(name = "DESCRIPTION")
  private String description;

  /** The new value. */
  @Column(name = "NEW_VALUE", nullable = false)
  @Lob
  private byte[] newValue;

  /** The old value. */
  @Column(name = "OLD_VALUE", nullable = true)
  @Lob
  private byte[] oldValue;

  /** The other details. */
  @Column(name = "OTHER_DETAILS", columnDefinition = "VARCHAR2(4000)")
  private String otherDetails;

  /**
   * Instantiates a new audit selection audit.
   */
  public AuditSelectionAudit() {
    super();
    this.refAuditId = UUID.randomUUID().toString();
  }

  /**
   * Gets the ref audit id.
   *
   * @return the ref audit id
   */
  public String getRefAuditId() {
    return refAuditId;
  }

  /**
   * Sets the ref audit id.
   *
   * @param refAuditId the new ref audit id
   */
  public void setRefAuditId(String refAuditId) {
    this.refAuditId = refAuditId;
  }

  /**
   * Gets the description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description.
   *
   * @param description the new description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the new value.
   *
   * @return the new value
   */
  public byte[] getNewValue() {
    return newValue;
  }

  /**
   * Sets the new value.
   *
   * @param newValue the new new value
   */
  public void setNewValue(byte[] newValue) {
    this.newValue = newValue;
  }

  /**
   * Gets the old value.
   *
   * @return the old value
   */
  public byte[] getOldValue() {
    return oldValue;
  }

  /**
   * Sets the old value.
   *
   * @param oldValue the new old value
   */
  public void setOldValue(byte[] oldValue) {
    this.oldValue = oldValue;
  }

  /**
   * Gets the other details.
   *
   * @return the other details
   */
  public String getOtherDetails() {
    return otherDetails;
  }

  /**
   * Sets the other details.
   *
   * @param otherDetails the new other details
   */
  public void setOtherDetails(String otherDetails) {
    this.otherDetails = otherDetails;
  }



  /**
   * Gets the serialversionuid.
   *
   * @return the serialversionuid
   */
  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "AuditSelectionAudit [refAuditId=" + refAuditId + ", description=" + description
        + ", newValue=" + Arrays.toString(newValue) + ", oldValue=" + Arrays.toString(oldValue)
        + ", otherDetails=" + otherDetails + "]";
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + Arrays.hashCode(newValue);
    result = prime * result + Arrays.hashCode(oldValue);
    result = prime * result + ((otherDetails == null)
        ? 0
        : otherDetails.hashCode());
    result = prime * result + ((refAuditId == null)
        ? 0
        : refAuditId.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AuditSelectionAudit other = (AuditSelectionAudit) obj;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (!Arrays.equals(newValue, other.newValue))
      return false;
    if (!Arrays.equals(oldValue, other.oldValue))
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
