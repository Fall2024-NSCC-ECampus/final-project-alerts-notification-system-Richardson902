package org.example.alertsnotificationsystem.payload;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Represents the response for a household alert.
 * Contains information about the fire station number, address and persons involved.
 */
@Data
@Builder
public class HouseholdResponse {
    private int stationNumber;
    private String address;
    private List<PersonMedicalResponse> persons;
}
