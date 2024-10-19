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
import com.school.sport_enrollment.Dto.SportEventDto;
import com.school.sport_enrollment.Response.BaseResponse;
import com.school.sport_enrollment.Response.SportResponse;
import com.school.sport_enrollment.Service.SportEventService;

@RestController
@RequestMapping("/api/sportevents")

public class SportEventController {

    @Autowired

    private final SportEventService sportEventService;

    public SportEventController(SportEventService sportEventService) {
        this.sportEventService = sportEventService;
    }

    @PostMapping("/create_event")
    public ResponseEntity<BaseResponse> createEvent(@RequestBody SportEventDto sportEventDto) {
        System.out.println(sportEventDto.getEventName());

        BaseResponse createdEvent = sportEventService.createEvent(sportEventDto);
        return new ResponseEntity<>(createdEvent, createdEvent.getStatusCode());

    }

    @DeleteMapping("/delete_event/{id}")

    public ResponseEntity<BaseResponse> deleteEvent(@PathVariable Long id) {

        BaseResponse sportEvent = sportEventService.deleteEvent(id);
        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());

    }

    @PutMapping("/update_event/{id}")

    public ResponseEntity<BaseResponse> updateSportEvent(@PathVariable Long id,
            @RequestBody SportEventDto sportEventDto) {
        System.out.println(id);
        BaseResponse updateSportEvent = sportEventService.updateSportEvent(id, sportEventDto);
        return new ResponseEntity<>(updateSportEvent, updateSportEvent.getStatusCode());

    }

    @GetMapping("/get_allevents")

    public ResponseEntity<BaseResponse> getAllEvents() {
        BaseResponse sportEvent = sportEventService.getAllEvents();
        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());
    }

    @GetMapping("/get_event_by_id/{id}")

    public ResponseEntity<BaseResponse> getEventById(@PathVariable Long id) {

        BaseResponse event = sportEventService.getEventById(id);
        return new ResponseEntity<>(event, event.getStatusCode());
    }

    @GetMapping("/get_event_by_sportid/{sportid}")

    public ResponseEntity<BaseResponse> getEventBySportId(@PathVariable Long sportid) {

        BaseResponse event = sportEventService.getEventBySportId(sportid);
        return new ResponseEntity<>(event, event.getStatusCode());
    }

    @GetMapping("/get_upcoming_events")

    public ResponseEntity<BaseResponse> getUpcomingEvents() {
        BaseResponse sportEvent = sportEventService.getUpcomingEvents();
        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());
    }

    @GetMapping("/get_past_events")

    public ResponseEntity<BaseResponse> getPastEvents() {
        BaseResponse sportEvent = sportEventService.getPastEvents();
        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());
    }

    @GetMapping("/get_upcoming_eventsbysport/{sportid}")

    public ResponseEntity<BaseResponse> getUpcomingEventsBySport(@PathVariable Long sportid) {
        BaseResponse sportEvent = sportEventService.getUpcomingEventsBySport(sportid);
        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());
    }

    @GetMapping("/get_past_eventsbysport/{sportid}")

    public ResponseEntity<BaseResponse> getPastEventsBySport(@PathVariable Long sportid) {
        BaseResponse sportEvent = sportEventService.getPastEventsBySport(sportid);
        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());
    }

    @GetMapping("/get_event_byuserid/{userid}")

    public ResponseEntity<BaseResponse> getEventsForStudent(@PathVariable Long userid) {
        BaseResponse sportEvent = sportEventService.getEventsForStudent(userid);

        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());
    }
}