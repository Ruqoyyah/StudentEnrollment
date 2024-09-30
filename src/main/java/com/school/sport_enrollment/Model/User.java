package com.school.sport_enrollment.Model;

import org.hibernate.annotations.CreationTimestamp;
import java.util.List;
import com.school.sport_enrollment.Enums.UserType;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "registnumber", unique = true)
    private String registnumber;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "CreationDate")
    @CreationTimestamp
    private LocalDateTime creationDate;

    // @Enumerated(EnumType.STRING)
    // private UserType userRole;

    @Column(name = "UserType")
    private UserType userType;

    public Long getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistnumber() {
        return registnumber;
    }

    public void setRegistnumber(String registnumber) {
        this.registnumber = registnumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_sport_assignments", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "sportId"))
    private List<Sport> sport;

    public List<Sport> getSport() {
        return sport;
    }

    public void setSport(List<Sport> sport) {
        this.sport = sport;
    }

    public void removeSport(Sport sport) {
        this.sport.remove(sport);
        sport.getUsers().remove(this);
    }

}
