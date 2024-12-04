package org.example.alertsnotificationsystem;

import org.example.alertsnotificationsystem.model.Firestation;
import org.example.alertsnotificationsystem.model.Person;
import org.example.alertsnotificationsystem.repository.FirestationRepository;
import org.example.alertsnotificationsystem.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PhoneAlertControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FirestationRepository firestationRepository;

    @BeforeEach
    void setUp() {
        personRepository.deleteAll();
        firestationRepository.deleteAll();

        Firestation firestation = new Firestation();
        firestation.setStationNumber(1);
        firestation.setCity("TestCity");
        firestationRepository.save(firestation);

        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Smith");
        person1.setAddress("TestAddress");
        person1.setCity("TestCity");
        person1.setPhone("555-5555");

        Person person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Doe");
        person2.setAddress("OtherTestAddress");
        person2.setCity("TestCity");
        person2.setPhone("555-1234");

        personRepository.saveAll(List.of(person1, person2));
    }

    @Test
    void getPhoneNumbersByStationNumber() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumbers[0]").value("555-5555"))
                .andExpect(jsonPath("$.phoneNumbers[1]").value("555-1234"));
    }


}
