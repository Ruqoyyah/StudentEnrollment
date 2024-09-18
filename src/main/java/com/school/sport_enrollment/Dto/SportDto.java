package com.school.sport_enrollment.Dto;

import com.school.sport_enrollment.Enums.SportType;

public class SportDto {
    private String sportName;
    private SportType sportType;

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

}
