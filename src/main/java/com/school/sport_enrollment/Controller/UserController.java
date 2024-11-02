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

    // Inject dependencies for authentication and JWT handling
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;

    // Constructor to inject the UserService dependency
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint for creating a super admin user
    @PostMapping("/create_super_admin")
    public ResponseEntity<UserResponse> createAdmin(@RequestBody UserDto userDto) {
        System.out.println(userDto.getUsername());
        // Call the user service to create the super admin using the provided UserDto
        // object
        // This method handles the logic to create a super admin user in the system.
        UserResponse createdUser = userService.createAdmin(userDto);
        // Return a ResponseEntity containing the createdUser object.
        // The HTTP status is set based on the status code provided within the
        // createdUser response.
        return new ResponseEntity<>(createdUser, createdUser.getStatusCode());
    }

    // Endpoint for creating a student user
    @PostMapping("/create_student")
    public ResponseEntity<UserResponse> createStudent(@RequestBody UserDto userDto) {
        System.out.println(userDto.getUsername());
        // Call the user service to create the student using the provided UserDto object
        UserResponse createdStudent = userService.createStudent(userDto);
        return new ResponseEntity<>(createdStudent, createdStudent.getStatusCode());
    }

    // Endpoint for creating a Coach
    @PostMapping("/create_coach")
    public ResponseEntity<UserResponse> createCoach(@RequestBody UserDto userDto) {
        System.out.println(userDto.getUsername());
        // Call the user service to create the coach using the provided UserDto object
        UserResponse createdCoach = userService.createdCoach(userDto);
        return new ResponseEntity<>(createdCoach, createdCoach.getStatusCode());
    }

    // Endpoint for retrieving all users
    @GetMapping("/get_all_user")

    public ResponseEntity<BaseResponse> getAllUsers() {
        // Call the user service to retrieve all user data
        BaseResponse user = userService.getAllUsers();
        return new ResponseEntity<>(user, user.getStatusCode());
    }

    // Endpoint for retrieving a user by their ID
    @GetMapping("/get_user_by_id/{id}")
    public ResponseEntity<BaseResponse> getUserById(@PathVariable Long id) {
        // Call the user service to fetch the user data based on the provided ID
        BaseResponse user = userService.getUserById(id);
        return new ResponseEntity<>(user, user.getStatusCode());
    }

    // Endpoint for retrieving all users of a specific type
    @GetMapping("/get_all_User_by_type/{userType}")
    public ResponseEntity<BaseResponse> getAllUserssByType(@PathVariable UserType userType) {
        // Call the user service to fetch users filtered by the provided user type
        BaseResponse users = userService.getAllUsersByType(userType);
        return new ResponseEntity<>(users, users.getStatusCode());

    }

    // Endpoint for deleting a user by their ID
    @DeleteMapping("/delete_user/{id}")

    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {
        // Call the user service to delete the user based on the provided ID
        UserResponse users = userService.deleteUser(id);
        return new ResponseEntity<>(users, users.getStatusCode());
    }

    // Endpoint for updating a user’s details by their ID
    @PutMapping("/update_user/{id}")
    public ResponseEntity<UserResponse> updateUsers(@PathVariable Long id, @RequestBody UserDto userDto) {
        // Call the user service to update the user with the provided ID using the
        // details from the UserDto object
        // The method takes an ID and a UserDto object containing the updated user
        // information.

        UserResponse updateUser = userService.updateUser(id, userDto);
        return new ResponseEntity<>(updateUser, updateUser.getStatusCode());
    }

    // Endpoint for user sign-in
    @PostMapping("/signin")

    public ResponseEntity<BaseResponse> authenticateUser(@RequestBody UserSignInDto userSignInDto) {

        try {
            // Authenticate user credentials
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userSignInDto.getEmail(),
                            userSignInDto.getPassword()));
            // Set the authenticated user in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Retrieve the authenticated user's details
            CustomUserDetailImpl userDetails = (CustomUserDetailImpl) authentication.getPrincipal();
            // Generate a JWT token for the authenticated user
            String jwt = jwtUtil.generateTokenFromEmail(userDetails.getEmail());
            // Creates a new instance of UserInforResponse to store user information.
            UserInforResponse userInforResponse = new UserInforResponse();

            userInforResponse.setJwt(jwt);
            userInforResponse.setEmail(userDetails.getEmail());
            userInforResponse.setUserType(userService.getUser(userDetails.getEmail()).getUserType());
            userInforResponse.setFirstName(userService.getUser(userDetails.getEmail()).getFirstname());
            userInforResponse.setLastName(userService.getUser(userDetails.getEmail()).getLastname());
            userInforResponse.setId(userService.getUser(userDetails.getEmail()).getId());
            // Creates a new instance of BaseResponse to encapsulate the response data for a
            // successful login.
            // It includes the user information, HTTP status, and a success message.

            BaseResponse baseResponse = new BaseResponse(userInforResponse, HttpStatus.OK,
                    "User successfully logged in");

            return new ResponseEntity<BaseResponse>(baseResponse, baseResponse.getStatusCode());

        } catch (BadCredentialsException e) {
            // Handle invalid credentials by setting an error message and status code
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
    // Defines an endpoint that updates a user by associating them with a specified
    // sport.

    @PutMapping("/update_user_with_sport/{userId}/{sportId}")
    // Takes `userId` and `sportId` as path variables from the request URL.
    // Wraps the `UserResponse` object in a ResponseEntity,
    public ResponseEntity<UserResponse> updateUserWithSport(@PathVariable Long userId, @PathVariable Long sportId) {
        // Calls the service layer method `updateUserWithSport` to perform the update,
        // passing in the `userId` and `sportId` as arguments.
        // Stores the result as a `UserResponse` object, which contains the update
        // status and any relevant data.
        UserResponse updateUserWithSport = userService.updateUserWithSport(userId, sportId);
        // Wraps the `UserResponse` object in a ResponseEntity,
        // setting the HTTP status code to the one contained within `UserResponse`.
        // This ResponseEntity will be returned to the client as the HTTP response.
        return new ResponseEntity<>(updateUserWithSport, updateUserWithSport.getStatusCode());

    }

    // Endpoint for retrieving users by their associated sport ID
    @GetMapping("/get_user_by_sportid/{sportid}")

    public ResponseEntity<BaseResponse> getUserBySportId(@PathVariable Long sportid) {

        BaseResponse user = userService.getUserBySportId(sportid);
        return new ResponseEntity<>(user, user.getStatusCode());
    }

    @GetMapping("/get_user_by_sportid_coach/{sportid}")

    public ResponseEntity<BaseResponse> getUserBySportIdByCoach(@PathVariable Long sportid) {

        BaseResponse user = userService.getUserBySportIdByCoach(sportid);
        return new ResponseEntity<>(user, user.getStatusCode());
    }
    // Endpoint for updating a user based on their ID and associated sport ID

    @PutMapping("/update_user/{userid}/{sportid}")

    public ResponseEntity<UserResponse> updateUsersBySportId(@PathVariable Long userid, @PathVariable Long sportid) {
        // Calls the method, passing in the sport ID which returns a BaseResponse object
        // containing user information.
        UserResponse updateUser = userService.updateUserBySportId(userid, sportid);
        return new ResponseEntity<>(updateUser, updateUser.getStatusCode());

    }
    // Endpoint for deleting a sport association from a user by user ID and sport ID

    @DeleteMapping("/delete_sportByUser/{userid}/{sportid}")

    public ResponseEntity<UserResponse> deleteSportByUserId(@PathVariable Long userid, @PathVariable Long sportid) {
        UserResponse deleteSport = userService.deleteSportByUserId(userid, sportid);
        return new ResponseEntity<>(deleteSport, deleteSport.getStatusCode());
    }

    @PutMapping("/update_coach_with_sport/{userId}/{sportId}")
    // Takes `userId` and `sportId` as path variables from the request URL.
    // Wraps the `UserResponse` object in a ResponseEntity,
    public ResponseEntity<UserResponse> updateCoachWithSport(@PathVariable Long userId, @PathVariable Long sportId) {
        // Calls the service layer method `updateUserWithSport` to perform the update,
        // passing in the `userId` and `sportId` as arguments.
        // Stores the result as a `UserResponse` object, which contains the update
        // status and any relevant data.
        UserResponse updateCoachWithSport = userService.updateCoachWithSport(userId, sportId);
        // Wraps the `UserResponse` object in a ResponseEntity,
        // setting the HTTP status code to the one contained within `UserResponse`.
        // This ResponseEntity will be returned to the client as the HTTP response.
        return new ResponseEntity<>(updateCoachWithSport, updateCoachWithSport.getStatusCode());

    }
}