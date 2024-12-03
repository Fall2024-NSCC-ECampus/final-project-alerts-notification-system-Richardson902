package org.example.alertsnotificationsystem.repository;


import org.example.alertsnotificationsystem.model.Firestation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Firestation entity.
 */
public interface FirestationRepository extends JpaRepository<Firestation, Long> {
    Optional<Firestation> findByStationNumber(int stationNumber);
    Optional<Firestation> findByCity(String city);
    List<Firestation> findByStationNumberIn(List<Integer> stationNumbers);
}
