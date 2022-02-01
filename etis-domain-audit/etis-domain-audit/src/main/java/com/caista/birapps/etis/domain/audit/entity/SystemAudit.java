/*
  * Modified by: obregoj
  * Last updated: 01 16, 20 12:53:56 PM
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
@Table(name = "SYSTEM_AUDIT")
public class SystemAudit implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 3241647787574460278L;

  @Id
  @Column(name = "REF_AUD_ID")
  private String refAuditId;

  @Column(name = "DESCRIPTION", nullable = false)
  private String description;

  @Column(name = "CONTEXT_PATH", nullable = false, columnDefinition = "VARCHAR2(50)")
  private String contextPath;

  @Column(name = "SCHEMA", nullable = false, columnDefinition = "VARCHAR2(50)")
  private String schemaName;

  @Column(name = "USED_DDL", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String usedDdl;

  public SystemAudit() {
    super();
    this.refAuditId = UUID.randomUUID().toString();
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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }


  public String getSchemaName() {
    return schemaName;
  }


  public void setSchemaName(String schemaName) {
    this.schemaName = schemaName;
  }


  public String getUsedDdl() {
    return usedDdl;
  }


  public void setUsedDdl(String usedDdl) {
    this.usedDdl = usedDdl;
  }


  public String getContextPath() {
    return contextPath;
  }


  public void setContextPath(String contextPath) {
    this.contextPath = contextPath;
  }


  @Override
  public String toString() {
    return "SystemAudit [refAuditId=" + refAuditId + ", description=" + description
        + ", contextPath=" + contextPath + ", schemaName=" + schemaName + ", usedDdl=" + usedDdl
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((contextPath == null)
        ? 0
        : contextPath.hashCode());
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((refAuditId == null)
        ? 0
        : refAuditId.hashCode());
    result = prime * result + ((schemaName == null)
        ? 0
        : schemaName.hashCode());
    result = prime * result + ((usedDdl == null)
        ? 0
        : usedDdl.hashCode());
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
    SystemAudit other = (SystemAudit) obj;
    if (contextPath == null) {
      if (other.contextPath != null)
        return false;
    } else if (!contextPath.equals(other.contextPath))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (refAuditId == null) {
      if (other.refAuditId != null)
        return false;
    } else if (!refAuditId.equals(other.refAuditId))
      return false;
    if (schemaName == null) {
      if (other.schemaName != null)
        return false;
    } else if (!schemaName.equals(other.schemaName))
      return false;
    if (usedDdl == null) {
      if (other.usedDdl != null)
        return false;
    } else if (!usedDdl.equals(other.usedDdl))
      return false;
    return true;
  }



}
