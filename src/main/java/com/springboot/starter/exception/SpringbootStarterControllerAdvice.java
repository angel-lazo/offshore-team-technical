package com.springboot.starter.exception;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.starter.common.Constant;
import com.springboot.starter.common.MessageConstants;
import com.springboot.starter.common.enums.ApiException;
import com.springboot.starter.model.dto.ResponseDTO;



@RestControllerAdvice
public class SpringbootStarterControllerAdvice extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDTO<Object> handleNoDataFound(NotFoundException message) {
        return createErrorResponse(ApiException.ERR41404.getErrorCode(), String.format(ApiException.ERR41404.getErrorMessage(), message.getMessage()));
    }

    @ExceptionHandler({DuplicateLimitException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseDTO<Object> handleDuplicateLimit(RuntimeException message) {
        return createErrorResponse(ApiException.ERR41409.getErrorCode(), String.format(ApiException.ERR41409.getErrorMessage(), getSpecificDuplicateErrorMsg(message)));
    }

    @ExceptionHandler({InvalidRequestException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO<Object> handleInvalidRequest(RuntimeException message) {
        return createErrorResponse(ApiException.ERR41400.getErrorCode(), String.format(ApiException.ERR41400.getErrorMessage(), message.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDTO<Object> handleInternalServer(Exception ex) {
        return createErrorResponse(ApiException.ERR41500.getErrorCode(), ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = messageSource.getMessage(MessageConstants.INVALID_REQUEST_FORMAT, null, Locale.getDefault());
        ResponseDTO<Object> responseDTO = createErrorResponse(ApiException.ERR41415.getErrorCode(), String.format(ApiException.ERR41415.getErrorMessage(), message));

        return new ResponseEntity<>(responseDTO, status);
    }

    public ResponseDTO<Object> createErrorResponse(String errorCode, String errorMessage) {
        ResponseDTO<Object> response = new ResponseDTO<>();
        response.setSuccess(false);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        return response;
    }

    private String getSpecificDuplicateErrorMsg(RuntimeException re) {

        if (re.getMessage().contains(Constant.PET_UNIQUE_COLS)) {
            return messageSource.getMessage(MessageConstants.PET_UNIQUE_COL, null, Locale.getDefault());
        } 

        return re.getMessage();
    }
}
