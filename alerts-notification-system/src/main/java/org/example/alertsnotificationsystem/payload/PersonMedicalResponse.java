package org.example.alertsnotificationsystem.payload;

import lombok.Builder;
import lombok.Data;
import org.example.alertsnotificationsystem.model.MedicalRecord;

/**
 * Represents the response for a person's medical information.
 * Contains information about the person's first name, last name, age, phone number and medical record.
 */
@Data
@Builder
public class PersonMedicalResponse {
    private String firstName;
    private String lastName;
    private int age;
    private String phone;
    private MedicalRecord medicalRecord;

}
