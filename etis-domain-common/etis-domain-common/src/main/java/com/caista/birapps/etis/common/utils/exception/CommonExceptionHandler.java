/*
  * Modified by: logronj
  * Last updated: Aug 28, 2018 2:02:43 PM
*/
package com.caista.birapps.etis.common.utils.exception;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * The Class CommonControllerAdviceException.
 */
@RestControllerAdvice
public class CommonExceptionHandler {

  /** The Constant LOG. */
  private static final Logger LOG = LoggerFactory.getLogger(CommonExceptionHandler.class);

  /** The Constant ERROR_CODE. */
  private static final String NOT_FOUND = "404 NOT FOUND";

  /** The Constant CONSTRAINT_VIOLATION. */
  private static final String CONSTRAINT_VIOLATION = "SQLIntegrityConstraintViolationException";

  /** The Constant ERROR_CODE. */
  private static final String NO_CONTENT = "204 NO CONTENT";

  /**
   * Handle code not found exception.
   *
   * @param request the request
   * @param base the base
   * @return the response entity
   */
  @ExceptionHandler(ApiNotFoundException.class)
  public ResponseEntity<ApiErrorDetail> handleCodeNotFoundException(HttpServletRequest request,
      final ApiNotFoundException base) {

    LOG.info("NOT FOUND EXCEPTION: ", base);

    ApiErrorDetail error = new ErrorDetails<ApiNotFoundException>().setErrorDetails(NOT_FOUND, base,
        "Input resource that exists.");

    return new ResponseEntity<ApiErrorDetail>(error, HttpStatus.NOT_FOUND);

  }

  /**
   * Handle no content violation exception.
   *
   * @param request the request
   * @param base the base
   * @return the response entity
   */
  @ExceptionHandler(ApiNoResultException.class)
  public ResponseEntity<ApiErrorDetail> handleNoResultsFoundException(HttpServletRequest request,
      final ApiNoResultException base) {

    LOG.info("NO RESULT EXCEPTION: ", base);
    ApiErrorDetail error = new ErrorDetails<ApiNoResultException>().setErrorDetails(NO_CONTENT,
        base, "Resource does not exist.");

    return new ResponseEntity<ApiErrorDetail>(error, HttpStatus.NOT_FOUND);
  }

  /**
   * Handle constraint violation exception.
   *
   * @param request the request
   * @param base the base
   * @return the response entity
   */
  @ExceptionHandler(ApiConstraintViolationException.class)
  public ResponseEntity<ApiErrorDetail> handleConstraintViolationException(
      HttpServletRequest request, ApiConstraintViolationException base) {

    LOG.info("CONSTRAINT VIOLATION EXCEPTION: ", base);

    ApiErrorDetail error = new ErrorDetails<ApiConstraintViolationException>().setErrorDetails(
        CONSTRAINT_VIOLATION, base, "CODE already exists.");

    return new ResponseEntity<ApiErrorDetail>(error, HttpStatus.UNPROCESSABLE_ENTITY);

  }
}
