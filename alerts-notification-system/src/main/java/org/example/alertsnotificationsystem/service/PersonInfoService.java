package org.example.alertsnotificationsystem.service;

import org.example.alertsnotificationsystem.payload.EmailResponse;
import org.example.alertsnotificationsystem.payload.PersonInfoResponse;

import java.util.List;

/**
 * Service for person information.
 * Contains methods for getting person information by name and emails by city.
 */
public interface PersonInfoService {
    List<PersonInfoResponse> getPersonInfoByName(String firstName, String lastName);
    List<EmailResponse> getEmailsByCity(String city);
}
