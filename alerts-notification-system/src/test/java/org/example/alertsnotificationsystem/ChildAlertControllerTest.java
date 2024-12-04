package org.example.alertsnotificationsystem;

import org.example.alertsnotificationsystem.model.Person;
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
public class ChildAlertControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        personRepository.deleteAll();

        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Smith");
        person1.setAddress("TestAddress");
        person1.setCity("TestCity");
        person1.setAge(25);

        Person person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Doe");
        person2.setAddress("TestAddress");
        person2.setCity("TestCity");
        person2.setAge(10);

        personRepository.saveAll(List.of(person1, person2));
    }

    @Test
    void getChildrenAtAddress() throws Exception {
        mockMvc.perform(get("/childAlert?address=TestAddress&city=TestCity"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Jane"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].age").value(10));
    }


}
