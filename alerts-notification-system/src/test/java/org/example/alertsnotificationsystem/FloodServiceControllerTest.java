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
public class FloodServiceControllerTest {

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

        Firestation firestation1 = new Firestation();
        firestation1.setStationNumber(2);
        firestation1.setCity("OtherTestCity");

        firestationRepository.saveAll(List.of(firestation, firestation1));

        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Smith");
        person1.setAddress("TestAddress");
        person1.setCity("TestCity");
        person1.setPhone("555-5555");
        person1.setAge(25);


        Person person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Doe");
        person2.setAddress("TestAddress");
        person2.setCity("TestCity");
        person2.setPhone("555-5556");
        person2.setAge(10);


        Person person3 = new Person();
        person3.setFirstName("John");
        person3.setLastName("Doe");
        person3.setAddress("OtherTestAddress");
        person3.setCity("OtherTestCity");
        person3.setPhone("555-5557");
        person3.setAge(30);

        personRepository.saveAll(List.of(person1, person2, person3));

        MedicalRecord medicalRecord1 = new MedicalRecord();
        medicalRecord1.setMedications(List.of("TestMedication1, 100mg"));
        medicalRecord1.setAllergies(List.of("TestAllergy1"));
        medicalRecord1.setPerson(person1);

        MedicalRecord medicalRecord2 = new MedicalRecord();
        medicalRecord2.setMedications(List.of("TestMedication2, 200mg"));
        medicalRecord2.setAllergies(List.of("TestAllergy2"));
        medicalRecord2.setPerson(person2);

        MedicalRecord medicalRecord3 = new MedicalRecord();
        medicalRecord3.setMedications(List.of("TestMedication3, 300mg"));
        medicalRecord3.setAllergies(List.of("TestAllergy3"));
        medicalRecord3.setPerson(person3);

        person1.setMedicalRecord(medicalRecord1);
        person2.setMedicalRecord(medicalRecord2);
        person3.setMedicalRecord(medicalRecord3);

        personRepository.saveAll(List.of(person1, person2, person3));


    }

    @Test
    void testGetHouseholdsByStation() throws Exception {
        mockMvc.perform(get("/flood/stations?stations=1,2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stationNumber").value(1))
                .andExpect(jsonPath("$[0].address").value("TestAddress"))
                .andExpect(jsonPath("$[0].persons[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].persons[0].lastName").value("Smith"))
                .andExpect(jsonPath("$[0].persons[0].phone").value("555-5555"))
                .andExpect(jsonPath("$[0].persons[0].age").value(25))
                .andExpect(jsonPath("$[0].persons[0].medicalRecord.medications[0]").value("TestMedication1, 100mg"))
                .andExpect(jsonPath("$[0].persons[0].medicalRecord.allergies[0]").value("TestAllergy1"))
                .andExpect(jsonPath("$[0].persons[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[0].persons[1].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].persons[1].phone").value("555-5556"))
                .andExpect(jsonPath("$[0].persons[1].age").value(10))
                .andExpect(jsonPath("$[0].persons[1].medicalRecord.medications[0]").value("TestMedication2, 200mg"))
                .andExpect(jsonPath("$[0].persons[1].medicalRecord.allergies[0]").value("TestAllergy2"))
                .andExpect(jsonPath("$[1].stationNumber").value(2))
                .andExpect(jsonPath("$[1].address").value("OtherTestAddress"))
                .andExpect(jsonPath("$[1].persons[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].persons[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[1].persons[0].phone").value("555-5557"))
                .andExpect(jsonPath("$[1].persons[0].age").value(30))
                .andExpect(jsonPath("$[1].persons[0].medicalRecord.medications[0]").value("TestMedication3, 300mg"))
                .andExpect(jsonPath("$[1].persons[0].medicalRecord.allergies[0]").value("TestAllergy3"));
    }

}
