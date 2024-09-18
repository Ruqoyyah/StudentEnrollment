package com.school.sport_enrollment.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.school.sport_enrollment.Enums.SportType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

}
