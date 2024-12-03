package org.example.alertsnotificationsystem.payload;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Represents the response for a fire alert.
 * Contains information about the fire station number and persons involved.
 */
@Data
@Builder
public class FireResponse {
    private int stationNumber;
    private List<PersonMedicalResponse> persons;
}
