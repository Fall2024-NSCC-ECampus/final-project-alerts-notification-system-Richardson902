package org.example.alertsnotificationsystem.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.example.alertsnotificationsystem.payload.HouseholdResponse;
import org.example.alertsnotificationsystem.service.FloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Rest controller for flood related endpoints.
 * Fetches households for specific stations.
 */
@Controller
@RequestMapping("/flood")
@Slf4j
public class FloodController {

    @Autowired
    private FloodService floodService;

    /**
     * Fetches households for specific stations.
     * @param stationNumbers the station numbers to fetch households for
     * @return the response containing the households for the stations
     */
    @GetMapping("/stations")
    public ResponseEntity<List<HouseholdResponse>> getHouseholdsByStations(@RequestParam("stations") List<Integer> stationNumbers) {
        log.info("Fetching households for stations: {}", stationNumbers);
        List<HouseholdResponse> households = floodService.getHouseholdsByStationNumber(stationNumbers);

        log.debug("Response: {}", households);
        return ResponseEntity.ok(households);
    }
}
