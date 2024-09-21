package com.school.sport_enrollment.Service;

import java.util.List;
import java.util.Optional;

import com.school.sport_enrollment.Enums.SportType;
// import org.hibernate.usertype.UserType;
import com.school.sport_enrollment.Enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.school.sport_enrollment.Dto.SportDto;
import com.school.sport_enrollment.Dto.UserDto;
import com.school.sport_enrollment.Model.Sport;
import com.school.sport_enrollment.Model.User;
import com.school.sport_enrollment.Repository.SportRepository;
import com.school.sport_enrollment.Repository.UserRepository;
import com.school.sport_enrollment.Response.BaseResponse;
import com.school.sport_enrollment.Response.SportResponse;
import com.school.sport_enrollment.Response.UserResponse;
import com.school.sport_enrollment.Utils.Helpers;

@Service

public class UserService {

    private UserRepository userRepository;
    private SportRepository sportRepository;

    public UserService(UserRepository userRepository, SportRepository sportRepository) {
        this.userRepository = userRepository;
        this.sportRepository = sportRepository;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // @Autowired
    // public UserService(PasswordEncoder passwordEncoder) {
    // this.passwordEncoder = passwordEncoder;
    // }

    public BaseResponse getAllUsers() {

        try {

            List<User> allUsers = userRepository.findAll();
            BaseResponse baseResponse = new BaseResponse(allUsers, HttpStatus.OK, "Users successfully fetched");
            return baseResponse;

        } catch (Exception e) {
            BaseResponse baseResponse = new BaseResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            return baseResponse;
        }

    }

    public BaseResponse getUserById(Long id) {

        try {

            Optional<User> User = userRepository.findById(id);

            if (User.isEmpty()) {

                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist");

            }
            BaseResponse baseResponse = new BaseResponse(User.get(), HttpStatus.OK, "User successfully fetched");
            return baseResponse;

        } catch (Exception e) {
            BaseResponse baseResponse = new BaseResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            return baseResponse;
        }
    }

    public UserResponse createAdmin(UserDto userDto) {

        try {

            if (Helpers.isStringEmptyOrBlank(userDto.getUsername())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot be blank");
            }
            Optional<User> existingUser = userRepository.findByUsername(userDto.getUsername());

            if (existingUser.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username  already exist");
            }
            Optional<User> existingEmail = userRepository.findByEmail(userDto.getEmail());

            if (existingEmail.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email  already exist");
            }

            User newUser = new User();
            newUser.setFirstname(userDto.getFirstname());
            newUser.setLastname(userDto.getLastname());
            newUser.setEmail(userDto.getEmail());
            newUser.setUsername(userDto.getUsername());
            newUser.setPassword(this.passwordEncoder().encode(userDto.getPassword()));
            newUser.setUserType(UserType.SUPER_ADMIN);
            User createdAdmin = userRepository.save(newUser);
            UserResponse userResponse = new UserResponse(createdAdmin, HttpStatus.OK,
                    "Admin user successfully created");
            return userResponse;

        } catch (ResponseStatusException e) {
            String message = e.getReason();
            Integer statusValue = e.getStatusCode().value();
            HttpStatus status = HttpStatus.valueOf(statusValue);

            UserResponse userResponse = new UserResponse(null, status, message);
            return userResponse;

            // TODO: handle exception
        } catch (Exception e) {
            System.out.println(e);
            UserResponse userResponse = new UserResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            return userResponse;
        }

    }

    public UserResponse createStudent(UserDto userDto) {

        try {

            if (Helpers.isStringEmptyOrBlank(userDto.getUsername())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot be blank");
            }
            Optional<User> existingUser = userRepository.findByUsername(userDto.getUsername());

            if (existingUser.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username  already exist");
            }
            Optional<User> existingEmail = userRepository.findByEmail(userDto.getEmail());

            if (existingEmail.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exist");
            }

            User newStudent = new User();
            newStudent.setFirstname(userDto.getFirstname());
            newStudent.setLastname(userDto.getLastname());
            newStudent.setEmail(userDto.getEmail());
            newStudent.setUsername(userDto.getUsername());
            newStudent.setRegistnumber(userDto.getRegistnumber());
            newStudent.setPassword(this.passwordEncoder().encode(userDto.getPassword()));
            newStudent.setUserType(UserType.STUDENT);
            User createdStudent = userRepository.save(newStudent);
            UserResponse userResponse = new UserResponse(createdStudent, HttpStatus.OK, "Student successfully created");
            return userResponse;

        } catch (ResponseStatusException e) {
            String message = e.getReason();
            Integer statusValue = e.getStatusCode().value();
            HttpStatus status = HttpStatus.valueOf(statusValue);

            UserResponse userResponse = new UserResponse(null, status, message);
            return userResponse;

            // TODO: handle exception
        } catch (Exception e) {
            System.out.println(e);
            UserResponse userResponse = new UserResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            return userResponse;
        }
    }

    public BaseResponse getAllUsersByType(UserType userType) {
        try {

            List<User> allUsers = userRepository.findByUserType(userType);
            BaseResponse baseResponse = new BaseResponse(allUsers, HttpStatus.OK,
                    "User successfully fetched by user type");
            return baseResponse;

        } catch (Exception e) {
            BaseResponse baseResponse = new BaseResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            return baseResponse;

        }
    }

    @Transactional

    public UserResponse deleteUser(Long id) {
        try {
            Optional<User> user = userRepository.findById(id);

            if (!user.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist");
            }
            userRepository.deleteById(id);
            UserResponse userResponse = new UserResponse(null, HttpStatus.OK, "User successfully deleted");
            return userResponse;

        } catch (

        ResponseStatusException e) {
            String message = e.getReason();
            Integer statusValue = e.getStatusCode().value();
            HttpStatus status = HttpStatus.valueOf(statusValue);

            UserResponse userResponse = new UserResponse(null, status, message);
            return userResponse;

        } catch (Exception e) {
            System.out.println(e);
            UserResponse userResponse = new UserResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            System.out.println(e);
            return userResponse;

        }
    }

    public UserResponse updateUser(Long id, UserDto userDto) {

        try {

            if (Helpers.isStringEmptyOrBlank(userDto.getUsername())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot be blank");
            }
            Optional<User> user = userRepository.findById(id);

            if (!user.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username does not exist");
            }
            user.get().setLastname(userDto.getLastname());
            user.get().setFirstname(userDto.getFirstname());
            user.get().setEmail(userDto.getEmail());
            User updateUser = userRepository.save(user.get());
            UserResponse userResponse = new UserResponse(updateUser, HttpStatus.OK, "User successfully updated");
            return userResponse;

        } catch (ResponseStatusException e) {
            String message = e.getReason();
            Integer statusValue = e.getStatusCode().value();
            HttpStatus status = HttpStatus.valueOf(statusValue);

            UserResponse userResponse = new UserResponse(null, status, message);
            return userResponse;

            // TODO: handle exception
        }

        catch (Exception e) {
            UserResponse userResponse = new UserResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            return userResponse;
        }
    }

    public UserResponse updateUserWithSport(Long userId, Long sportId) {

        try {

            Optional<User> user = userRepository.findById(userId);

            if (!user.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist");
            }
            Optional<Sport> sport = sportRepository.findById(sportId);

            if (!sport.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sport does not exist");
            }
            List<Sport> userExistingSport = user.get().getSport();

            if (userExistingSport.size() > 0) {

                for (Sport sportInstance : userExistingSport) {
                    if (sportInstance.getSportType().equals(SportType.TEAM)) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You've exceeded your sport limit");
                    }

                    // List<Sport> roles = user.getRoles();
                    // roles.add(use.get());
                    // user.setRoles(roles);
                    // users.add(user);
                }
                if (sport.get().getSportType().equals(SportType.TEAM)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You've exceeded your sport limit");
                }
                userExistingSport.add(sport.get());

                user.get().setSport(userExistingSport);
                userRepository.save(user.get());
                UserResponse userResponse = new UserResponse(user.get(), HttpStatus.OK,
                        "Sport successfully added to User");
                return userResponse;

            } else {

                userExistingSport.add(sport.get());

                user.get().setSport(userExistingSport);
                userRepository.save(user.get());
                UserResponse userResponse = new UserResponse(user.get(), HttpStatus.OK,
                        "Sport successfully added to User");
                return userResponse;

            }

        } catch (ResponseStatusException e) {
            String message = e.getReason();
            Integer statusValue = e.getStatusCode().value();
            HttpStatus status = HttpStatus.valueOf(statusValue);

            UserResponse userResponse = new UserResponse(null, status, message);
            return userResponse;

        } catch (Exception e) {
            UserResponse userResponse = new UserResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            System.out.println(e);
            return userResponse;

        }

    }

}
