package com.school.sport_enrollment.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.school.sport_enrollment.Enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Student_Table")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RegistrationNo")
    private String RegistrationNo;

    @Column(name = "FirstName")
    private String FirstName;

    @Column(name = "LastName")
    private String LastName;

    @Column(name = "Grade")
    private String Grade;

    @Column(name = "Password")
    private String Password;

    @Column(unique = true)
    private String emailAddress;

    @Column(name = "CreatedDate")
    @CreationTimestamp
    private LocalDateTime CreatedDate;

    @Column(name = "UserType")
    private UserType UserType;

    public Long getId() {
        return id;

    }

    public String getRegistrationNo() {
        return RegistrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        RegistrationNo = registrationNo;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        CreatedDate = createdDate;
    }

    public UserType getUserType() {
        return UserType;
    }

    public void setUserType(UserType userType) {
        UserType = userType;
    }

}
