/*
  * Modified by: logronj
  * Last updated: 01 7, 20 4:25:56 PM
  */
package com.caista.birapps.etis.domain.audit.entity.wrapper;

import java.io.Serializable;
import com.caista.birapps.etis.domain.audit.entity.AuditMainDetails;

public class AuditLog<T> implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = -1307229551015861164L;
  /**
   *
   */
  private AuditMainDetails auditMainDetails;
  private transient T subDetails;

  public AuditLog() {
    super();
  }

  public AuditLog(AuditMainDetails auditMainDetails, T subDetails) {
    super();
    this.auditMainDetails = auditMainDetails;
    this.subDetails = subDetails;
  }

  public AuditMainDetails getAuditMainDetails() {
    return auditMainDetails;
  }

  public void setAuditMainDetails(AuditMainDetails auditMainDetails) {
    this.auditMainDetails = auditMainDetails;
  }

  public T getSubDetails() {
    return subDetails;
  }

  public void setSubDetails(T subDetails) {
    this.subDetails = subDetails;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }


  @Override
  public String toString() {
    return "AuditLog [auditMainDetails=" + auditMainDetails + ", subDetails=" + subDetails + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((auditMainDetails == null)
        ? 0
        : auditMainDetails.hashCode());
    result = prime * result + ((subDetails == null)
        ? 0
        : subDetails.hashCode());
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
    AuditLog<?> other = (AuditLog<?>) obj;
    if (auditMainDetails == null) {
      if (other.auditMainDetails != null)
        return false;
    } else if (!auditMainDetails.equals(other.auditMainDetails))
      return false;
    if (subDetails == null) {
      if (other.subDetails != null)
        return false;
    } else if (!subDetails.equals(other.subDetails))
      return false;
    return true;
  }



}
