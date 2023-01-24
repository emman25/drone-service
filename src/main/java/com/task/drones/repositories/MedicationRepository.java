package com.task.drones.repositories;

import com.task.drones.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, String> {
}
