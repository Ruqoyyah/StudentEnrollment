package com.school.sport_enrollment.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.grammars.hql.HqlParser.LocalDateTimeContext;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.sport_enrollment.Enums.SportType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Sport")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SportName")
    private String sportName;

    @Column(name = "CreationDate")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "SportType")
    private SportType sportType;

    @Column(name = "EnrollmentDeadline")
    private LocalDateTime enrollmentDeadline;

    @Column(name = "Year")
    private Long year;

    @Column(name = "Season")
    private String season;

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public LocalDateTime getEnrollmentDeadline() {
        return enrollmentDeadline;
    }

    public void setEnrollmentDeadline(LocalDateTime enrollmentDeadline) {
        this.enrollmentDeadline = enrollmentDeadline;
    }

    public Long getId() {
        return id;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    @JsonIgnore // Ignores this property in JSON serialization to prevent circular references.
    @ManyToMany(mappedBy = "sport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users; // List of users associated with this sport.

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @OneToMany(mappedBy = "sport", cascade = CascadeType.ALL) // One-to-many relationship with SportEvent.
    private List<SportEvent> events; // List of events associated with this sport.

}
