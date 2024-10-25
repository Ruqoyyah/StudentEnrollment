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

// SportEventController is responsible for handling requests related to sport
// events

public class SportEventController {

    @Autowired

    private final SportEventService sportEventService;

    // Constructor that injects the SportEventService dependency into the controller
    public SportEventController(SportEventService sportEventService) {
        this.sportEventService = sportEventService;
    }

    // Annotation Handles POST requests for creating a new sport event
    @PostMapping("/create_event")
    public ResponseEntity<BaseResponse> createEvent(@RequestBody SportEventDto sportEventDto) {
        System.out.println(sportEventDto.getEventName());
        // Calls the service layer to create the event and store the result in
        // createdEvent
        BaseResponse createdEvent = sportEventService.createEvent(sportEventDto);
        return new ResponseEntity<>(createdEvent, createdEvent.getStatusCode());

    }

    // Anotation that Handles the DELETE requests for deleting a specific sport
    // event by its ID
    @DeleteMapping("/delete_event/{id}")

    public ResponseEntity<BaseResponse> deleteEvent(@PathVariable Long id) {

        // Calls the service layer to delete the event with the given ID and stores the
        // result in sportEvent
        BaseResponse sportEvent = sportEventService.deleteEvent(id);
        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());

    }

    // Annotation that Handles PUT requests for updating an existing sport event
    @PutMapping("/update_event/{id}")

    public ResponseEntity<BaseResponse> updateSportEvent(@PathVariable Long id,
            @RequestBody SportEventDto sportEventDto) {
        System.out.println(id);
        // Call the service layer to handle the update process and store the result
        BaseResponse updateSportEvent = sportEventService.updateSportEvent(id, sportEventDto);
        return new ResponseEntity<>(updateSportEvent, updateSportEvent.getStatusCode());

    }

    // Annotation that Handles HTTP GET requests to retrieve all sports events
    @GetMapping("/get_allevents")

    public ResponseEntity<BaseResponse> getAllEvents() {

        // Call the service layer to fetch all events
        BaseResponse sportEvent = sportEventService.getAllEvents();
        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());
    }

    // Annotation to handle GET requests by getting event by ID
    @GetMapping("/get_event_by_id/{id}")

    public ResponseEntity<BaseResponse> getEventById(@PathVariable Long id) {

        BaseResponse event = sportEventService.getEventById(id);
        return new ResponseEntity<>(event, event.getStatusCode());
    }

    // Annotation to handle GET requests for retrieving events by sport ID
    @GetMapping("/get_event_by_sportid/{sportid}")

    public ResponseEntity<BaseResponse> getEventBySportId(@PathVariable Long sportid) {
        // Call the service method to fetch the event details based on the provided
        // sport ID
        BaseResponse event = sportEventService.getEventBySportId(sportid);
        return new ResponseEntity<>(event, event.getStatusCode());
    }

    // Annotation to handle GET requests retrieving upcoming events
    @GetMapping("/get_upcoming_events")
    // Method to retrieve upcoming events and return them in a ResponseEntity
    public ResponseEntity<BaseResponse> getUpcomingEvents() {
        // Call the service method to fetch upcoming events
        BaseResponse sportEvent = sportEventService.getUpcomingEvents();
        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());
    }

    // Annotation to handle GET requests for retrieving past events
    @GetMapping("/get_past_events")

    public ResponseEntity<BaseResponse> getPastEvents() {
        // Call the service method to fetch the past events
        BaseResponse sportEvent = sportEventService.getPastEvents();
        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());
    }

    // Annotation to handle GET requests for retrieving upcoming events by sport ID
    @GetMapping("/get_upcoming_eventsbysport/{sportid}")

    public ResponseEntity<BaseResponse> getUpcomingEventsBySport(@PathVariable Long sportid) {
        // Call the service method to fetch upcoming events for the specified sport ID
        BaseResponse sportEvent = sportEventService.getUpcomingEventsBySport(sportid);
        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());
    }

    // Annotation to handle GET requests for retrieving upcoming events by sport ID
    @GetMapping("/get_past_eventsbysport/{sportid}")

    public ResponseEntity<BaseResponse> getPastEventsBySport(@PathVariable Long sportid) {
        // Call the service method to fetch upcoming events for the specified sport ID
        BaseResponse sportEvent = sportEventService.getPastEventsBySport(sportid);
        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());
    }

    // Annotation to handle GET requests at the specified path for retrieving events
    // by user ID
    @GetMapping("/get_event_byuserid/{userid}")

    public ResponseEntity<BaseResponse> getEventsForStudent(@PathVariable Long userid) {
        // Call the service method to fetch events associated with the specified user ID
        BaseResponse sportEvent = sportEventService.getEventsForStudent(userid);

        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());
    }

    // Annotation to handle GET requests for retrieving past events by user ID
    @GetMapping("/get_past_event_byuserid/{userid}")

    public ResponseEntity<BaseResponse> getPastEventsForStudent(@PathVariable Long userid) {
        // Call the service method to fetch past events associated with the specified
        // user ID
        BaseResponse sportEvent = sportEventService.getPastEventsForStudent(userid);

        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());
    }

    // Annotation to handle GET requests for retrieving upcoming events by user ID
    @GetMapping("/get_Upcoming_event_byuserid/{userid}")

    public ResponseEntity<BaseResponse> getUpcomingEventsForStudent(@PathVariable Long userid) {
        // Call the service method to fetch upcoming events associated with the
        // specified user ID
        BaseResponse sportEvent = sportEventService.getUpcomingEventsForStudent(userid);
        // Return the past events for the specified user wrapped in a ResponseEntity,
        // including the status code from the event
        return new ResponseEntity<>(sportEvent, sportEvent.getStatusCode());
    }
}