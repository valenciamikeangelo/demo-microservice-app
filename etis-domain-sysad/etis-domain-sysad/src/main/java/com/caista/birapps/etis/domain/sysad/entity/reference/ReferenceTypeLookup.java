/*
  * Modified by: logronj
  * Last updated: Sep 28, 2018 11:46:47 AM
*/
package com.caista.birapps.etis.domain.sysad.entity.reference;

import java.io.Serializable;
import javax.persistence.*;
import com.caista.birapps.etis.domain.sysad.enums.ReferenceTypeEnum;

@Entity
@Table(name = "REF_LOOKUP")
public class ReferenceTypeLookup implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  @Enumerated(EnumType.STRING)
  @Column(name = "REFERENCE_TYPE_CODE", updatable = false, nullable = false,
      columnDefinition = "VARCHAR(50)")
  private ReferenceTypeEnum code;

  @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(30)", nullable = false)
  private String description;

  @Column(name = "ID_PREFIX", columnDefinition = "VARCHAR(10)", nullable = false)
  private String idPrefix;

  public ReferenceTypeLookup() {
    super();
  }

  public ReferenceTypeLookup(ReferenceTypeEnum code, String description, String idPrefix) {
    super();
    this.code = code;
    this.description = description;
    this.idPrefix = idPrefix;
  }

  public ReferenceTypeEnum getCode() {
    return code;
  }

  public void setCode(ReferenceTypeEnum code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getIdPrefix() {
    return idPrefix;
  }

  public void setIdPrefix(String idPrefix) {
    this.idPrefix = idPrefix;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "ReferenceTypeLookup [code=" + code + ", description=" + description + ", idPrefix="
        + idPrefix + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null)
        ? 0
        : code.hashCode());
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((idPrefix == null)
        ? 0
        : idPrefix.hashCode());
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
    ReferenceTypeLookup other = (ReferenceTypeLookup) obj;
    if (code != other.code)
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (idPrefix == null) {
      if (other.idPrefix != null)
        return false;
    } else if (!idPrefix.equals(other.idPrefix))
      return false;
    return true;
  }



}
