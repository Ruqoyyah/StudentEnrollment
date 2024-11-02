package com.school.sport_enrollment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.sport_enrollment.Dto.SportDto;
import com.school.sport_enrollment.Enums.SportType;
import com.school.sport_enrollment.Response.BaseResponse;
import com.school.sport_enrollment.Response.SportResponse;
import com.school.sport_enrollment.Service.SportService;

@RestController
@RequestMapping("/api/sport")

// SportController is responsible for handling requests related to sport

public class SportController {
    private final SportService sportService;

    // Automatically inject the SportService dependency into the SportController
    @Autowired
    public SportController(SportService sportService) {
        // Assign the injected service to the controller's service variable for use in
        // other methods
        this.sportService = sportService;

    }

    // Annotation to handle POST requests for creating a new sport
    @PostMapping("/create_sport")
    public ResponseEntity<SportResponse> createSport(@RequestBody SportDto sportDto) {
        System.out.println(sportDto.getSportName());
        // Call the service method to create a new sport using the provided sport data
        // transfer object (DTO)collected by user
        SportResponse createdSport = sportService.createSport(sportDto);
        return new ResponseEntity<>(createdSport, createdSport.getStatusCode());

    }

    // Annotation to handle GET requests for retrieving all sports
    @GetMapping("/get_all")

    public ResponseEntity<BaseResponse> getAllSports() {
        // Call the service method to fetch all sports
        BaseResponse sports = sportService.getAllSports();
        return new ResponseEntity<>(sports, sports.getStatusCode());

    }

    // Annotation to handle PUT requests for updating a sport by its ID
    @PutMapping("/update_sport/{id}")

    public ResponseEntity<SportResponse> updateSports(@PathVariable Long id, @RequestBody SportDto sportDto) {
        // Call the service method to update the sport identified by the given ID using
        // the provided sport data transfer object (DTO)
        SportResponse updateSport = sportService.updateSport(id, sportDto);
        return new ResponseEntity<>(updateSport, updateSport.getStatusCode());

    }

    // Annotation to handle GET requests for retrieving all sports by type
    @GetMapping("/get_all_Sport_by_type/{sportType}")

    public ResponseEntity<BaseResponse> getAllSportsByType(@PathVariable SportType sportType) {
        // Call the service method to fetch all sports of the specified type
        BaseResponse sports = sportService.getAllSportsByType(sportType);
        // Return the list of sports by type wrapped in a ResponseEntity, including the
        // status code from the response
        return new ResponseEntity<>(sports, sports.getStatusCode());
    }

    // Annotation to handle DELETE requests for deleting a sport by its ID
    @DeleteMapping("/delete_sport/{id}")

    public ResponseEntity<BaseResponse> deleteSport(@PathVariable Long id) {
        // Call the service method to delete the sport identified by the given ID
        BaseResponse sports = sportService.deleteSport(id);
        // Return the response from the delete operation wrapped in a ResponseEntity,
        // including the status code from the response
        return new ResponseEntity<>(sports, sports.getStatusCode());
    }
}