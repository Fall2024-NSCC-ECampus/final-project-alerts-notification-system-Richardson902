package org.example.alertsnotificationsystem.service;

import org.example.alertsnotificationsystem.payload.ChildAlertResponse;

import java.util.List;

/**
 * Service for child alerts.
 * Contains methods for getting children by address.
 */
public interface ChildAlertService {
    List<ChildAlertResponse> getChildrenByAddress(String address, String city);
}
