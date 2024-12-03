package org.example.alertsnotificationsystem.service;

import org.example.alertsnotificationsystem.payload.FireResponse;
import org.example.alertsnotificationsystem.payload.FirestationResponse;

/**
 * Service for fire stations.
 * Contains methods for getting persons by station number and getting station by address.
 */
public interface FirestationService {
    FirestationResponse getPersonsByStationNumber(int stationNumber);
    FireResponse getStationByAddress(String address, String city);
}
