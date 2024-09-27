package com.school.sport_enrollment.Response;

import com.school.sport_enrollment.Enums.UserType;

public class UserInforResponse {
    private String Jwt;
    private long id;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String email;
    private String firstName;
    private String lastName;
    private UserType userType;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}
