/*
 * Modified by: santojo
 * Last updated: Sep 17, 2018 10:29:15 AM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintReceiptInvoiceRequest implements Serializable {

  private static final long serialVersionUID = 1L;
  private String code;
  private String description;

  private String createdBy;
  private String kindReceiptInvoice;

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

  public String getKindReceiptInvoice() {
    return kindReceiptInvoice;
  }

  public void setKindReceiptInvoice(String kindReceiptInvoice) {
    this.kindReceiptInvoice = kindReceiptInvoice;
  }

  @Override
  public String toString() {
    return "MaintReceiptInvoiceRequest [code=" + code + ", description=" + description
        + ", createdBy=" + createdBy + ", kindReceiptInvoice=" + kindReceiptInvoice + "]";
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
    result = prime * result + ((kindReceiptInvoice == null)
        ? 0
        : kindReceiptInvoice.hashCode());
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
    MaintReceiptInvoiceRequest other = (MaintReceiptInvoiceRequest) obj;
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
    if (kindReceiptInvoice == null) {
      if (other.kindReceiptInvoice != null)
        return false;
    } else if (!kindReceiptInvoice.equals(other.kindReceiptInvoice))
      return false;
    return true;
  }

}
