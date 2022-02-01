/*
  * Modified by: obregoj
  * Last updated: Dec 4, 2019 10:02:40 AM
  */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintOfficeDesignationRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String designationId;
  private String officeTypeId;
  private String officeGroupId;
  private String officeServiceId;
  private String officeLtsGroupId;
  private String officeDivisionId;
  private Long officeId;
  private String officeSectionId;

  public String getDesignationId() {
    return designationId;
  }

  public void setDesignationId(String designationId) {
    this.designationId = designationId;
  }

  public String getOfficeTypeId() {
    return officeTypeId;
  }

  public void setOfficeTypeId(String officeTypeId) {
    this.officeTypeId = officeTypeId;
  }

  public String getOfficeGroupId() {
    return officeGroupId;
  }

  public void setOfficeGroupId(String officeGroupId) {
    this.officeGroupId = officeGroupId;
  }

  public String getOfficeServiceId() {
    return officeServiceId;
  }

  public void setOfficeServiceId(String officeServiceId) {
    this.officeServiceId = officeServiceId;
  }

  public String getOfficeLtsGroupId() {
    return officeLtsGroupId;
  }

  public void setOfficeLtsGroupId(String officeLtsGroupId) {
    this.officeLtsGroupId = officeLtsGroupId;
  }

  public String getOfficeDivisionId() {
    return officeDivisionId;
  }

  public void setOfficeDivisionId(String officeDivisionId) {
    this.officeDivisionId = officeDivisionId;
  }

  public Long getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Long officeId) {
    this.officeId = officeId;
  }

  public String getOfficeSectionId() {
    return officeSectionId;
  }

  public void setOfficeSectionId(String officeSectionId) {
    this.officeSectionId = officeSectionId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((designationId == null)
        ? 0
        : designationId.hashCode());
    result = prime * result + ((officeDivisionId == null)
        ? 0
        : officeDivisionId.hashCode());
    result = prime * result + ((officeGroupId == null)
        ? 0
        : officeGroupId.hashCode());
    result = prime * result + ((officeId == null)
        ? 0
        : officeId.hashCode());
    result = prime * result + ((officeLtsGroupId == null)
        ? 0
        : officeLtsGroupId.hashCode());
    result = prime * result + ((officeSectionId == null)
        ? 0
        : officeSectionId.hashCode());
    result = prime * result + ((officeServiceId == null)
        ? 0
        : officeServiceId.hashCode());
    result = prime * result + ((officeTypeId == null)
        ? 0
        : officeTypeId.hashCode());
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
    MaintOfficeDesignationRequest other = (MaintOfficeDesignationRequest) obj;
    if (designationId == null) {
      if (other.designationId != null)
        return false;
    } else if (!designationId.equals(other.designationId))
      return false;
    if (officeDivisionId == null) {
      if (other.officeDivisionId != null)
        return false;
    } else if (!officeDivisionId.equals(other.officeDivisionId))
      return false;
    if (officeGroupId == null) {
      if (other.officeGroupId != null)
        return false;
    } else if (!officeGroupId.equals(other.officeGroupId))
      return false;
    if (officeId == null) {
      if (other.officeId != null)
        return false;
    } else if (!officeId.equals(other.officeId))
      return false;
    if (officeLtsGroupId == null) {
      if (other.officeLtsGroupId != null)
        return false;
    } else if (!officeLtsGroupId.equals(other.officeLtsGroupId))
      return false;
    if (officeSectionId == null) {
      if (other.officeSectionId != null)
        return false;
    } else if (!officeSectionId.equals(other.officeSectionId))
      return false;
    if (officeServiceId == null) {
      if (other.officeServiceId != null)
        return false;
    } else if (!officeServiceId.equals(other.officeServiceId))
      return false;
    if (officeTypeId == null) {
      if (other.officeTypeId != null)
        return false;
    } else if (!officeTypeId.equals(other.officeTypeId))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "MaintOfficeDesignationRequest [designationId=" + designationId + ", officeTypeId="
        + officeTypeId + ", officeGroupId=" + officeGroupId + ", officeServiceId=" + officeServiceId
        + ", officeLtsGroupId=" + officeLtsGroupId + ", officeDivisionId=" + officeDivisionId
        + ", officeId=" + officeId + ", officeSectionId=" + officeSectionId + "]";
  }



}
