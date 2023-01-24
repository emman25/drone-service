package com.task.drones.dtos;

import com.task.drones.models.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneResponseDto {
    private String serialNumber;
    private Model model;
}
