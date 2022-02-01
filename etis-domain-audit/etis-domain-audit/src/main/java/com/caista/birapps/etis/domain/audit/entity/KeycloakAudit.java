package com.caista.birapps.etis.domain.audit.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "KEYCLOAK_AUDIT")
public class KeycloakAudit implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -3995011299732288487L;

  @Id
  @Column(name = "REF_AUD_ID")
  private String refAuditId;

  @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR2(255)")
  private String description;

  @Column(name = "DETAILS", columnDefinition = "VARCHAR2(4000)")
  private String details;

  @Column(name = "REPRESENTATION", columnDefinition = "VARCHAR2(4000)")
  private String representation;



  public KeycloakAudit() {
    super();
    this.refAuditId = UUID.randomUUID().toString();
  }



  public KeycloakAudit(String refAuditId, String description, String details,
      String representation) {
    super();
    this.refAuditId = refAuditId;
    this.description = description;
    this.details = details;
    this.representation = representation;
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



  public String getDetails() {
    return details;
  }



  public void setDetails(String details) {
    this.details = details;
  }



  public String getRepresentation() {
    return representation;
  }



  public void setRepresentation(String representation) {
    this.representation = representation;
  }



  public static long getSerialversionuid() {
    return serialVersionUID;
  }



  @Override
  public String toString() {
    return "KeycloakAudit [refAuditId=" + refAuditId + ", description=" + description + ", details="
        + details + ", representation=" + representation + "]";
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((details == null)
        ? 0
        : details.hashCode());
    result = prime * result + ((refAuditId == null)
        ? 0
        : refAuditId.hashCode());
    result = prime * result + ((representation == null)
        ? 0
        : representation.hashCode());
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
    KeycloakAudit other = (KeycloakAudit) obj;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (details == null) {
      if (other.details != null)
        return false;
    } else if (!details.equals(other.details))
      return false;
    if (refAuditId == null) {
      if (other.refAuditId != null)
        return false;
    } else if (!refAuditId.equals(other.refAuditId))
      return false;
    if (representation == null) {
      if (other.representation != null)
        return false;
    } else if (!representation.equals(other.representation))
      return false;
    return true;
  }



}
