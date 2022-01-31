/*
 * Modified by: gonzalm
 * Last updated: 11 25, 19 5:26:36 PM
*/
package com.caista.birapps.etis.common.report;

import java.math.BigDecimal;
import java.util.Date;

public class StatementOfAccountDetailsParam {

  private Long ledgerDetailId;
  private String taxType;
  private Date returnPeriod;
  private Date referenceTransactionDate;
  private String transactionType;
  private String transactionId;
  private BigDecimal debit;
  private BigDecimal credit;
  private BigDecimal balance;
  private BigDecimal previousBalance;

  public StatementOfAccountDetailsParam() {
    super();
  }

  public StatementOfAccountDetailsParam(Long ledgerDetailId, String taxType,
      BigDecimal previousBalance,
      Date returnPeriod, Date referenceTransactionDate, String transactionType,
      String transactionId, BigDecimal debit, BigDecimal credit, BigDecimal balance) {
    super();
    this.ledgerDetailId = ledgerDetailId;
    this.taxType = taxType;
    this.previousBalance = previousBalance;
    this.returnPeriod = returnPeriod;
    this.referenceTransactionDate = referenceTransactionDate;
    this.transactionType = transactionType;
    this.transactionId = transactionId;
    this.debit = debit;
    this.credit = credit;
    this.balance = balance;
  }

  public Long getLedgerDetailId() {
    return ledgerDetailId;
  }

  public void setLedgerDetailId(Long ledgerDetailId) {
    this.ledgerDetailId = ledgerDetailId;
  }

  public String getTaxType() {
    return taxType;
  }

  public void setTaxType(String taxType) {
    this.taxType = taxType;
  }

  public BigDecimal getPreviousBalance() {
    return previousBalance;
  }

  public void setPreviousBalance(BigDecimal previousBalance) {
    this.previousBalance = previousBalance;
  }

  public Date getReturnPeriod() {
    return returnPeriod;
  }

  public void setReturnPeriod(Date returnPeriod) {
    this.returnPeriod = returnPeriod;
  }

  public Date getReferenceTransactionDate() {
    return referenceTransactionDate;
  }

  public void setReferenceTransactionDate(Date referenceTransactionDate) {
    this.referenceTransactionDate = referenceTransactionDate;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

}
