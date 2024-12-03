package org.example.alertsnotificationsystem.payload;

import lombok.Builder;
import lombok.Data;

/**
 * Represents the response for a person.
 * Contains information about the person's first name, last name, address, and phone number.
 */
@Data
@Builder
public class PersonResponse {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
}
