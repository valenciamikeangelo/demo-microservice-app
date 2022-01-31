/**
 * 
 */
package com.caista.birapps.etis.common.utils.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author valencm
 *
 */
public class ValidationResponse {

  private List<ValidationDetail> validationDetails;


  public ValidationResponse() {
    validationDetails = new ArrayList<ValidationDetail>();
  }

  public void addValidationDetail(String fieldName, String messageKey) {
    ValidationDetail validationDetail = new ValidationDetail(fieldName, messageKey);
    validationDetails.add(validationDetail);
  }

  public void addValidationDetail(String fieldName, String messageKey, String message) {
    ValidationDetail validationDetail = new ValidationDetail(fieldName, messageKey, message);
    validationDetails.add(validationDetail);
  }

  /**
   * @return the validationDetails
   */
  public List<ValidationDetail> getValidationDetails() {
    return validationDetails;
  }
}
