package org.example.alertsnotificationsystem.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.example.alertsnotificationsystem.payload.EmailResponse;
import org.example.alertsnotificationsystem.payload.PersonInfoResponse;
import org.example.alertsnotificationsystem.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest controller for person info related endpoints.
 * Provides endpoints to fetch person info by name and emails by city.
 */
@RestController
@Slf4j
public class PersonInfoController {

    @Autowired
    private PersonInfoService personInfoService;

    /**
     * Fetches person info by first and last name.
     * @param firstName the first name of the person
     * @param lastName the last name of the person
     * @return the response containing the person info
     */
    @GetMapping("/personInfo")
    public ResponseEntity<List<PersonInfoResponse>> getPersonInfoByName(@RequestParam String firstName, @RequestParam String lastName) {
        log.info("Fetching person info for first name: {} and last name: {}", firstName, lastName);
        List<PersonInfoResponse> response = personInfoService.getPersonInfoByName(firstName, lastName);

        log.debug("Response: {}", response);
        return ResponseEntity.ok(response);
    }

    /**
     * Fetches emails for a specific city.
     * @param city the city to fetch emails for
     * @return the response containing the emails for the persons in the city
     */
    @GetMapping("/communityEmail")
    public ResponseEntity<List<EmailResponse>> getEmailsByCity(@RequestParam String city) {
        log.info("Fetching emails for city: {}", city);
        List<EmailResponse> response = personInfoService.getEmailsByCity(city);

        log.debug("Response: {}", response);
        return ResponseEntity.ok(response);
    }
}
