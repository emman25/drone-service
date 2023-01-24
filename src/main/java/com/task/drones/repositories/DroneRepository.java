package com.task.drones.repositories;

import com.task.drones.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, String> {
    Drone findBySerialNumber(String serialNumber);
}
