/*
  * Modified by: obregoj
  * Last updated: Sep 19, 2018 1:35:09 PM
  */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintBookOfAccountRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String createdBy;
  private String bookOfAccountTypeCode;

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



  public String getBookOfAccountTypeCode() {
    return bookOfAccountTypeCode;
  }

  public void setBookOfAccountTypeCode(String bookOfAccountTypeCode) {
    this.bookOfAccountTypeCode = bookOfAccountTypeCode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintBookOfAccountRequest [code=" + code + ", description=" + description
        + ", createdBy=" + createdBy + ", bookOfAccountTypeCode=" + bookOfAccountTypeCode + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bookOfAccountTypeCode == null)
        ? 0
        : bookOfAccountTypeCode.hashCode());
    result = prime * result + ((code == null)
        ? 0
        : code.hashCode());
    result = prime * result + ((createdBy == null)
        ? 0
        : createdBy.hashCode());
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
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
    MaintBookOfAccountRequest other = (MaintBookOfAccountRequest) obj;
    if (bookOfAccountTypeCode == null) {
      if (other.bookOfAccountTypeCode != null)
        return false;
    } else if (!bookOfAccountTypeCode.equals(other.bookOfAccountTypeCode))
      return false;
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
    return true;
  }

}
