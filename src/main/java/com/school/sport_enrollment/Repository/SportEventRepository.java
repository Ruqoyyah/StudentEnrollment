package com.school.sport_enrollment.Repository;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.sport_enrollment.Model.Sport;
import com.school.sport_enrollment.Model.SportEvent;
import com.school.sport_enrollment.Model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface SportEventRepository extends JpaRepository<SportEvent, Long> {

        List<SportEvent> findByEventDateAfter(LocalDate date);

        // Find all past events (date is before current date)
        List<SportEvent> findByEventDateBefore(LocalDate date);

        List<SportEvent> findByEventName(String eventName);

        java.util.Optional<SportEvent> findById(Long id);

        void deleteById(Long id);

        List<SportEvent> findBySport(Sport sport);

        @Query("SELECT e FROM SportEvent e WHERE e.eventDate > :currentDate")

        List<SportEvent> findUpcomingEvents(@Param("currentDate") LocalDateTime currentDate);

        @Query("SELECT e FROM SportEvent e WHERE e.eventDate < :currentDate")
        List<SportEvent> findPastEvents(@Param("currentDate") LocalDateTime currentDate);

        @Query("SELECT e FROM SportEvent e WHERE e.eventDate > :currentDate AND e.sport = :sport")
        List<SportEvent> findUpcomingEventsBySport(@Param("currentDate") LocalDateTime currentDate,
                        @Param("sport") Sport sport);

        @Query("SELECT e FROM SportEvent e WHERE e.eventDate < :currentDate AND e.sport = :sport")
        List<SportEvent> findPastEventsBySport(@Param("currentDate") LocalDateTime currentDate,
                        @Param("sport") Sport sport);

        @Query("SELECT e FROM SportEvent e WHERE e.sport.id IN :sportid")
        List<SportEvent> findEventBySportId(@Param("sportid") List<Long> sportid);

        @Query("SELECT e FROM SportEvent e WHERE e.sport.id IN :sportid AND e.eventDate < :currentDate ")

        List<SportEvent> findPastEventsByStudent(List<Long> sportid, @Param("currentDate") LocalDateTime currentDate);

        @Query("SELECT e FROM SportEvent e WHERE e.sport.id IN :sportid AND e.eventDate > :currentDate ")

        List<SportEvent> findUpcomingEventsByStudent(List<Long> sportid,
                        @Param("currentDate") LocalDateTime currentDate);

}
