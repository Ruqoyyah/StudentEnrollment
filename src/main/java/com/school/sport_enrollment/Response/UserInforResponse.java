package com.school.sport_enrollment.Response;

public class UserInforResponse {
    String Jwt;
    String email;

    public String getJwt() {
        return Jwt;
    }

    public void setJwt(String jwt) {
        Jwt = jwt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
