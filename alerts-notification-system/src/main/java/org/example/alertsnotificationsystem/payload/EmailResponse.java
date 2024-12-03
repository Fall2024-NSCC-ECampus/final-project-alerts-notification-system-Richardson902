package org.example.alertsnotificationsystem.payload;

import lombok.Builder;
import lombok.Data;

/**
 * Represents the response for an email alert.
 * Contains information about the email address.
 */
@Data
@Builder
public class EmailResponse {
    private String email;
}
