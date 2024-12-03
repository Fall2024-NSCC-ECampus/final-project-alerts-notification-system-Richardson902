package org.example.alertsnotificationsystem.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.example.alertsnotificationsystem.payload.PhoneAlertResponse;
import org.example.alertsnotificationsystem.service.PhoneAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Rest controller for phone alert related endpoints.
 * Provides endpoints to fetch phone numbers by station number.
 */
@Controller
@RequestMapping("/phoneAlert")
@Slf4j
public class PhoneAlertController {

    @Autowired
    private PhoneAlertService phoneAlertService;

    /**
     * Fetches phone numbers of persons for a specific firestation.
     * @param firestation the firestation number to fetch phone numbers for
     * @return the response containing the phone numbers of the persons
     */
    @GetMapping
    public ResponseEntity<PhoneAlertResponse> getPhoneNumbersByStationNumber(@RequestParam int firestation) {
        log.info("Fetching phone numbers for firestation: {}", firestation);
        PhoneAlertResponse response = phoneAlertService.getPhoneNumbersByStationNumber(firestation);

        log.debug("Resonses: {}", response);
        return ResponseEntity.ok(response);


    }
}
