package org.example.alertsnotificationsystem.payload;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Represents the response for a child alert.
 * Contains information about a child and their family members.
 */
@Data
@Builder
public class ChildAlertResponse {
    private String firstName;
    private String lastName;
    private int age;
    private List<String> familyMembers;
}
