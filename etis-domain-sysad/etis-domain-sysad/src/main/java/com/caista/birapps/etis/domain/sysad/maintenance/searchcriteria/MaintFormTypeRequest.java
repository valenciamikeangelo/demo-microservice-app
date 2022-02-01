/*
 * Modified by: santojo
 * Last updated: Sep 17, 2018 10:26:31 AM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintFormTypeRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String name;
  private String createdBy;
  private String filingFrequency;
  private String version;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getFilingFrequency() {
    return filingFrequency;
  }

  public void setFilingFrequency(String filingFrequency) {
    this.filingFrequency = filingFrequency;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintFormTypeRequest [code=" + code + ", description=" + description + ", name=" + name
        + ", createdBy=" + createdBy + ", filingFrequency=" + filingFrequency + ", version="
        + version + "]";
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
    result = prime * result + ((filingFrequency == null)
        ? 0
        : filingFrequency.hashCode());
    result = prime * result + ((name == null)
        ? 0
        : name.hashCode());
    result = prime * result + ((version == null)
        ? 0
        : version.hashCode());
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
    MaintFormTypeRequest other = (MaintFormTypeRequest) obj;
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
    if (filingFrequency == null) {
      if (other.filingFrequency != null)
        return false;
    } else if (!filingFrequency.equals(other.filingFrequency))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (version == null) {
      if (other.version != null)
        return false;
    } else if (!version.equals(other.version))
      return false;
    return true;
  }

}
