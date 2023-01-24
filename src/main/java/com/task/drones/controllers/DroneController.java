package com.task.drones.controllers;

import com.task.drones.dtos.DroneRequestDto;
import com.task.drones.dtos.DroneResponseDto;
import com.task.drones.services.DroneService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drones")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping
    public DroneResponseDto registerDrone(HttpServletRequest request, @RequestBody DroneRequestDto droneRequestDto) throws Exception {
        return droneService.addDrone(request, droneRequestDto);
    }

}
