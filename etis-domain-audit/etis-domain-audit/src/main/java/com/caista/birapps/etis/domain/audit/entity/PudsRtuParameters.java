/*
  * Modified by: decinam
  * Last updated: May 31, 2019 3:02:02 PM
  */
package com.caista.birapps.etis.domain.audit.entity;

import java.io.Serializable;
import java.util.*;

/**
 * @author decinam
 *
 */
public class PudsRtuParameters implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 3241647787574460278L;

  private String refAuditId;
  private String description;
  private List<Map<String, String>> parameters;

  public PudsRtuParameters() {
    super();
    this.refAuditId = UUID.randomUUID().toString();
  }

  public PudsRtuParameters(String refAuditId, String description,
      List<Map<String, String>> parameters) {
    super();
    this.refAuditId = refAuditId;
    this.description = description;
    this.parameters = parameters;
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

  public List<Map<String, String>> getParameters() {
    return parameters;
  }

  public void setParameters(List<Map<String, String>> parameters) {
    this.parameters = parameters;
  }

  @Override
  public String toString() {
    return "PudsRtuParameters [refAuditId=" + refAuditId + ", description=" + description
        + ", parameters=" + parameters + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((parameters == null)
        ? 0
        : parameters.hashCode());
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
    PudsRtuParameters other = (PudsRtuParameters) obj;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (parameters == null) {
      if (other.parameters != null)
        return false;
    } else if (!parameters.equals(other.parameters))
      return false;
    if (refAuditId == null) {
      if (other.refAuditId != null)
        return false;
    } else if (!refAuditId.equals(other.refAuditId))
      return false;
    return true;
  }



}
