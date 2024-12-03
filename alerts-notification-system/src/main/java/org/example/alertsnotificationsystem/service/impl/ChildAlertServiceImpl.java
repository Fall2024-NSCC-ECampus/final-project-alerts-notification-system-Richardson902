package org.example.alertsnotificationsystem.service.impl;

import org.example.alertsnotificationsystem.exception.ResourceNotFoundException;
import org.example.alertsnotificationsystem.model.Person;
import org.example.alertsnotificationsystem.payload.ChildAlertResponse;
import org.example.alertsnotificationsystem.repository.PersonRepository;
import org.example.alertsnotificationsystem.service.ChildAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the ChildAlertService interface.
 * Provides functionality to get children by address and city.
 */
@Service
public class ChildAlertServiceImpl implements ChildAlertService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Fetches children by address and city.
     * @param address the address to search for
     * @param city the city to search for
     * @return a list of ChildAlertResponse objects
     */
    @Override
    public List<ChildAlertResponse> getChildrenByAddress(String address, String city) {

        // Fetch persons by address and city
        List<Person> persons = personRepository.findByAddressAndCity(address, city);

        // Filter for children under 18 and build response
        List<ChildAlertResponse> children = persons.stream()
                .filter(p -> p.getAge() < 18) // filter under 18
                .map(child -> ChildAlertResponse.builder()
                        .firstName(child.getFirstName())
                        .lastName(child.getLastName())
                        .age(child.getAge())
                        .familyMembers(persons.stream()
                                .filter(p -> p.getAge() >= 18) // filter for family members
                                .map(p -> p.getFirstName() + " " + p.getLastName())
                                .collect(Collectors.toList())

                        )
                        .build())
                .toList();

        // Throw exception if no children found
        if (children.isEmpty()) {
            throw new ResourceNotFoundException("No children found for address: " + address + ", city: " + city);
        }

        return children;
    }
}
