/*
 * Modified by: romerov
 * Last updated: Sep 16, 2019 8:47:55 AM
 */
package com.caista.birapps.etis.domain.audit.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ATPAudit.
 */
@Entity
@Table(name = "ATP_AUDIT")
public class ATPAudit implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 3241647787574460278L;

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

  /**
   * Instantiates a new ATP audit.
   */
  public ATPAudit() {
    super();
    this.refAuditId = UUID.randomUUID().toString();
  }

  /**
   * Instantiates a new ATP audit.
   *
   * @param refAuditId the ref audit id
   * @param description the description
   * @param newValue the new value
   * @param oldValue the old value
   */
  public ATPAudit(String refAuditId, String description, byte[] newValue, byte[] oldValue) {
    super();
    this.refAuditId = refAuditId;
    this.description = description;
    this.newValue = newValue;
    this.oldValue = oldValue;
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

  @Override
  public String toString() {
    return "ATPAudit [refAuditId=" + refAuditId + ", description=" + description + ", newValue="
        + Arrays.toString(newValue) + ", oldValue=" + Arrays.toString(oldValue) + "]";
  }



}
