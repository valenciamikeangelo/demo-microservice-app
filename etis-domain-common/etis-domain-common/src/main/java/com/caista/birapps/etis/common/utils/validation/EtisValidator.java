/*
  * Modified by: decinam
  * Last updated: Aug 8, 2018 2:49:27 PM
  */
package com.caista.birapps.etis.common.utils.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

/**
 * @author valencm
 *
 */
@Component
public class EtisValidator<T> {

  private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  Validator validator = factory.getValidator();


  public ValidationResponse validate(T validationRequest) {
    Set<ConstraintViolation<T>> violations = validator.validate(validationRequest);
    if (violations.isEmpty())
      return new ValidationResponse();

    return buildValidationRespose(violations);
  }

  public ValidationResponse validate(T validationRequest, Class<?> clazz) {
    Set<ConstraintViolation<T>> violations = validator.validate(validationRequest, clazz);
    if (violations.isEmpty())
      return new ValidationResponse();

    return buildValidationRespose(violations);
  }

  private ValidationResponse buildValidationRespose(Set<ConstraintViolation<T>> violations) {
    ValidationResponse validationResponse = new ValidationResponse();

    for (ConstraintViolation<T> violation : violations) {
      validationResponse.addValidationDetail(violation.getPropertyPath().toString(),
          violation.getMessage());
    }

    
    return validationResponse;
  }
}
