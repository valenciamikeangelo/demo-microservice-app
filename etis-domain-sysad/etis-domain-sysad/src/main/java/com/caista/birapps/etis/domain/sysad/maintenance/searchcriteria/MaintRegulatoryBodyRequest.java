/*
  * Modified by: obregoj
  * Last updated: Sep 17, 2018 4:10:46 PM
  */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintRegulatoryBodyRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String taxpayerClassificationCode;


  public MaintRegulatoryBodyRequest() {
    super();
  }


  public MaintRegulatoryBodyRequest(String code, String description,
      String taxpayerClassificationCode) {
    super();
    this.code = code;
    this.description = description;
    this.taxpayerClassificationCode = taxpayerClassificationCode;
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



  public String getTaxpayerClassificationCode() {
    return taxpayerClassificationCode;
  }


  public void setTaxpayerClassificationCode(String taxpayerClassificationCode) {
    this.taxpayerClassificationCode = taxpayerClassificationCode;
  }


  public static long getSerialversionuid() {
    return serialVersionUID;
  }


  @Override
  public String toString() {
    return "MaintRegulatoryBodyRequest [code=" + code + ", description=" + description
        + ", taxpayerClassificationCode=" + taxpayerClassificationCode + "]";
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
    result = prime * result + ((taxpayerClassificationCode == null)
        ? 0
        : taxpayerClassificationCode.hashCode());
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
    MaintRegulatoryBodyRequest other = (MaintRegulatoryBodyRequest) obj;
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
    if (taxpayerClassificationCode == null) {
      if (other.taxpayerClassificationCode != null)
        return false;
    } else if (!taxpayerClassificationCode.equals(other.taxpayerClassificationCode))
      return false;
    return true;
  }

}
