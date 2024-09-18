package com.school.sport_enrollment.Repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.school.sport_enrollment.Enums.UserType;
import com.school.sport_enrollment.Model.User;

@EnableJpaRepositories

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserType(UserType userType);

    Optional<User> findByUsername(String string);

    Optional<User> findByEmail(String email);

    Optional<User> deleteByUsername(String username);

}
