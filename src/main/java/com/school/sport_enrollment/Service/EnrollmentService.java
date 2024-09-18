package com.school.sport_enrollment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.sport_enrollment.Model.Enrollment;
import com.school.sport_enrollment.Model.EnrollmentDate;
import com.school.sport_enrollment.Model.Sport;
import com.school.sport_enrollment.Model.Student;
import com.school.sport_enrollment.Repository.EnrollmentRepository;
import com.school.sport_enrollment.Repository.SportRepository;
import com.school.sport_enrollment.Repository.StudentRepository;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Service
public class EnrollmentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public String enrollStudent(Long studentId, Long sportId) {
        try {

            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            Sport sport = sportRepository.findById(sportId)
                    .orElseThrow(() -> new RuntimeException("Sport not found"));

            // Create new enrollment
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setSport(sport);
            enrollment.setEnrollmentDate(LocalDateTime.now());

            enrollmentRepository.save(enrollment);

            return "Student enrolled successfully!";

        } catch (RuntimeException e) {
            // Handle specific runtime exceptions (e.g., student or sport not found)
            return e.getMessage(); // Return the exception message (e.g., "Student not found" or "Sport not found")
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            return "An error occurred during the enrollment process.";
        }
    }
}
