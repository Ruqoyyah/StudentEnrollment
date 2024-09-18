package com.school.sport_enrollment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.sport_enrollment.Model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
