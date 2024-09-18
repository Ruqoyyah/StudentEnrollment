package com.school.sport_enrollment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.sport_enrollment.Service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/student/{studentId}/sport/{sportId}")
    public ResponseEntity<String> enrollStudent(@PathVariable Long studentId, @PathVariable Long sportId) {

        String result = enrollmentService.enrollStudent(studentId, sportId);
        return ResponseEntity.ok(result);
    }
}
