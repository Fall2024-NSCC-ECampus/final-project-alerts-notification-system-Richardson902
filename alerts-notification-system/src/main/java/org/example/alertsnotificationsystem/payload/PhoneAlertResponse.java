package org.example.alertsnotificationsystem.payload;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Represents the response for a phone alert.
 * Contains information about the phone numbers.
 */
@Data
@Builder
public class PhoneAlertResponse {
    private List<String> phoneNumbers;
}
