/*
 * Modified by: santojo
 * Last updated: Sep 17, 2018 10:32:12 AM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintTaxFormTypeRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String taxTypeCode;
  private String formTypeCode;
  private String createdBy;

  public String getTaxTypeCode() {
    return taxTypeCode;
  }

  public void setTaxTypeCode(String taxTypeCode) {
    this.taxTypeCode = taxTypeCode;
  }

  public String getFormTypeCode() {
    return formTypeCode;
  }

  public void setFormTypeCode(String formTypeCode) {
    this.formTypeCode = formTypeCode;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintTaxFormTypeRequest [taxTypeCode=" + taxTypeCode + ", formTypeCode=" + formTypeCode
        + ", createdBy=" + createdBy + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((createdBy == null)
        ? 0
        : createdBy.hashCode());
    result = prime * result + ((formTypeCode == null)
        ? 0
        : formTypeCode.hashCode());
    result = prime * result + ((taxTypeCode == null)
        ? 0
        : taxTypeCode.hashCode());
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
    MaintTaxFormTypeRequest other = (MaintTaxFormTypeRequest) obj;
    if (createdBy == null) {
      if (other.createdBy != null)
        return false;
    } else if (!createdBy.equals(other.createdBy))
      return false;
    if (formTypeCode == null) {
      if (other.formTypeCode != null)
        return false;
    } else if (!formTypeCode.equals(other.formTypeCode))
      return false;
    if (taxTypeCode == null) {
      if (other.taxTypeCode != null)
        return false;
    } else if (!taxTypeCode.equals(other.taxTypeCode))
      return false;
    return true;
  }

}
