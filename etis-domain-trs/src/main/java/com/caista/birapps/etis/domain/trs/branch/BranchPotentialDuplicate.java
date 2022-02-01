/*
  * Modified by: bacosal
  * Last updated: Oct 11, 2018 5:58:16 PM
  */
package com.caista.birapps.etis.domain.trs.branch;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.caista.birapps.etis.domain.trs.entity.Address;
import com.caista.birapps.etis.domain.trs.utils.enums.ReferenceCodesEnum;
import com.caista.birapps.etis.domain.trs.utils.enums.TaxpayerTypeEnum;

public class BranchPotentialDuplicate {

  private Long taxpayerId;
  private String tin;
  private String branchCode;
  private String firstName;
  private String lastName;
  private String middleName;
  private String estateName;
  private String trustName;
  private String registeredName;
  private String suffixId;
  private String suffixDescription;
  private String taxpayerRegistrationType;
  private String primaryAddress;
  private String alternateAddress;
  private Date incorporationDate;
  private Date birthDate;
  private Date organizationDate;
  private Long officeId;
  private String officeDescription;
  private String dataSourceCreated;
  private String taxpayerTypeId;
  private String taxpayerClassificationId;
  private String fullName;

  private List<Address> addresses;

  public Long getTaxpayerId() {
    return taxpayerId;
  }

  public void setTaxpayerId(Long taxpayerId) {
    this.taxpayerId = taxpayerId;
  }
  public String getTin() {
    return tin;
  }
  public void setTin(String tin) {
    this.tin = tin;
  }

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }
  public String getRegisteredName() {
    return registeredName;
  }
  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
  }
  
  public String getFirstName() {
	return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getEstateName() {
		return estateName;
	}
	
	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}
	
	public String getTrustName() {
		return trustName;
	}
	
	public void setTrustName(String trustName) {
		this.trustName = trustName;
	}
	
	public String getSuffixId() {
		return suffixId;
	}
	
	public void setSuffixId(String suffixId) {
		this.suffixId = suffixId;
	}
	
	public String getSuffixDescription() {
		return suffixDescription;
	}
	
	public void setSuffixDescription(String suffixDescription) {
		this.suffixDescription = suffixDescription;
	}

	public String getTaxpayerRegistrationType() {
    return taxpayerRegistrationType;
  }

	 public void setTaxpayerRegistrationType(String taxpayerRegistrationType) {
	    this.taxpayerRegistrationType = taxpayerRegistrationType;
	  }
	 public String getPrimaryAddress() {
	    return primaryAddress;
	  }
	 public void setPrimaryAddress(String primaryAddress) {
	    this.primaryAddress = primaryAddress;
	  }
	 public String getAlternateAddress() {
	    return alternateAddress;
	  }
	 public void setAlternateAddress(String alternateAddress) {
	    this.alternateAddress = alternateAddress;
	  }
	 public Date getIncorporationDate() {
	    return incorporationDate;
	  }
	 public void setIncorporationDate(Date incorporationDate) {
	    this.incorporationDate = incorporationDate;
	  }
	
	 public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getOrganizationDate() {
		return organizationDate;
	}

	public void setOrganizationDate(Date organizationDate) {
		this.organizationDate = organizationDate;
	}

	public Long getOfficeId() {
	    return officeId;
	  }
	
	 public void setOfficeId(Long officeId) {
	    this.officeId = officeId;
	  }
	
	 public String getOfficeDescription() {
	    return officeDescription;
	  }
	
	 public void setOfficeDescription(String officeDescription) {
	    this.officeDescription = officeDescription;
	  }
	 public String getDataSourceCreated() {
	    return dataSourceCreated;
	  }
	 public void setDataSourceCreated(String dataSourceCreated) {
	    this.dataSourceCreated = dataSourceCreated;
	  }
	 
	 public List<Address> getAddresses() {
	    return addresses;
	  }
	 public void setAddresses(List<Address> addresses) {
	    this.addresses = addresses;
	  }
  
	 public String getTaxpayerTypeId() {
		return taxpayerTypeId;
	}
	
	public void setTaxpayerTypeId(String taxpayerTypeId) {
		this.taxpayerTypeId = taxpayerTypeId;
	}
	
	public String getTaxpayerClassificationId() {
		return taxpayerClassificationId;
	}

	public void setTaxpayerClassificationId(String taxpayerClassificationId) {
		this.taxpayerClassificationId = taxpayerClassificationId;
	}
	
	public String getFullName() {
		if(getTaxpayerClassificationId().equals(String.valueOf(ReferenceCodesEnum.TAXPAYERCLASSIFICATION_INDIVIDUAL.getId()))){
			if(getRegisteredName() == null){
				if(getTaxpayerTypeId().equals(String.valueOf(TaxpayerTypeEnum.ESTAFC))||getTaxpayerTypeId().equals(String.valueOf(TaxpayerTypeEnum.ESTAFN))){
					this.fullName = getEstateName();
				}else if(getTaxpayerTypeId().equals(String.valueOf(TaxpayerTypeEnum.TRUSTFC))||getTaxpayerTypeId().equals(String.valueOf(TaxpayerTypeEnum.TRUSTFN))){
					this.fullName = getTrustName();
				}else{
					this.fullName = checkFirstValue(getFirstName())+appendWithSpace(getMiddleName())+appendWithSpace(getLastName())+appendWithSpace(getSuffixDescription());
				}
			}else{
				this.fullName = getRegisteredName();
			}
		}else{
			this.fullName = getRegisteredName();
		}
		return fullName;
	  }

	public static String checkFirstValue(String str){
		  return StringUtils.isNotBlank(str) 
			? str : "";
	  }
	public static String appendWithSpace(String str) {
	    return StringUtils.isNotBlank(str)
	        ? " " + str
	        : "";
	  }

	  public static String concatWithSpaceAndComma(String str) {
	    return StringUtils.isNotBlank(str)
	        ? ", " + str
	        : "";
	  }

}
