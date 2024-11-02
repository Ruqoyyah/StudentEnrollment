package com.school.sport_enrollment.Service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.school.sport_enrollment.Dto.SportDto;
import com.school.sport_enrollment.Enums.SportType;
import com.school.sport_enrollment.Model.Sport;
import com.school.sport_enrollment.Model.User;
import com.school.sport_enrollment.Repository.SportRepository;
import com.school.sport_enrollment.Response.BaseResponse;
import com.school.sport_enrollment.Response.SportResponse;
import com.school.sport_enrollment.Response.UserResponse;
import com.school.sport_enrollment.Utils.Helpers;

@Service
public class SportService {

    private final SportRepository sportRepository;
    public Object getAllSports;

    @Autowired
    public SportService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public BaseResponse getAllSports() {

        try {

            List<Sport> allSports = sportRepository.findAll();
            BaseResponse baseResponse = new BaseResponse(allSports, HttpStatus.OK, "Sport successfully fetched");
            return baseResponse;

        } catch (Exception e) {
            BaseResponse baseResponse = new BaseResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            return baseResponse;
        }

    }

    public SportResponse createSport(SportDto sportDto) {
        try {

            if (Helpers.isStringEmptyOrBlank(sportDto.getSportName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sport Name cannot be blank");
            }

            if (sportDto.getYear() == null || Helpers.isStringEmptyOrBlank(sportDto.getSeason())) {

                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sport Year cannot be blank");
            }
            Optional<Sport> sport = sportRepository.findBySportNameAndYear(sportDto.getSportName(), sportDto.getYear());

            if (sport.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sport already exist");
            }

            Sport Newsport = new Sport();
            Newsport.setSportName(sportDto.getSportName());
            Newsport.setSportType(sportDto.getSportType());
            Newsport.setEnrollmentDeadline(sportDto.getEnrollmentDeadline());
            Newsport.setYear(sportDto.getYear());
            Newsport.setSeason(sportDto.getSeason());
            Sport createdSport = sportRepository.save(Newsport);
            SportResponse sportResponse = new SportResponse(createdSport, HttpStatus.OK, "Sport successfully created");
            return sportResponse;

        } catch (ResponseStatusException e) {
            String message = e.getReason();
            Integer statusValue = e.getStatusCode().value();
            HttpStatus status = HttpStatus.valueOf(statusValue);

            SportResponse sportResponse = new SportResponse(null, status, message);
            return sportResponse;

            // TODO: handle exception
        } catch (Exception e) {

            SportResponse sportResponse = new SportResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            return sportResponse;

            // TODO: handle exception
        }

    }

    public SportResponse updateSport(Long id, SportDto sportDto) {

        try {

            if (Helpers.isStringEmptyOrBlank(sportDto.getSportName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sport Name cannot be blank");
            }
            Optional<Sport> sport = sportRepository.findById(id);

            if (!sport.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sport ID does not exist");
            }
            sport.get().setSportName(sportDto.getSportName());
            sport.get().setSportType(sportDto.getSportType());
            Sport updateSport = sportRepository.save(sport.get());
            SportResponse sportResponse = new SportResponse(updateSport, HttpStatus.OK, "Sport successfully updated");
            return sportResponse;

        } catch (ResponseStatusException e) {
            String message = e.getReason();
            Integer statusValue = e.getStatusCode().value();
            HttpStatus status = HttpStatus.valueOf(statusValue);

            SportResponse sportResponse = new SportResponse(null, status, message);
            return sportResponse;

            // TODO: handle exception
        }

        catch (Exception e) {
            SportResponse sportResponse = new SportResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            return sportResponse;
        }

    }

    public BaseResponse getAllSportsByType(SportType sportType) {
        try {

            List<Sport> allSports = sportRepository.findBySportType(sportType);
            BaseResponse baseResponse = new BaseResponse(allSports, HttpStatus.OK,
                    "Sport successfully fetched by sport type");
            return baseResponse;

        } catch (Exception e) {
            BaseResponse baseResponse = new BaseResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            return baseResponse;
        }

    }

    public BaseResponse deleteSport(Long id) {
        try {
            Optional<Sport> sport = sportRepository.findById(id);

            if (!sport.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sport ID does not exist");
            }
            List<User> enrolledUsers = sport.get().getUsers();
            if (enrolledUsers.size() > 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Sport cannot be deleted,uneroll users registered for the sport to delete");
            }
            sportRepository.deleteById(id);
            BaseResponse baseResponse = new BaseResponse(null, HttpStatus.OK, "Sport successfully deleted");
            return baseResponse;

        } catch (

        ResponseStatusException e) {
            String message = e.getReason();
            Integer statusValue = e.getStatusCode().value();
            HttpStatus status = HttpStatus.valueOf(statusValue);

            BaseResponse baseResponse = new BaseResponse(null, status, message);
            return baseResponse;

        } catch (Exception e) {
            BaseResponse baseResponse = new BaseResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            return baseResponse;
        }
    }

    public Sport getSportById(Long id) {

        Optional<Sport> sport = sportRepository.findById(id);
        if (!sport.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sport ID does not exist");
        }
        return sport.get();
    }

}
