/*
 * Modified by: santojo
 * Last updated: Sep 17, 2018 10:25:31 AM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;

public class MaintBarangayRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String provinceCode;
  private String municipalityCode;

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

  public String getProvinceCode() {
    return provinceCode;
  }

  public void setProvinceCode(String provinceCode) {
    this.provinceCode = provinceCode;
  }

  public String getMunicipalityCode() {
    return municipalityCode;
  }

  public void setMunicipalityCode(String municipalityCode) {
    this.municipalityCode = municipalityCode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintBarangayRequest [code=" + code + ", description=" + description + ", provinceCode="
        + provinceCode + ", municipalityCode=" + municipalityCode + "]";
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
    result = prime * result + ((municipalityCode == null)
        ? 0
        : municipalityCode.hashCode());
    result = prime * result + ((provinceCode == null)
        ? 0
        : provinceCode.hashCode());
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
    MaintBarangayRequest other = (MaintBarangayRequest) obj;
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
    if (municipalityCode == null) {
      if (other.municipalityCode != null)
        return false;
    } else if (!municipalityCode.equals(other.municipalityCode))
      return false;
    if (provinceCode == null) {
      if (other.provinceCode != null)
        return false;
    } else if (!provinceCode.equals(other.provinceCode))
      return false;
    return true;
  }

}
