package com.task.drones.repositories;

import com.task.drones.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelsRepository extends JpaRepository<Model, Long> {
}
