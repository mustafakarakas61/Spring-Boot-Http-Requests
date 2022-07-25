package com.mtm.test.mustafa.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;



@ControllerAdvice
public class MtmException extends Exception {

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public MtmException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public MtmException(String errorMessage) {
        super(errorMessage);
    }

    public MtmException() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}

