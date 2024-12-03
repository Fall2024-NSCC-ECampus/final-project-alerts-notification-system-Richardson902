package org.example.alertsnotificationsystem.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.example.alertsnotificationsystem.payload.FireResponse;
import org.example.alertsnotificationsystem.payload.FirestationResponse;
import org.example.alertsnotificationsystem.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for firestation related endpoints.
 * Provides endpoints to fetch persons by station number and station by address.
 */
@RestController
@Slf4j
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

    /**
     * Fetches persons by specific station number.
     * @param stationNumber the station number to fetch persons for
     * @return the response containing the persons for the station number
     */
    @GetMapping("/firestation")
    public ResponseEntity<FirestationResponse> getPersonsByStationNumber(@RequestParam int stationNumber) {
        log.info("Fetching persons for station number: {}", stationNumber);
        FirestationResponse response = firestationService.getPersonsByStationNumber(stationNumber);

        log.debug("Response: {}", response);
        return ResponseEntity.ok(response);
    }

    /**
     * Fetches the station number for a specific address in a city.
     * @param address the address to map the persons to the station
     * @param city the city where the station is located
     * @return the response containing the station number and persons involved
     */
    @GetMapping("/fire")
    public ResponseEntity<FireResponse> getStationByAddress(@RequestParam String address, @RequestParam String city) {
        log.info("Fetching station for address: {} in city: {}", address, city);
        FireResponse response = firestationService.getStationByAddress(address, city);

        log.debug("Response: {}", response);
        return ResponseEntity.ok(response);
    }
}
