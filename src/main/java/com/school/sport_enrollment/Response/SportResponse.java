package com.school.sport_enrollment.Response;

import org.springframework.http.HttpStatus;

import com.school.sport_enrollment.Model.Sport;

public class SportResponse {
    private Sport data;
    private HttpStatus statusCode;
    private String message;

    public SportResponse(Sport data, HttpStatus statusCode, String message) {
        this.data = data;
        this.statusCode = statusCode;
        this.message = message;

    }

    public Sport getData() {
        return data;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

}
