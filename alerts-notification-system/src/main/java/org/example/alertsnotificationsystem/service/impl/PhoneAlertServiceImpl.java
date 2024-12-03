package org.example.alertsnotificationsystem.service.impl;

import org.example.alertsnotificationsystem.exception.ResourceNotFoundException;
import org.example.alertsnotificationsystem.model.Firestation;
import org.example.alertsnotificationsystem.model.Person;
import org.example.alertsnotificationsystem.payload.PhoneAlertResponse;
import org.example.alertsnotificationsystem.repository.FirestationRepository;
import org.example.alertsnotificationsystem.repository.PersonRepository;
import org.example.alertsnotificationsystem.service.PhoneAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the PhoneAlertService interface.
 * Provides methods to get phone numbers of persons living in the city of a firestation.
 */
@Service
public class PhoneAlertServiceImpl implements PhoneAlertService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FirestationRepository firestationRepository;

    /**
     * Get the phone numbers of all persons living in the city of the firestation with the given station number.
     * @param stationNumber The station number of the firestation.
     * @return A response containing the phone numbers of the persons.
     */
    @Override
    public PhoneAlertResponse getPhoneNumbersByStationNumber(int stationNumber) {
        Optional<Firestation> firestationOptional = firestationRepository.findByStationNumber(stationNumber);

        // If no firestations are found, return an empty response
        if (firestationOptional.isEmpty()) {
            throw new ResourceNotFoundException("Firestation not found for station number: " + stationNumber);
        }

        Firestation firestation = firestationOptional.get();
        // Get all persons living in the city of the firestation
        List<Person> persons = personRepository.findByCity(firestation.getCity());

        List<String> phoneNumbers = persons.stream()
                .map(Person::getPhone)
                .toList();

        return PhoneAlertResponse.builder()
                .phoneNumbers(phoneNumbers)
                .build();
    }
}
