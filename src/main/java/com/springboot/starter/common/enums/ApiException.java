package com.springboot.starter.common.enums;

public enum ApiException {


    ERR41400("ERROR-41400","Bad Request - %s"), //BAD REQUEST
    ERR41404("ERROR-41404","Not Found - %s"), //NOT FOUND
    ERR41409("ERROR-41409","Conflict - %s"), //CONFLICT
    ERR41415("ERROR-41415","Unsupported Media Type - %s"), //UNSUPPORTED MEDIA TYPE
    ERR41500("ERROR-41500","Internal Server Error - %s"), //INTERNAL SERVER ERROR
    ERR41503("ERROR-41503", "Service Unavailable - %s");//SERVICE UNAVAILABLE

    private String errorCode;
    private String errorMessage;

    private ApiException(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
