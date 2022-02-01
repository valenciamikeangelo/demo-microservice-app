/*
  * Modified by: decinam
  * Last updated: Jul 15, 2019 2:58:54 PM
  */
package com.caista.birapps.etis.domain.audit.entity;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author decinam
 *
 */
public class PudsParameters implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 3241647787574460278L;

  private String refAuditId;
  private String description;
  private String batchId;
  private String periodFrom;
  private String periodTo;
  private String transaction;
  private String numberOfDays;

  public PudsParameters() {
    super();
    this.refAuditId = UUID.randomUUID().toString();
  }

  public PudsParameters(String refAuditId, String description, String batchId, String periodFrom,
      String periodTo, String transaction, String numberOfDays) {
    super();
    this.refAuditId = refAuditId;
    this.description = description;
    this.batchId = batchId;
    this.periodFrom = periodFrom;
    this.periodTo = periodTo;
    this.transaction = transaction;
    this.numberOfDays = numberOfDays;
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

  public String getBatchId() {
    return batchId;
  }

  public void setBatchId(String batchId) {
    this.batchId = batchId;
  }

  public String getPeriodFrom() {
    return periodFrom;
  }

  public void setPeriodFrom(String periodFrom) {
    this.periodFrom = periodFrom;
  }

  public String getPeriodTo() {
    return periodTo;
  }

  public void setPeriodTo(String periodTo) {
    this.periodTo = periodTo;
  }


  public String getTransaction() {
    return transaction;
  }

  public void setTransaction(String transaction) {
    this.transaction = transaction;
  }

  public String getNumberOfDays() {
    return numberOfDays;
  }

  public void setNumberOfDays(String numberOfDays) {
    this.numberOfDays = numberOfDays;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((batchId == null)
        ? 0
        : batchId.hashCode());
    result = prime * result + ((refAuditId == null)
        ? 0
        : refAuditId.hashCode());
    result = prime * result + ((periodFrom == null)
        ? 0
        : periodFrom.hashCode());
    result = prime * result + ((periodTo == null)
        ? 0
        : periodTo.hashCode());
    result = prime * result + ((transaction == null)
        ? 0
        : transaction.hashCode());
    result = prime * result + ((numberOfDays == null)
        ? 0
        : numberOfDays.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return "PudsParameters [refAuditId=" + refAuditId + ", description=" + description
        + ", batchId=" + batchId + ", periodFrom=" + periodFrom + ", periodTo=" + periodTo
        + ", transaction=" + transaction + ", numberOfDays=" + numberOfDays + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PudsParameters other = (PudsParameters) obj;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (batchId == null) {
      if (other.batchId != null)
        return false;
    } else if (!batchId.equals(other.batchId))
      return false;
    if (refAuditId == null) {
      if (other.refAuditId != null)
        return false;
    } else if (!refAuditId.equals(other.refAuditId))
      return false;
    if (periodFrom == null) {
      if (other.periodFrom != null)
        return false;
    } else if (!periodFrom.equals(other.periodFrom))
      return false;
    if (periodTo == null) {
      if (other.periodTo != null)
        return false;
    } else if (!periodTo.equals(other.periodTo))
      return false;
    if (transaction == null) {
      if (other.transaction != null)
        return false;
    } else if (!transaction.equals(other.transaction))
      return false;
    if (numberOfDays == null) {
      if (other.numberOfDays != null)
        return false;
    } else if (!numberOfDays.equals(other.numberOfDays))
      return false;
    return true;
  }



}
