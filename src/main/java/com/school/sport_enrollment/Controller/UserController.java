package com.school.sport_enrollment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.school.sport_enrollment.Dto.SportDto;
import com.school.sport_enrollment.Dto.UserDto;
import com.school.sport_enrollment.Dto.UserSignInDto;
import com.school.sport_enrollment.Enums.UserType;
import com.school.sport_enrollment.Model.User;
import com.school.sport_enrollment.Response.BaseResponse;
import com.school.sport_enrollment.Response.SportResponse;
import com.school.sport_enrollment.Response.UserInforResponse;
import com.school.sport_enrollment.Response.UserResponse;
import com.school.sport_enrollment.Service.UserService;
import com.school.sport_enrollment.Utils.CustomUserDetailImpl;
import com.school.sport_enrollment.Utils.JwtUtil;

import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/user")

public class UserController {

    private final UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create_super_admin")
    public ResponseEntity<UserResponse> createAdmin(@RequestBody UserDto userDto) {
        System.out.println(userDto.getUsername());
        UserResponse createdUser = userService.createAdmin(userDto);
        return new ResponseEntity<>(createdUser, createdUser.getStatusCode());
    }

    @PostMapping("/create_student")
    public ResponseEntity<UserResponse> createStudent(@RequestBody UserDto userDto) {
        System.out.println(userDto.getUsername());
        UserResponse createdStudent = userService.createStudent(userDto);
        return new ResponseEntity<>(createdStudent, createdStudent.getStatusCode());
    }

    @GetMapping("/get_all_user")

    public ResponseEntity<BaseResponse> getAllUsers() {
        BaseResponse user = userService.getAllUsers();
        return new ResponseEntity<>(user, user.getStatusCode());
    }

    @GetMapping("/get_user_by_id/{id}")

    public ResponseEntity<BaseResponse> getUserById(@PathVariable Long id) {

        BaseResponse user = userService.getUserById(id);
        return new ResponseEntity<>(user, user.getStatusCode());
    }

    @GetMapping("/get_all_User_by_type/{userType}")

    public ResponseEntity<BaseResponse> getAllUserssByType(@PathVariable UserType userType) {
        BaseResponse users = userService.getAllUsersByType(userType);
        return new ResponseEntity<>(users, users.getStatusCode());

    }

    @DeleteMapping("/delete_user/{id}")

    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {

        UserResponse users = userService.deleteUser(id);
        return new ResponseEntity<>(users, users.getStatusCode());
    }

    @PutMapping("/update_user/{id}")

    public ResponseEntity<UserResponse> updateUsers(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserResponse updateUser = userService.updateUser(id, userDto);
        return new ResponseEntity<>(updateUser, updateUser.getStatusCode());
    }

    @PostMapping("/signin")

    public ResponseEntity<BaseResponse> authenticateUser(@RequestBody UserSignInDto userSignInDto) {

        try {

            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userSignInDto.getEmail(),
                            userSignInDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetailImpl userDetails = (CustomUserDetailImpl) authentication.getPrincipal();

            String jwt = jwtUtil.generateTokenFromEmail(userDetails.getEmail());
            UserInforResponse userInforResponse = new UserInforResponse();

            userInforResponse.setJwt(jwt);
            userInforResponse.setEmail(userDetails.getEmail());

            BaseResponse baseResponse = new BaseResponse(userInforResponse, HttpStatus.OK,
                    "User successfully logged in");

            return new ResponseEntity<BaseResponse>(baseResponse, baseResponse.getStatusCode());

        } catch (BadCredentialsException e) {
            // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid
            // credentials", e);
            String message = "Invalid credentials";
            Integer statusValue = 400;
            HttpStatus status = HttpStatus.valueOf(statusValue);

            BaseResponse baseResponse = new BaseResponse(null, status, message);
            return new ResponseEntity<BaseResponse>(baseResponse, baseResponse.getStatusCode());
        }

        catch (Exception e) {
            System.out.println(e);

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong", e);
        }

    }
}