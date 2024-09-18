package com.school.sport_enrollment.Dto;

import com.school.sport_enrollment.Model.Sport;
import com.school.sport_enrollment.Model.Student;

public class EnrollmentDto {

    private Student student;
    private Sport sport;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

}
