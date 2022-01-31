/**
 * 
 */
package com.caista.birapps.etis.common.utils.exception;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author valencm
 *
 */
public class ApiErrorDetail {

  private String errorCode;
  private String errorDescription;
  private String displayArea;
  private String recommendedAction;

  public ApiErrorDetail(String errorCode, String errorDescription, String displayArea,
      String recommendedAction) {
    this.errorCode = errorCode;
    this.errorDescription = errorDescription;
    this.displayArea = displayArea;
    this.recommendedAction = recommendedAction;
  }

  /**
   * @return the errorCode
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * @param errorCode the errorCode to set
   */
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * @return the errorDescription
   */
  public String getErrorDescription() {
    return errorDescription;
  }

  /**
   * @param errorDescription the errorDescription to set
   */
  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }

  /**
   * @return the displayArea
   */
  public String getDisplayArea() {
    return displayArea;
  }

  /**
   * @param displayArea the displayArea to set
   */
  public void setDisplayArea(String displayArea) {
    this.displayArea = displayArea;
  }

  /**
   * @return the recommendedAction
   */
  public String getRecommendedAction() {
    return recommendedAction;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /**
   * @param recommendedAction the recommendedAction to set
   */
  public void setRecommendedAction(String recommendedAction) {
    this.recommendedAction = recommendedAction;
  }
}
