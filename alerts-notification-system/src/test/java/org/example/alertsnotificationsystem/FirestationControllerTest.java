package org.example.alertsnotificationsystem;

import org.example.alertsnotificationsystem.model.Firestation;
import org.example.alertsnotificationsystem.model.MedicalRecord;
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
public class FirestationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    FirestationRepository firestationRepository;

    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        firestationRepository.deleteAll();
        personRepository.deleteAll();

        Firestation firestation = new Firestation();
        firestation.setStationNumber(1);
        firestation.setCity("TestCity");
        firestationRepository.save(firestation);

        // Currently there's a bug with testing when adding medical records. The test will pass but it leaves the database ugly. I'll fix it later.
        MedicalRecord medicalRecord1 = new MedicalRecord();
        medicalRecord1.setMedications(List.of("TestMedication1, 100mg"));
        medicalRecord1.setAllergies(List.of("TestAllergy1"));

        MedicalRecord medicalRecord2 = new MedicalRecord();
        medicalRecord2.setMedications(List.of("TestMedication2, 200mg"));
        medicalRecord2.setAllergies(List.of("TestAllergy2"));

        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Smith");
        person1.setAddress("TestAddress");
        person1.setCity("TestCity");
        person1.setPhone("555-5555");
        person1.setAge(25);
        person1.setMedicalRecord(medicalRecord1);
        medicalRecord1.setPerson(person1);

        Person person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Doe");
        person2.setAddress("TestAddress");
        person2.setCity("TestCity");
        person2.setPhone("555-5556");
        person2.setAge(10);
        person2.setMedicalRecord(medicalRecord2);
        medicalRecord2.setPerson(person2);

        personRepository.saveAll(List.of(person1, person2));
    }

    @Test
    void testGetPersonsByStationNumber() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persons[0].firstName").value("John"))
                .andExpect(jsonPath("$.persons[0].lastName").value("Smith"))
                .andExpect(jsonPath("$.persons[0].address").value("TestAddress"))
                .andExpect(jsonPath("$.persons[0].phone").value("555-5555"))
                .andExpect(jsonPath("$.persons[1].firstName").value("Jane"))
                .andExpect(jsonPath("$.persons[1].lastName").value("Doe"))
                .andExpect(jsonPath("$.persons[1].address").value("TestAddress"))
                .andExpect(jsonPath("$.persons[1].phone").value("555-5556"));
    }

    @Test
    void testGetStationByAddress() throws Exception {
        mockMvc.perform(get("/fire?address=TestAddress&city=TestCity"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stationNumber").value(1))
                .andExpect(jsonPath("$.persons[0].firstName").value("John"))
                .andExpect(jsonPath("$.persons[0].lastName").value("Smith"))
                .andExpect(jsonPath("$.persons[0].phone").value("555-5555"))
                .andExpect(jsonPath("$.persons[0].age").value(25))
                .andExpect(jsonPath("$.persons[0].medicalRecord.medications[0]").value("TestMedication1, 100mg"))
                .andExpect(jsonPath("$.persons[0].medicalRecord.allergies[0]").value("TestAllergy1"))
                .andExpect(jsonPath("$.persons[1].firstName").value("Jane"))
                .andExpect(jsonPath("$.persons[1].lastName").value("Doe"))
                .andExpect(jsonPath("$.persons[1].phone").value("555-5556"))
                .andExpect(jsonPath("$.persons[1].age").value(10))
                .andExpect(jsonPath("$.persons[1].medicalRecord.medications[0]").value("TestMedication2, 200mg"))
                .andExpect(jsonPath("$.persons[1].medicalRecord.allergies[0]").value("TestAllergy2"));
    }
}
