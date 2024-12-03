package org.example.alertsnotificationsystem.repository;

import org.example.alertsnotificationsystem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Person entity.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByCity(String city);
    List<Person> findByAddressAndCity(String address, String city);
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);

}
