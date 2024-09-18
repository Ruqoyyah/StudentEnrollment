package com.school.sport_enrollment.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.sport_enrollment.Enums.SportType;
import com.school.sport_enrollment.Model.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {

    Optional<Sport> findBySportName(String sportName);

    List<Sport> findBySportType(SportType sportType);

}
