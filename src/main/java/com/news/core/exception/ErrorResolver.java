/*
 * Parrot.
 */

package com.news.core.exception;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.news.core.exception.ErrorConstants.ErrorType;
import com.news.core.exception.model.BadRequestException;
import com.news.core.exception.model.DataNotFoundException;
import com.news.core.exception.model.GenericBusinessException;
import com.news.core.exception.model.UnknownErrorException;
import lombok.extern.slf4j.Slf4j;

/**
 * Error resolver component.
 * 
 * @author .
 */
@Slf4j
@Component
@RestControllerAdvice
@EnableConfigurationProperties
public class ErrorResolver {

  private ErrorConstants errorResolverConstants;

  public ErrorResolver(ErrorConstants errorResolverConstants) {
    this.errorResolverConstants = errorResolverConstants;
  }

  private static void writeToLog(ErrorResponse errorResponse, Exception exception) {
    log.error("An exception occured: {}", exception);
  }

  @ExceptionHandler(ServiceUnavailableException.class)
  @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
  public ErrorResponse resolveBadRequestException(HttpServletRequest req,
      ServiceUnavailableException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.ERROR.name());
    errorResponse.setCode(errorResolverConstants.getGenericErrorCode());
    errorResponse.setDetails(ex.getMessage());
    errorResponse.setTimestamp(ZonedDateTime.now());

    writeToLog(errorResponse, ex);

    return errorResponse;

  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse resolveException(HttpServletRequest req, Exception ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.FATAL.name());
    errorResponse.setCode(errorResolverConstants.getGenericErrorCode());
    errorResponse.setDetails(errorResolverConstants.getGenericErrorMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setTimestamp(ZonedDateTime.now());

    writeToLog(errorResponse, ex);

    return errorResponse;

  }

  @ExceptionHandler(UnknownErrorException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse resolveException(HttpServletRequest req, UnknownErrorException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.ERROR.name());
    errorResponse.setCode(errorResolverConstants.getGenericErrorCode());
    errorResponse.setDetails(errorResolverConstants.getGenericErrorMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setTimestamp(ZonedDateTime.now());

    writeToLog(errorResponse, ex);

    return errorResponse;

  }

  @ExceptionHandler(GenericBusinessException.class)
  @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
  public ErrorResponse resolveException(HttpServletRequest req, GenericBusinessException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.WARN.name());
    errorResponse.setCode(errorResolverConstants.getGenericErrorCode());
    errorResponse.setDetails(errorResolverConstants.getGenericErrorMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setTimestamp(ZonedDateTime.now());

    writeToLog(errorResponse, ex);

    return errorResponse;

  }

  @ExceptionHandler(DataNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorResponse resolveException(HttpServletRequest req, DataNotFoundException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.WARN.name());
    errorResponse.setCode(errorResolverConstants.getGenericErrorCode());
    errorResponse.setDetails(errorResolverConstants.getGenericErrorMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setTimestamp(ZonedDateTime.now());

    writeToLog(errorResponse, ex);

    return errorResponse;

  }

  @ExceptionHandler({BadRequestException.class, HttpMessageNotReadableException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorResponse resolveException(HttpServletRequest req, BadRequestException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.ERROR.name());
    errorResponse.setCode(errorResolverConstants.getGenericErrorCode());

    String location = null;
    List<String> badfields = ex.getBadFields();
    if (Objects.nonNull(badfields) && !badfields.isEmpty()) {
      location = String.join(",", badfields);
    }

    errorResponse.setDetails(ex.getMessage());
    errorResponse.setLocation(location);
    errorResponse.setTimestamp(ZonedDateTime.now());

    writeToLog(errorResponse, ex);

    return errorResponse;
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorResponse resolveException(HttpServletRequest req,
      HttpRequestMethodNotSupportedException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.INVALID.name());
    errorResponse.setCode(errorResolverConstants.getGenericErrorCode());
    errorResponse.setDetails(ex.getMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setTimestamp(ZonedDateTime.now());

    writeToLog(errorResponse, ex);

    return errorResponse;

  }

  @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorResponse resolveException(HttpServletRequest req,
      HttpMediaTypeNotAcceptableException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.INVALID.name());
    errorResponse.setCode(errorResolverConstants.getGenericErrorCode());
    errorResponse.setDetails(ex.getMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setTimestamp(ZonedDateTime.now());

    writeToLog(errorResponse, ex);

    return errorResponse;

  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorResponse resolveException(HttpServletRequest req, ConstraintViolationException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.INVALID.name());
    errorResponse.setCode(errorResolverConstants.getGenericErrorCode());

    List<String> violationMessages = new ArrayList<String>();
    ex.getConstraintViolations()
        .forEach(violation -> violationMessages.add(violation.getMessage()));

    errorResponse.setDetails( String.join(",", violationMessages));
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setTimestamp(ZonedDateTime.now());

    writeToLog(errorResponse, ex);

    return errorResponse;

  }
}
