/*
  * Modified by: adzuara
  * Last updated: Jan 3, 2019 12:57:19 PM
  */
package com.caista.birapps.etis.domain.trs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class GenerateSerialNumberParams {

  private String receiptInvoiceId;

  private String receiptInvoiceKindId;

  private String type;

  private String tradeName;
  
  private String specify;
  
  private Boolean manner;
  
  private String tin;
  
  private String branchCode;
  
  private String prefix;
  
  private String suffix;
  
  private Integer flag;

  public String getReceiptInvoiceId() {
    return receiptInvoiceId;
  }

  public void setReceiptInvoiceId(String receiptInvoiceId) {
    this.receiptInvoiceId = receiptInvoiceId;
  }

  public String getReceiptInvoiceKindId() {
    return receiptInvoiceKindId;
  }

	public void setReceiptInvoiceKindId(String receiptInvoiceKindId) {
		this.receiptInvoiceKindId = receiptInvoiceKindId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTradeName() {
    return tradeName;
  }

  public void setTradeName(String tradeName) {
    this.tradeName = tradeName;
  }

  
  public String getSpecify() {
	return specify;
}

public void setSpecify(String specify) {
	this.specify = specify;
}

public Boolean getManner() {
	return manner;
}

public void setManner(Boolean manner) {
	this.manner = manner;
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

public String getPrefix() {
	return prefix;
}

public void setPrefix(String prefix) {
	this.prefix = prefix;
}

public String getSuffix() {
	return suffix;
}

public void setSuffix(String suffix) {
	this.suffix = suffix;
}

public Integer getFlag() {
	return flag;
}

public void setFlag(Integer flag) {
	this.flag = flag;
}

@Override
  public String toString() {
    return "GenerateSerialNumberParams [receiptInvoiceId=" + receiptInvoiceId
        + ", receiptInvoiceDescription=" + receiptInvoiceKindId + ", type=" + type
        + ", tradeName=" + tradeName + ", manner=" + manner + ", specify=" + specify 
        + ", tin =" + tin + ",branchCode =" + branchCode + ",prefix =" + prefix 
        + ", suffix =" + suffix + " ]";
  }

}
