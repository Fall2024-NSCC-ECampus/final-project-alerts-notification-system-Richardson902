package org.example.alertsnotificationsystem.service.impl;

import org.example.alertsnotificationsystem.exception.ResourceNotFoundException;
import org.example.alertsnotificationsystem.model.Firestation;
import org.example.alertsnotificationsystem.model.Person;
import org.example.alertsnotificationsystem.payload.FireResponse;
import org.example.alertsnotificationsystem.payload.FirestationResponse;
import org.example.alertsnotificationsystem.payload.PersonMedicalResponse;
import org.example.alertsnotificationsystem.payload.PersonResponse;
import org.example.alertsnotificationsystem.repository.FirestationRepository;
import org.example.alertsnotificationsystem.repository.PersonRepository;
import org.example.alertsnotificationsystem.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the FirestationService interface.
 * Provides methods to get information about firestations and persons.
 */
@Service
public class FirestationServiceImpl implements FirestationService {

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Get all persons living in the city of the firestation with the given station number.
     * @param stationNumber The station number of the firestation.
     * @return A response containing information about the persons living in the city of the firestation.
     */
    @Override
    public FirestationResponse getPersonsByStationNumber(int stationNumber) {
        Optional<Firestation> firestationOptional = firestationRepository.findByStationNumber(stationNumber);

        // If no firestations are found, return an empty response
        if (firestationOptional.isEmpty()) {
            throw new ResourceNotFoundException("Firestation not found for station number: " + stationNumber);
        }

        Firestation firestation = firestationOptional.get();

        // Get all persons living in the city of the firestation
        List<Person> persons = personRepository.findByCity(firestation.getCity());

        // Count the number of adults and children
        long adults = persons.stream().filter(p -> p.getAge() > 18).count();
        long children = persons.size() - adults;

        // Map the persons to a response object
        List<PersonResponse> personResponses = persons.stream()
                .map(p -> PersonResponse.builder()
                        .firstName(p.getFirstName())
                        .lastName(p.getLastName())
                        .address(p.getAddress())
                        .phone(p.getPhone())
                        .build())
                .toList();

        // Build the response object
        return FirestationResponse.builder()
                .persons(personResponses)
                .numberOfAdults(adults)
                .numberOfChildren(children)
                .build();
    }

    /**
     * Get the firestation number and persons living at the given address in the given city.
     * @param address The address of the person.
     * @param city The city of the person.
     * @return the firestation number and persons living at the given address in the given city.
     */
    @Override
    public FireResponse getStationByAddress(String address, String city) {
        Optional<Firestation> firestationOptional = firestationRepository.findByCity(city);

        if (firestationOptional.isEmpty()) {
            throw new ResourceNotFoundException("Firestation not found for address: " + address + ", city: " + city);
        }

        Firestation firestation = firestationOptional.get();
        int stationNumber = firestation.getStationNumber();

        List<Person> persons = personRepository.findByAddressAndCity(address, city);

        // Map the persons to a response object
        List<PersonMedicalResponse> personMedicalResponse = persons.stream()
                .map(p -> PersonMedicalResponse.builder()
                        .firstName(p.getFirstName())
                        .lastName(p.getLastName())
                        .age(p.getAge())
                        .phone(p.getPhone())
                        .medicalRecord(p.getMedicalRecord())
                        .build())
                .toList();

        // Build the response object
        return FireResponse.builder()
                .stationNumber(stationNumber)
                .persons(personMedicalResponse)
                .build();


    }
}
