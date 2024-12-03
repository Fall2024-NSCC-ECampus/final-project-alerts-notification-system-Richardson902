package org.example.alertsnotificationsystem.payload;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Represents the response for a fire station.
 * Contains information about the persons involved.
 */
@Data
@Builder
public class FirestationResponse {
    private List<PersonResponse> persons;
    private long numberOfAdults;
    private long numberOfChildren;
}
