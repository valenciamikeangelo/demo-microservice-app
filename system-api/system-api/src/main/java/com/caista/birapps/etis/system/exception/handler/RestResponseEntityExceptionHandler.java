/*
 * Modified by: obregoj Last updated: Oct 30, 2019 7:10:49 PM
 */
package com.caista.birapps.etis.system.exception.handler;

import java.util.Locale;
import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.caista.birapps.etis.common.utils.exception.*;
import com.caista.birapps.etis.system.exception.NoDefinedPermissionException;
import com.caista.birapps.etis.system.exception.UnAuthorizedPagesAccess;

/**
 * @author valencm
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger("errorLogger");

  @Autowired
  private MessageSource messageSource;

  final String contactAdminKey = "error.contact.admin";

  @ExceptionHandler({NoDefinedPermissionException.class, AuthorizationDeniedException.class})
  public ResponseEntity<ApiErrorDetail> handleNoDefinedPermissionException(Exception ex,
      WebRequest request) {
    String adminMessage = messageSource.getMessage(contactAdminKey, new Object[] {},
        Locale.getDefault());
    return new ResponseEntity<>(buildError(ex, adminMessage), new HttpHeaders(),
        HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler({UnAuthorizedPagesAccess.class})
  public ResponseEntity<ApiErrorDetail> handleUnAuthorizedPagesAccess(Exception ex,
      WebRequest request) {
    String adminMessage = messageSource.getMessage(contactAdminKey, new Object[] {},
        Locale.getDefault());
    return new ResponseEntity<>(buildError(ex, adminMessage), new HttpHeaders(),
        HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<ApiErrorDetail> handleGenericException(Exception ex, WebRequest request) {
    String adminMessage = messageSource.getMessage(contactAdminKey, new Object[] {},
        Locale.getDefault());
    return new ResponseEntity<>(buildError(ex, adminMessage), new HttpHeaders(),
        HttpStatus.UNPROCESSABLE_ENTITY);
  }

  private ApiErrorDetail buildError(Exception e, String recommendedAction) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    String message = "";
    String code = "";
    String display = "";

    if (e instanceof UnAuthorizedPagesAccess) {

      String exceptionName = e.getClass().getSimpleName();
      String messageKey = exceptionName + ".message";
      String codeKey = exceptionName + ".code";
      String displayKey = exceptionName + ".display";

      message = messageSource.getMessage(messageKey,
          new Object[] {currentPrincipalName, ((UnAuthorizedPagesAccess) e).getResourceId(),
              ((UnAuthorizedPagesAccess) e).getClientId()},
          Locale.getDefault());

      code = messageSource.getMessage(codeKey, new Object[] {}, Locale.getDefault());
      display = messageSource.getMessage(displayKey, new Object[] {}, Locale.getDefault());

    } else if (e instanceof BaseApiException) {
      String exceptionName = e.getClass().getSimpleName();
      String messageKey = exceptionName + ".message";
      String codeKey = exceptionName + ".code";
      String displayKey = exceptionName + ".display";

      message = messageSource.getMessage(messageKey,
          new Object[] {currentPrincipalName, ((BaseApiException) e).getModule()},
          Locale.getDefault());

      code = messageSource.getMessage(codeKey, new Object[] {}, Locale.getDefault());
      display = messageSource.getMessage(displayKey, new Object[] {}, Locale.getDefault());
    }

    else {

      String messageKey = "error.generic.message";
      String codeKey = "error.generic.code";
      String displayKey = "error.generic.display";

      message = messageSource.getMessage(messageKey, new Object[] {currentPrincipalName},
          Locale.getDefault());

      code = messageSource.getMessage(codeKey, new Object[] {}, Locale.getDefault());
      display = messageSource.getMessage(displayKey, new Object[] {}, Locale.getDefault());

    }

    ApiErrorDetail apiErrorDetail = new ApiErrorDetail(code, message, display, recommendedAction);
    logErrorDetails(e, apiErrorDetail);
    return apiErrorDetail;

  }

  private void logErrorDetails(Exception e, ApiErrorDetail details) {
    LOGGER.info("Exception : " + e.getClass().getName());
    LOGGER.info("ApiError Details : " + details.toString());

  }
}
