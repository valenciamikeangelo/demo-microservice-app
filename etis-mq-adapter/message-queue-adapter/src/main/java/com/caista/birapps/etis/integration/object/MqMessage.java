/*
 * Last modified by: gonzalm Last updated date: Jun 1, 2018 6:16:24 PM
 */
package com.caista.birapps.etis.integration.object;

import java.io.Serializable;

/**
 * The Class MqMessage.
 */
public class MqMessage implements Serializable {

  /** The id. */
  private String id;

  /** The username. */
  private String username;

  /** The ip address. */
  private String ipAddress;

  /** The transaction detail. */
  private String transactionDetail;

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new mq message.
   */
  public MqMessage() {
    super();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getTransactionDetail() {
    return transactionDetail;
  }

  public void setTransactionDetail(String transactionDetail) {
    this.transactionDetail = transactionDetail;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public MqMessage(String id, String username, String ipAddress, String transactionDetail) {
    super();
    this.id = id;
    this.username = username;
    this.ipAddress = ipAddress;
    this.transactionDetail = transactionDetail;
  }



}
