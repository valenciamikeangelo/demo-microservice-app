/*
  * Modified by: obregoj
  * Last updated: Sep 17, 2018 2:39:34 PM
  */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintAlphanumericTaxCodeRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String formType;
  private String atcdtlCode;
  private String sgcaCode;
  private String atcType;
  private String uom;
  private String gpcCode;
  private String rep1209Rowcode;
  private String rep1209Schcode;

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

  public String getFormType() {
    return formType;
  }

  public void setFormType(String formType) {
    this.formType = formType;
  }

  public String getAtcdtlCode() {
    return atcdtlCode;
  }

  public void setAtcdtlCode(String atcdtlCode) {
    this.atcdtlCode = atcdtlCode;
  }

  public String getSgcaCode() {
    return sgcaCode;
  }

  public void setSgcaCode(String sgcaCode) {
    this.sgcaCode = sgcaCode;
  }

  public String getAtcType() {
    return atcType;
  }

  public void setAtcType(String atcType) {
    this.atcType = atcType;
  }

  public String getUom() {
    return uom;
  }

  public void setUom(String uom) {
    this.uom = uom;
  }

  public String getGpcCode() {
    return gpcCode;
  }

  public void setGpcCode(String gpcCode) {
    this.gpcCode = gpcCode;
  }

  public String getRep1209Rowcode() {
    return rep1209Rowcode;
  }

  public void setRep1209Rowcode(String rep1209Rowcode) {
    this.rep1209Rowcode = rep1209Rowcode;
  }

  public String getRep1209Schcode() {
    return rep1209Schcode;
  }

  public void setRep1209Schcode(String rep1209Schcode) {
    this.rep1209Schcode = rep1209Schcode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintAlphanumericTaxCodeRequest [code=" + code + ", description=" + description
        + ", formType=" + formType + ", atcdtlCode=" + atcdtlCode + ", sgcaCode=" + sgcaCode
        + ", atcType=" + atcType + ", uom=" + uom + ", gpcCode=" + gpcCode + ", rep1209Rowcode="
        + rep1209Rowcode + ", rep1209Schcode=" + rep1209Schcode + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((atcType == null)
        ? 0
        : atcType.hashCode());
    result = prime * result + ((atcdtlCode == null)
        ? 0
        : atcdtlCode.hashCode());
    result = prime * result + ((code == null)
        ? 0
        : code.hashCode());
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((formType == null)
        ? 0
        : formType.hashCode());
    result = prime * result + ((gpcCode == null)
        ? 0
        : gpcCode.hashCode());
    result = prime * result + ((rep1209Rowcode == null)
        ? 0
        : rep1209Rowcode.hashCode());
    result = prime * result + ((rep1209Schcode == null)
        ? 0
        : rep1209Schcode.hashCode());
    result = prime * result + ((sgcaCode == null)
        ? 0
        : sgcaCode.hashCode());
    result = prime * result + ((uom == null)
        ? 0
        : uom.hashCode());
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
    MaintAlphanumericTaxCodeRequest other = (MaintAlphanumericTaxCodeRequest) obj;
    if (atcType == null) {
      if (other.atcType != null)
        return false;
    } else if (!atcType.equals(other.atcType))
      return false;
    if (atcdtlCode == null) {
      if (other.atcdtlCode != null)
        return false;
    } else if (!atcdtlCode.equals(other.atcdtlCode))
      return false;
    if (code == null) {
      if (other.code != null)
        return false;
    } else if (!code.equals(other.code))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (formType == null) {
      if (other.formType != null)
        return false;
    } else if (!formType.equals(other.formType))
      return false;
    if (gpcCode == null) {
      if (other.gpcCode != null)
        return false;
    } else if (!gpcCode.equals(other.gpcCode))
      return false;
    if (rep1209Rowcode == null) {
      if (other.rep1209Rowcode != null)
        return false;
    } else if (!rep1209Rowcode.equals(other.rep1209Rowcode))
      return false;
    if (rep1209Schcode == null) {
      if (other.rep1209Schcode != null)
        return false;
    } else if (!rep1209Schcode.equals(other.rep1209Schcode))
      return false;
    if (sgcaCode == null) {
      if (other.sgcaCode != null)
        return false;
    } else if (!sgcaCode.equals(other.sgcaCode))
      return false;
    if (uom == null) {
      if (other.uom != null)
        return false;
    } else if (!uom.equals(other.uom))
      return false;
    return true;
  }



}
