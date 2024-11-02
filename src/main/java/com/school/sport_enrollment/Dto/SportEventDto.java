package com.school.sport_enrollment.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SportEventDto {

    private Long sportId;
    private String eventName;
    private LocalDateTime eventDate;

    // Getters and setters.
    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportid) {
        this.sportId = sportid;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

}
