/**
 * 
 */
package com.caista.birapps.etis.common.utils.validation;

/**
 * @author valencm
 *
 */
public class ValidationDetail {

  private String fieldname;
  private String messageKey;
  private String message;

  public ValidationDetail(String fieldname, String messageKey) {
    this.fieldname = fieldname;
    this.messageKey = messageKey;
  }

  public ValidationDetail(String fieldname, String messageKey, String message) {
    this.fieldname = fieldname;
    this.messageKey = messageKey;
    this.message = message;
  }

  /**
   * @param fieldname the fieldname to set
   */
  public void setFieldname(String fieldname) {
    this.fieldname = fieldname;
  }

  /**
   * @param messageKey the messageKey to set
   */
  public void setMessageKey(String messageKey) {
    this.messageKey = messageKey;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * @return the fieldname
   */
  public String getFieldname() {
    return fieldname;
  }

  /**
   * @return the messageKey
   */
  public String getMessageKey() {
    return messageKey;
  }

}
