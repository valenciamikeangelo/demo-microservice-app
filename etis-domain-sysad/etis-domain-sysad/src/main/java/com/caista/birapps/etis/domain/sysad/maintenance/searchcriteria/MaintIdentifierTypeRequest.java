/*
 * Modified by: santojo
 * Last updated: Sep 17, 2018 10:26:36 AM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintIdentifierTypeRequest implements Serializable {

  private static final long serialVersionUID = 1L;
  private String code;
  private String description;
  private String createdBy;
  private String identifierCode;
  private String identifierName;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getIdentifierCode() {
    return identifierCode;
  }

  public void setIdentifierCode(String identifierCode) {
    this.identifierCode = identifierCode;
  }

  public String getIdentifierName() {
    return identifierName;
  }

  public void setIdentifierName(String identifierName) {
    this.identifierName = identifierName;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintIdentifierTypeRequest [code=" + code + ", description=" + description
        + ", createdBy=" + createdBy + ", identifierCode=" + identifierCode + ", identifierName="
        + identifierName + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null)
        ? 0
        : code.hashCode());
    result = prime * result + ((createdBy == null)
        ? 0
        : createdBy.hashCode());
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((identifierCode == null)
        ? 0
        : identifierCode.hashCode());
    result = prime * result + ((identifierName == null)
        ? 0
        : identifierName.hashCode());
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
    MaintIdentifierTypeRequest other = (MaintIdentifierTypeRequest) obj;
    if (code == null) {
      if (other.code != null)
        return false;
    } else if (!code.equals(other.code))
      return false;
    if (createdBy == null) {
      if (other.createdBy != null)
        return false;
    } else if (!createdBy.equals(other.createdBy))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (identifierCode == null) {
      if (other.identifierCode != null)
        return false;
    } else if (!identifierCode.equals(other.identifierCode))
      return false;
    if (identifierName == null) {
      if (other.identifierName != null)
        return false;
    } else if (!identifierName.equals(other.identifierName))
      return false;
    return true;
  }

}
