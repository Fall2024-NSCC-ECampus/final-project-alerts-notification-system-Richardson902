package org.example.alertsnotificationsystem.service;

import org.example.alertsnotificationsystem.payload.HouseholdResponse;

import java.util.List;

/**
 * Service for flood alerts.
 * Contains methods for getting households by station number.
 */
public interface FloodService {
    List<HouseholdResponse> getHouseholdsByStationNumber(List<Integer> stationNumbers);
}
