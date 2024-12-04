package org.example.alertsnotificationsystem;

import org.example.alertsnotificationsystem.model.MedicalRecord;
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
public class PersonInfoControllerTest {

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
        person1.setPostalCode("12345");
        person1.setPhone("555-5555");
        person1.setEmail("john@email.com");

        Person person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Doe");
        person2.setAddress("OtherTestAddress");
        person2.setCity("TestCity");
        person2.setPostalCode("12345");
        person2.setPhone("555-5555");
        person2.setEmail("jane@email.com");

        personRepository.saveAll(List.of(person1, person2));

        MedicalRecord medicalRecord1 = new MedicalRecord();
        medicalRecord1.setMedications(List.of("TestMedication1, 100mg"));
        medicalRecord1.setAllergies(List.of("TestAllergy1"));
        medicalRecord1.setPerson(person1);

        MedicalRecord medicalRecord2 = new MedicalRecord();
        medicalRecord2.setMedications(List.of("TestMedication2, 200mg"));
        medicalRecord2.setAllergies(List.of("TestAllergy2"));
        medicalRecord2.setPerson(person2);

        person1.setMedicalRecord(medicalRecord1);
        person2.setMedicalRecord(medicalRecord2);

        personRepository.saveAll(List.of(person1, person2));
    }

    @Test
    void getPersonInfoByName1() throws Exception {
        mockMvc.perform(get("/personInfo?firstName=John&lastName=Smith"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Smith"))
                .andExpect(jsonPath("$[0].address").value("TestAddress"))
                .andExpect(jsonPath("$[0].city").value("TestCity"))
                .andExpect(jsonPath("$[0].postalCode").value("12345"))
                .andExpect(jsonPath("$[0].phoneNumber").value("555-5555"))
                .andExpect(jsonPath("$[0].email").value("john@email.com"))
                .andExpect(jsonPath("$[0].medicalRecord.medications[0]").value("TestMedication1, 100mg"));
    }

    @Test
    void getPersonInfoByName2() throws Exception {
        mockMvc.perform(get("/personInfo?firstName=Jane&lastName=Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Jane"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].address").value("OtherTestAddress"))
                .andExpect(jsonPath("$[0].city").value("TestCity"))
                .andExpect(jsonPath("$[0].postalCode").value("12345"))
                .andExpect(jsonPath("$[0].phoneNumber").value("555-5555"))
                .andExpect(jsonPath("$[0].email").value("jane@email.com"))
                .andExpect(jsonPath("$[0].medicalRecord.medications[0]").value("TestMedication2, 200mg"));
    }

    @Test
    void getEmailsByCity() throws Exception {
        mockMvc.perform(get("/communityEmail?city=TestCity"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("john@email.com"))
                .andExpect(jsonPath("$[1].email").value("jane@email.com"));
    }
}
