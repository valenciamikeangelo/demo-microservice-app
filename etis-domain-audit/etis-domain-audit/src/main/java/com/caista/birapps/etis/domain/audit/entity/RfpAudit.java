/*
  * Modified by: logronj
  * Last updated: Aug 29, 2019 1:54:59 PM
  */
package com.caista.birapps.etis.domain.audit.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name = "RFP_AUDIT")
public class RfpAudit implements Serializable {

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

  public RfpAudit() {
    super();
    this.refAuditId = UUID.randomUUID().toString();
  }


  public RfpAudit(String refAuditId, String description, byte[] newValue, byte[] oldValue,
      String otherDetails) {
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
    return new Gson().toJson(this);
  }



}
