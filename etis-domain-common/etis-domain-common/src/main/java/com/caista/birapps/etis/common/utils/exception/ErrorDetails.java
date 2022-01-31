/*
 * Modified by: santojo
 * Last updated: Jul 31, 2018 1:35:26 PM
 */
package com.caista.birapps.etis.common.utils.exception;

/**
 * The Class ErrorDetails.
 *
 * @param <T> the generic type
 */
public class ErrorDetails<T extends BaseApiRuntimeException> {

  /**
   * Sets the error details.
   *
   * @param ERROR_CODE the error code
   * @param base the base
   * @param recommendedAction the recommended action
   * @return the api error detail
   */
  public ApiErrorDetail setErrorDetails(final String ERROR_CODE, T base, String recommendedAction) {
    final String errorDescription = base.getMessage();
    final String displayArea = "Etis Module:" + base.getModule() + " - class "
        + base.getStackTrace()[0].getClassName() + " - line: "
        + base.getStackTrace()[0].getLineNumber();

    return new ApiErrorDetail(ERROR_CODE, errorDescription, displayArea, recommendedAction);
  }
}
