package org.example.alertsnotificationsystem.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.example.alertsnotificationsystem.payload.ChildAlertResponse;
import org.example.alertsnotificationsystem.service.ChildAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for child alerts.
 * Provides endpoints for fetching children and their family members at a given address.
 */
@RestController
@RequestMapping("/childAlert")
@Slf4j
public class ChildAlertController {

    @Autowired
    private ChildAlertService childAlertService;

    /**
     * Endpoint to get children and their family members at a given address.
     * @param address the address to search for children
     * @param city the city which the address is located
     * @return a ResponseEntity containing a list of ChildAlertResponse objects
     */
    @GetMapping
    public ResponseEntity<List<ChildAlertResponse>> getChildrenAtAddress(@RequestParam String address, @RequestParam String city) {
        log.info("Fetching children at address: {}, city: {}", address, city);
        List<ChildAlertResponse> response = childAlertService.getChildrenByAddress(address, city);

        log.debug("Response: {}", response);
        return ResponseEntity.ok(response);
    }
}
