package com.mimacom.back.controller.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

/**
 * ex of standard error body :
 * {
 * "timestamp": "2018-11-16T17:41:54.635+0000",
 * "status": 500,
 * "error": "Internal Server Error",
 * "message": "No value present",
 * "path": "/infos/-100"
 * }
 * <p>
 * Using ErrorDetail class for Json body to mimic the same attributes.
 * * <p>
 * Exception not explicitely handled here are handled by
 * DefaultHandlerExceptionResolver
 * <p>
 *
 * @author Gauthier
 * @ControllerAdvice(annotations = MyRestException.class)
 * => enables to add this
 * exceptionHandler ONLY to @RestController class holding the custom annotation
 * MyRestException.class
 * or
 * @ControllerAdvice(annotations = RestController.class)
 * => enables to add this
 * exceptionHandler to all @RestController.
 * ==
 * If you // extends ResponseEntityExceptionHandler
 * then ResponseEntityExceptionHandler would intercept the current Exception and the output body is really not pleasant.
 */
@ControllerAdvice(annotations = RestController.class)
// @ControllerAdvice(annotations = MyRestException.class)
public class MyRestExceptionHandler { // extends ResponseEntityExceptionHandler {

    // public class RestExceptionHandler extends ResponseEn bb;tityExceptionHandler {
    // DefaultHandlerExceptionResolver ss;
    // ResponseEntityExceptionHandler bb;
    ExceptionHandlerExceptionResolver gg;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({ObjectOptimisticLockingFailureException.class,
            EntityNotFoundException.class,
            OptimisticLockingFailureException.class,
            ObjectRetrievalFailureException.class,})
    public final ResponseEntity<Object> handleEntityNotFoundException(Exception ex,
                                                                      HttpServletRequest request) {
        ErrorDetail errorDetail = ErrorDetail.build(ex, request, HttpStatus.NOT_FOUND);
        HttpHeaders headers = new HttpHeaders();
        logger.error("", ex);
        return new ResponseEntity<>(errorDetail, headers, errorDetail.getHttpStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handleBadRequest(Exception ex, HttpServletRequest request) {
        ErrorDetail errorDetail = ErrorDetail.build(ex, request, HttpStatus.BAD_REQUEST);
        HttpHeaders headers = new HttpHeaders();
        logger.error("", ex);
        return new ResponseEntity<>(errorDetail, headers, errorDetail.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllException(Exception ex, HttpServletRequest request) {
        ErrorDetail errorDetail = ErrorDetail.build(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
        HttpHeaders headers = new HttpHeaders();
        logger.error("", ex);
        return new ResponseEntity<>(errorDetail, headers, errorDetail.getHttpStatus());
    }

}
