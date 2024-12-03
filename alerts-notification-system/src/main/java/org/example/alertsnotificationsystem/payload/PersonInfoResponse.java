package org.example.alertsnotificationsystem.payload;

import lombok.Builder;
import lombok.Data;
import org.example.alertsnotificationsystem.model.MedicalRecord;

/**
 * Represents the response for a person information.
 * Contains information about the person's first name, last name, address, city, postal code, phone number, email and medical record.
 */
@Data
@Builder
public class PersonInfoResponse {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String postalCode;
    private String phoneNumber;
    private String email;
    private MedicalRecord medicalRecord;
}
