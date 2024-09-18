package com.school.sport_enrollment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.sport_enrollment.Model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
