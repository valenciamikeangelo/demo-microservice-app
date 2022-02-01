/*
 * Modified by: fuentem
 * Last updated: Nov 7, 2018 4:40:25 PM
 */
package com.caista.birapps.etis.domain.trs.atp;

import java.io.Serializable;

/**
 * The Class AuthoritytoPrintResponse.
 */
public class AuthoritytoPrintResponse implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The trade name. */
  private String tradeName;

  /** The outbound correspondence number. */
  private String outboundCorrespondenceNumber;

  /** The document locator number. */
  private String documentLocatorNumber;

  /** The transaction number. */
  private String transactionNumber;

  private String correspondence;
  
  private String taskNumber;
  
  private String tinBranchCode;

  /**
   * Instantiates a new authorityto print response.
   */
  public AuthoritytoPrintResponse() {
    super();
  }

  /**
   * Instantiates a new authorityto print response.
   *
   * @param tradeName the trade name
   * @param outboundCorrespondenceNumber the outbound correspondence number
   * @param documentLocatorNumber the document locator number
   * @param transactionNumber the transaction number
   */
  public AuthoritytoPrintResponse(String tinBranchCode, String tradeName,String transactionNumber) {
    super();
    this.tinBranchCode = tinBranchCode;
    this.tradeName = tradeName;        
    this.transactionNumber = transactionNumber;   
  }
  
  public AuthoritytoPrintResponse(String tinBranchCode, String tradeName,String transactionNumber, String taskNumber) {
    super();
    this.tinBranchCode = tinBranchCode;
    this.tradeName = tradeName;        
    this.transactionNumber = transactionNumber; 
    this.taskNumber = taskNumber;         
  }

  public AuthoritytoPrintResponse(String tradeName, String outboundCorrespondenceNumber,
      String documentLocatorNumber, String tinBranchCode, String transactionNumber, String correspondence) {
    super();
    this.tradeName = tradeName;
    this.outboundCorrespondenceNumber = outboundCorrespondenceNumber;
    this.documentLocatorNumber = documentLocatorNumber;
    this.tinBranchCode = tinBranchCode;
    this.transactionNumber = transactionNumber;
    this.correspondence = correspondence;
  }  

  public String getTradeName() {
    return tradeName;
  }

  public void setTradeName(String tradeName) {
    this.tradeName = tradeName;
  }

  public String getOutboundCorrespondenceNumber() {
    return outboundCorrespondenceNumber;
  }

  public void setOutboundCorrespondenceNumber(String outboundCorrespondenceNumber) {
    this.outboundCorrespondenceNumber = outboundCorrespondenceNumber;
  }

  public String getDocumentLocatorNumber() {
    return documentLocatorNumber;
  }

  public void setDocumentLocatorNumber(String documentLocatorNumber) {
    this.documentLocatorNumber = documentLocatorNumber;
  }

  public String getTransactionNumber() {
    return transactionNumber;
  }

  public void setTransactionNumber(String transactionNumber) {
    this.transactionNumber = transactionNumber;
  }

  public String getCorrespondence() {
    return correspondence;
  }

  public void setCorrespondence(String correspondence) {
    this.correspondence = correspondence;
  }

  public String getTaskNumber() {
	return taskNumber;
  }

  public void setTaskNumber(String taskNumber) {
	this.taskNumber = taskNumber;
  }

  public String getTinBranchCode() {
	return tinBranchCode;
  }
	
  public void setTinBranchCode(String tinBranchCode) {
	this.tinBranchCode = tinBranchCode;
  }

@Override
  public String toString() {
    return "AuthoritytoPrintResponse [tradeName=" + tradeName + ", outboundCorrespondenceNumber="
        + outboundCorrespondenceNumber + ", documentLocatorNumber=" + documentLocatorNumber
        + ", transactionNumber=" + transactionNumber + ", correspondence=" + correspondence + "]";
  }

}
