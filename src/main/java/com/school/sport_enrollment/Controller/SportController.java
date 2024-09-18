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

public class SportController {
    private final SportService sportService;

    @Autowired
    public SportController(SportService sportService) {
        this.sportService = sportService;

    }

    @PostMapping("/create_sport")
    public ResponseEntity<SportResponse> createSport(@RequestBody SportDto sportDto) {
        System.out.println(sportDto.getSportName());
        SportResponse createdSport = sportService.createSport(sportDto);
        return new ResponseEntity<>(createdSport, createdSport.getStatusCode());

    }

    @GetMapping("/get_all")

    public ResponseEntity<BaseResponse> getAllSports() {
        BaseResponse sports = sportService.getAllSports();
        return new ResponseEntity<>(sports, sports.getStatusCode());

    }

    @PutMapping("/update_sport/{id}")

    public ResponseEntity<SportResponse> updateSports(@PathVariable Long id, @RequestBody SportDto sportDto) {
        SportResponse updateSport = sportService.updateSport(id, sportDto);
        return new ResponseEntity<>(updateSport, updateSport.getStatusCode());

    }

    @GetMapping("/get_all_Sport_by_type/{sportType}")

    public ResponseEntity<BaseResponse> getAllSportsByType(@PathVariable SportType sportType) {
        BaseResponse sports = sportService.getAllSportsByType(sportType);
        return new ResponseEntity<>(sports, sports.getStatusCode());
    }

    @DeleteMapping("/delete_sport/{id}")

    public ResponseEntity<BaseResponse> deleteSport(@PathVariable Long id) {

        BaseResponse sports = sportService.deleteSport(id);
        return new ResponseEntity<>(sports, sports.getStatusCode());
    }
}