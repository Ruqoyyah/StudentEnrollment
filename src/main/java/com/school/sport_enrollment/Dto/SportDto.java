package com.school.sport_enrollment.Dto;

import org.hibernate.grammars.hql.HqlParser.LocalDateTimeContext;
import org.hibernate.mapping.List;

import com.school.sport_enrollment.Enums.SportType;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import java.time.LocalDateTime;

public class SportDto {
    private String sportName;
    private SportType sportType;
    private LocalDateTime enrollmentDeadline;
    private Long year;
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

    public String getSportName() {
        return sportName;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public LocalDateTime getEnrollmentDeadline() {
        return enrollmentDeadline;
    }

    public void setEnrollmentDeadline(LocalDateTime enrollmentDeadline) {
        this.enrollmentDeadline = enrollmentDeadline;
    }

}
