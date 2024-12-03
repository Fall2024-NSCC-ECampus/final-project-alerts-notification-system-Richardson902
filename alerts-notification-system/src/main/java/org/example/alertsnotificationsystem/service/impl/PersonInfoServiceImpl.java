package org.example.alertsnotificationsystem.service.impl;

import org.example.alertsnotificationsystem.exception.ResourceNotFoundException;
import org.example.alertsnotificationsystem.model.Person;
import org.example.alertsnotificationsystem.payload.EmailResponse;
import org.example.alertsnotificationsystem.payload.PersonInfoResponse;
import org.example.alertsnotificationsystem.repository.PersonRepository;
import org.example.alertsnotificationsystem.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the PersonInfoService interface.
 * Provides methods to get information about persons.
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Get information about persons with the given first name and last name.
     * @param firstName The first name of the person.
     * @param lastName The last name of the person.
     * @return A list of responses containing information about the persons.
     */
    @Override
    public List<PersonInfoResponse> getPersonInfoByName(String firstName, String lastName) {
        List<Person> persons = personRepository.findByFirstNameAndLastName(firstName, lastName);

        if (persons.isEmpty()) {
            throw new ResourceNotFoundException("No persons found for first name: " + firstName + ", last name: " + lastName);
        }

        List<PersonInfoResponse> personInfoResponses = persons.stream()
                .map(p -> PersonInfoResponse.builder()
                        .firstName(p.getFirstName())
                        .lastName(p.getLastName())
                        .address(p.getAddress())
                        .city(p.getCity())
                        .postalCode(p.getPostalCode())
                        .phoneNumber(p.getPhone())
                        .email(p.getEmail())
                        .medicalRecord(p.getMedicalRecord())
                        .build())
                .toList();

        return personInfoResponses;
    }

    /**
     * Get the emails of all persons living in the city with the given name.
     * @param city The name of the city.
     * @return A list of responses containing the emails of the persons.
     */
    @Override
    public List<EmailResponse> getEmailsByCity(String city) {
        List<Person> persons = personRepository.findByCity(city);

        if (persons.isEmpty()) {
            throw new ResourceNotFoundException("No persons found for city: " + city);
        }

        List<EmailResponse> emailResponses = persons.stream()
                .map(p -> EmailResponse.builder()
                        .email(p.getEmail())
                        .build())
                .toList();

        return emailResponses;
    }
}
