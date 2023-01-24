package com.task.drones.repositories;

import com.task.drones.models.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatesRepository extends JpaRepository<State, Long> {
}
