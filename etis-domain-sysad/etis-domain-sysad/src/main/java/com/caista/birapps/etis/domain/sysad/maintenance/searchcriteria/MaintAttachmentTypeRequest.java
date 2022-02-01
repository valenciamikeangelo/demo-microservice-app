/*
 * Modified by: santojo
 * Last updated: Sep 17, 2018 10:25:14 AM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintAttachmentTypeRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String createdBy;
  private String taxpayerClassificationCode;
  private String taxpayerTypeCode;

  public MaintAttachmentTypeRequest() {
    super();
  }

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

  public String getTaxpayerClassificationCode() {
    return taxpayerClassificationCode;
  }

  public void setTaxpayerClassificationCode(String taxpayerClassificationCode) {
    this.taxpayerClassificationCode = taxpayerClassificationCode;
  }

  public String getTaxpayerTypeCode() {
    return taxpayerTypeCode;
  }

  public void setTaxpayerTypeCode(String taxpayerTypeCode) {
    this.taxpayerTypeCode = taxpayerTypeCode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintAttachmentTypeRequest [code=" + code + ", description=" + description
        + ", createdBy=" + createdBy + ", taxpayerClassificationCode=" + taxpayerClassificationCode
        + ", taxpayerTypeCode=" + taxpayerTypeCode + "]";
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
    result = prime * result + ((taxpayerClassificationCode == null)
        ? 0
        : taxpayerClassificationCode.hashCode());
    result = prime * result + ((taxpayerTypeCode == null)
        ? 0
        : taxpayerTypeCode.hashCode());
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
    MaintAttachmentTypeRequest other = (MaintAttachmentTypeRequest) obj;
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
    if (taxpayerClassificationCode == null) {
      if (other.taxpayerClassificationCode != null)
        return false;
    } else if (!taxpayerClassificationCode.equals(other.taxpayerClassificationCode))
      return false;
    if (taxpayerTypeCode == null) {
      if (other.taxpayerTypeCode != null)
        return false;
    } else if (!taxpayerTypeCode.equals(other.taxpayerTypeCode))
      return false;
    return true;
  }

}
