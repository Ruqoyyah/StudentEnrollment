package com.school.sport_enrollment.Response;

import org.springframework.http.HttpStatus;

import com.school.sport_enrollment.Model.User;

public class UserResponse {

    private User data;
    private HttpStatus statusCode;
    private String message;

    public UserResponse(User data, HttpStatus statusCode, String message) {
        this.data = data;
        this.statusCode = statusCode;
        this.message = message;
    }

    public User getData() {
        return data;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

}
