package org.example.alertsnotificationsystem.service;

import org.example.alertsnotificationsystem.payload.PhoneAlertResponse;

/**
 * Service for phone alerts.
 * Contains methods for getting phone numbers by station number.
 */
public interface PhoneAlertService {
    PhoneAlertResponse getPhoneNumbersByStationNumber(int stationNumber);
}
