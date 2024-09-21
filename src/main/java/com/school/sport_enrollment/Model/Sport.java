package com.school.sport_enrollment.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
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

    @JsonIgnore
    @ManyToMany(mappedBy = "sport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
