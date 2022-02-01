/*
 * Modified by: santojo
 * Last updated: Jul 31, 2018 5:30:24 PM
 */
package com.caista.birapps.etis.main.exception;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.caista.birapps.etis.common.utils.exception.*;
import com.caista.birapps.etis.domain.sysad.exception.FileSizeExceededException;

/**
 * The Class ReferenceExceptionHandler.
 */
@RestControllerAdvice(
    basePackages = {"com.caista.birapps.etis.maintenance", "com.caista.birapps.etis.reference"})
public class ReferenceExceptionHandler extends CommonExceptionHandler {

  /** The Constant LOG. */
  private static final Logger LOG = LoggerFactory.getLogger(ReferenceExceptionHandler.class);

  /** The Constant FILE_SIZE_EXCEEDED. */
  private static final String FILE_SIZE_EXCEEDED = "400 BAD REQUEST";

  /**
   * Handle file size exceeded exception.
   *
   * @param request the request
   * @param base the base
   * @return the response entity
   */
  @ExceptionHandler(FileSizeExceededException.class)
  public ResponseEntity<ApiErrorDetail> handleFileSizeExceededException(HttpServletRequest request,
      FileSizeExceededException base) {

    LOG.info("ATTACHMENT SIZE EXCEEDED EXCEPTION: ", base);

    ErrorDetails<FileSizeExceededException> errorDetails = new ErrorDetails<>();

    ApiErrorDetail error = errorDetails.setErrorDetails(FILE_SIZE_EXCEEDED, base,
        "Image file size is too large.");

    return new ResponseEntity<>(error, HttpStatus.PAYLOAD_TOO_LARGE);
  }
}
