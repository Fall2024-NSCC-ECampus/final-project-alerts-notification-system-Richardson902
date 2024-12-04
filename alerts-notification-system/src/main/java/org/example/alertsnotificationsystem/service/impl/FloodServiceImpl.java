package org.example.alertsnotificationsystem.service.impl;

import org.example.alertsnotificationsystem.exception.ResourceNotFoundException;
import org.example.alertsnotificationsystem.model.Firestation;
import org.example.alertsnotificationsystem.model.Person;
import org.example.alertsnotificationsystem.payload.HouseholdResponse;
import org.example.alertsnotificationsystem.payload.PersonMedicalResponse;
import org.example.alertsnotificationsystem.repository.FirestationRepository;
import org.example.alertsnotificationsystem.repository.PersonRepository;
import org.example.alertsnotificationsystem.service.FloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the FloodService interface.
 * Provides methods to get information about households and persons affected by a flood.
 */
@Service
public class FloodServiceImpl implements FloodService {

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Get all households and persons affected by a flood in the cities of the firestations with the given station numbers.
     * @param stationNumbers The station numbers of the firestations.
     * @return A list of responses containing information about the households and persons affected by the flood.
     */
    @Override
    public List<HouseholdResponse> getHouseholdsByStationNumber(List<Integer> stationNumbers) {
        List<Firestation> firestations = firestationRepository.findByStationNumberIn(stationNumbers);

        if (firestations.isEmpty()) {
            throw new ResourceNotFoundException("No firestations found for station numbers: " + stationNumbers);
        }

        // Map each firerstation to its household and persons
        List<HouseholdResponse> responses = firestations.stream()
                .flatMap(firestation -> {
                    List<Person> persons = personRepository.findByCity(firestation.getCity());

                    //Group persons by address
                    Map<String, List<Person>> groupedByAddress = persons.stream()
                            .collect(Collectors.groupingBy(Person::getAddress));

                    //Map each household to household response
                    return groupedByAddress.entrySet().stream()
                            .map(entry -> {
                                String address = entry.getKey();
                                List<PersonMedicalResponse> personResponses = entry.getValue().stream()
                                        .map(p -> PersonMedicalResponse.builder()
                                                .firstName(p.getFirstName())
                                                .lastName(p.getLastName())
                                                .age(p.getAge())
                                                .phone(p.getPhone())
                                                .medicalRecord(p.getMedicalRecord())
                                                .build())
                                        .toList();

                                return HouseholdResponse.builder()
                                        .stationNumber(firestation.getStationNumber())
                                        .address(address)
                                        .persons(personResponses)
                                        .build();
                            });
                })
                .toList();

        return responses;
    }
}
