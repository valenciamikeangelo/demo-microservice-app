/*
 * Modified by: fuentem
 * Last updated: Nov 13, 2018 9:14:50 PM
 */
package com.caista.birapps.etis.domain.trs.transfer;

import java.io.Serializable;
import java.util.List;

/**
 * The Class TransferResponse.
 */
public class TransferResponse implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The details. */
  private TransferDetails details;

  private String taskNumber;

  private Boolean isValid;

  private List<TransferEmailDetails> transferEmailDetails;

  public TransferResponse() {
    super();
  }

  public TransferResponse(TransferDetails transferDetails,
      List<TransferEmailDetails> emailDetails) {
    super();
    this.details = transferDetails;
    this.transferEmailDetails = emailDetails;
  }

  public TransferResponse(TransferDetails details) {
    super();
    this.details = details;
  }

  public TransferResponse(TransferDetails details, String taskNumber, Boolean isValid) {
    super();
    this.details = details;
    this.taskNumber = taskNumber;
    this.isValid = isValid;
  }

  public TransferDetails getDetails() {
    return details;
  }

  public void setDetails(TransferDetails details) {
    this.details = details;
  }

  public String getTaskNumber() {
    return taskNumber;
  }

  public void setTaskNumber(String taskNumber) {
    this.taskNumber = taskNumber;
  }

  public Boolean getIsValid() {
    return isValid;
  }

  public void setIsValid(Boolean isValid) {
    this.isValid = isValid;
  }

  public List<TransferEmailDetails> getTransferEmailDetails() {
    return transferEmailDetails;
  }

  public void setTransferEmailDetails(List<TransferEmailDetails> transferEmailDetails) {
    this.transferEmailDetails = transferEmailDetails;
  }


}
