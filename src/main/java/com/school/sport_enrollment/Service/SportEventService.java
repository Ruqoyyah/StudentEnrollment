package com.school.sport_enrollment.Service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.school.sport_enrollment.Dto.SportDto;
import com.school.sport_enrollment.Dto.SportEventDto;
import com.school.sport_enrollment.Enums.SportType;
import com.school.sport_enrollment.Model.Sport;
import com.school.sport_enrollment.Model.SportEvent;
import com.school.sport_enrollment.Model.User;
import com.school.sport_enrollment.Repository.SportEventRepository;
import com.school.sport_enrollment.Response.BaseResponse;
import com.school.sport_enrollment.Response.SportResponse;
import com.school.sport_enrollment.Utils.Helpers;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service

public class SportEventService {

    private final SportEventRepository sportEventRepository;

    private final SportService sportService;

    private final UserService userService;

    public SportEventService(SportEventRepository sportEventRepository, SportService sportService,
            UserService userService) {
        this.sportEventRepository = sportEventRepository;
        this.sportService = sportService;
        this.userService = userService;

    }

    public BaseResponse createEvent(SportEventDto sportEventDto) {
        try {

            if (Helpers.isStringEmptyOrBlank(sportEventDto.getEventName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event Name cannot be blank");
            }

            Sport sport = sportService.getSportById(sportEventDto.getSportId());

            SportEvent Newevent = new SportEvent();
            Newevent.setEventName(sportEventDto.getEventName());
            Newevent.setEventDate(sportEventDto.getEventDate());
            Newevent.setSport(sport);

            SportEvent createdSport = sportEventRepository.save(Newevent);
            BaseResponse baseResponse = new BaseResponse(createdSport, HttpStatus.OK, "Event successfully created");
            return baseResponse;

        } catch (ResponseStatusException e) {
            String message = e.getReason();
            Integer statusValue = e.getStatusCode().value();
            HttpStatus status = HttpStatus.valueOf(statusValue);

            BaseResponse baseResponse = new BaseResponse(null, status, message);
            return baseResponse;

            // TODO: handle exception
        } catch (Exception e) {

            BaseResponse baseResponse = new BaseResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            return baseResponse;

            // TODO: handle exception
        }

    }

    public BaseResponse getAllSportEvent() {

        try {

            List<SportEvent> allSportEvent = sportEventRepository.findAll();
            BaseResponse baseResponse = new BaseResponse(allSportEvent, HttpStatus.OK, "Event successfully fetched");
            return baseResponse;

        } catch (Exception e) {
            BaseResponse baseResponse = new BaseResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            return baseResponse;
        }

    }

    public BaseResponse deleteEvent(Long id) {
        try {
            Optional<SportEvent> sportEvent = sportEventRepository.findById(id);

            if (!sportEvent.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event ID does not exist");
            }
            sportEventRepository.deleteById(id);
            BaseResponse baseResponse = new BaseResponse(null, HttpStatus.OK, "Event successfully deleted");
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

    public BaseResponse updateSportEvent(Long id, SportEventDto sportEventDto) {

        try {

            if (Helpers.isStringEmptyOrBlank(sportEventDto.getEventName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sport Name cannot be blank");
            }
            Optional<SportEvent> sportEvent = sportEventRepository.findById(id);

            if (!sportEvent.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sport Event does not exist");
            }
            sportEvent.get().setEventName(sportEventDto.getEventName());
            sportEvent.get().setEventDate(sportEventDto.getEventDate());
            SportEvent updateSportEvent = sportEventRepository.save(sportEvent.get());
            BaseResponse baseResponse = new BaseResponse(updateSportEvent, HttpStatus.OK, "Event successfully updated");
            return baseResponse;

        } catch (ResponseStatusException e) {
            String message = e.getReason();
            Integer statusValue = e.getStatusCode().value();
            HttpStatus status = HttpStatus.valueOf(statusValue);

            BaseResponse baseResponse = new BaseResponse(null, status, message);
            return baseResponse;

            // TODO: handle exception
        }

        catch (Exception e) {
            BaseResponse baseResponse = new BaseResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            return baseResponse;
        }

    }

    // public BaseResponse getAllSportsByType(SportType sportType) {
    // try {

    // List<Sport> allSports = sportRepository.findBySportType(sportType);
    // BaseResponse baseResponse = new BaseResponse(allSports, HttpStatus.OK,
    // "Sport successfully fetched by sport type");
    // return baseResponse;

    // } catch (Exception e) {
    // BaseResponse baseResponse = new BaseResponse(null,
    // HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
    // return baseResponse;

    public BaseResponse getAllEvents() {

        try {

            List<SportEvent> allEvents = sportEventRepository.findAll();
            BaseResponse baseResponse = new BaseResponse(allEvents, HttpStatus.OK, "Events successfully fetched");
            return baseResponse;
        }

        catch (ResponseStatusException e) {
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

    public BaseResponse getEventById(Long id) {

        try {

            Optional<SportEvent> Event = sportEventRepository.findById(id);

            if (Event.isEmpty()) {

                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event does not exist");

            }
            BaseResponse baseResponse = new BaseResponse(Event.get(), HttpStatus.OK,
                    "Event successfully fetched");
            return baseResponse;

        } catch (ResponseStatusException e) {
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

    public BaseResponse getEventBySportId(Long sportid) {

        try {

            Sport sport = sportService.getSportById(sportid);
            List<SportEvent> event = sportEventRepository.findBySport(sport);

            BaseResponse baseResponse = new BaseResponse(event, HttpStatus.OK, "Events successfully fetched");
            return baseResponse;

        }

        catch (ResponseStatusException e) {
            String message = e.getReason();
            Integer statusValue = e.getStatusCode().value();
            HttpStatus status = HttpStatus.valueOf(statusValue);

            BaseResponse baseResponse = new BaseResponse(null, status, message);
            return baseResponse;

        } catch (Exception e) {
            BaseResponse baseResponse = new BaseResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occured");
            System.out.println(e);
            return baseResponse;
        }

    }

    public BaseResponse getUpcomingEvents() {
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            List<SportEvent> allUpcomingEvents = sportEventRepository.findUpcomingEvents(currentTime);
            BaseResponse baseResponse = new BaseResponse(allUpcomingEvents, HttpStatus.OK,
                    "Upcoming Events successfully fetched");
            return baseResponse;
        }

        catch (ResponseStatusException e) {
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

    public BaseResponse getPastEvents() {

        try {
            LocalDateTime currentTime = LocalDateTime.now();
            List<SportEvent> allPastEvents = sportEventRepository.findPastEvents(currentTime);
            BaseResponse baseResponse = new BaseResponse(allPastEvents, HttpStatus.OK,
                    "Past Events successfully fetched");
            return baseResponse;
        }

        catch (ResponseStatusException e) {
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

    public BaseResponse getUpcomingEventsBySport(Long sportid) {
        try {
            Sport sport = sportService.getSportById(sportid);
            LocalDateTime currentTime = LocalDateTime.now();
            List<SportEvent> allUpcomingEventsBySport = sportEventRepository.findUpcomingEventsBySport(currentTime,
                    sport);
            BaseResponse baseResponse = new BaseResponse(allUpcomingEventsBySport, HttpStatus.OK,
                    "Upcoming Events successfully fetched");
            return baseResponse;
        }

        catch (ResponseStatusException e) {
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

    public BaseResponse getPastEventsBySport(Long sportid) {

        try {
            Sport sport = sportService.getSportById(sportid);
            LocalDateTime currentTime = LocalDateTime.now();
            List<SportEvent> allPastEventsBySport = sportEventRepository.findPastEventsBySport(currentTime, sport);
            BaseResponse baseResponse = new BaseResponse(allPastEventsBySport, HttpStatus.OK,
                    "Past Events successfully fetched");
            return baseResponse;
        }

        catch (ResponseStatusException e) {
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

    public BaseResponse getEventsForStudent(Long userid) {

        try {

            User user = userService.getUsersById(userid);

            if (user == null) {

                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

            }

            List<Long> sportid = user.getSport().stream().map(Sport::getId).collect(Collectors.toList());

            List<SportEvent> studentEvents = sportEventRepository.findEventBySportId(sportid);

            BaseResponse baseResponse = new BaseResponse(studentEvents, HttpStatus.OK, "Events fetched successfully");
            return baseResponse;

        }

        catch (ResponseStatusException e) {
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
}
