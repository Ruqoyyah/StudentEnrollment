package com.school.sport_enrollment.Dto;

import org.hibernate.usertype.UserType;

public class UserDto {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String registnumber;

    public String getRegistnumber() {
        return registnumber;
    }

    public void setRegistnumber(String registnumber) {
        this.registnumber = registnumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
