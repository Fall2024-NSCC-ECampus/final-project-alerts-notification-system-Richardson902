package org.example.alertsnotificationsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a person in the system.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String city;
    private String postalCode;
    private String phone;
    private String email;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private MedicalRecord medicalRecord;
}
