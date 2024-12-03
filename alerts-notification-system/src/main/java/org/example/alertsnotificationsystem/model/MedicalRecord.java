package org.example.alertsnotificationsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents a medical record.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medical_records")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @JsonIgnore // Prevent infinite recursion lol wall of JSON
    private Person person;

    @ElementCollection
    @CollectionTable(name = "medications", joinColumns = @JoinColumn(name = "medical_record_id"))
    private List<String> medications;

    @ElementCollection
    @CollectionTable(name = "allergies", joinColumns = @JoinColumn(name = "medical_record_id"))
    private List<String> allergies;
}
