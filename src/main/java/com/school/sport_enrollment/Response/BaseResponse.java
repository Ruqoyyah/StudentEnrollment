package com.school.sport_enrollment.Response;

import org.springframework.http.HttpStatus;

public class BaseResponse {

    private Object data;
    private HttpStatus statusCode;
    private String message;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BaseResponse(Object data, HttpStatus statusCode, String message) {
        this.data = data;
        this.statusCode = statusCode;
        this.message = message;
    }

}
