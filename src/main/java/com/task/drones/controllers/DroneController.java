package com.task.drones.controllers;

import com.task.drones.dtos.DroneRequestDto;
import com.task.drones.dtos.DroneResponseDto;
import com.task.drones.services.DroneService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drones")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping("/register")
    public DroneResponseDto registerDrone(HttpServletRequest request, @RequestBody DroneRequestDto droneRequestDto) throws Exception {
        return droneService.addDrone(request, droneRequestDto);
    }

    @PostMapping("/load")
    public DroneResponseDto loadDrone(HttpServletRequest request, @RequestBody DroneRequestDto droneRequestDto) throws Exception {
        return droneService.addDrone(request, droneRequestDto);
    }

    @PostMapping("/check-medication")
    public DroneResponseDto checkDrone(HttpServletRequest request, @RequestBody DroneRequestDto droneRequestDto) throws Exception {
        return droneService.addDrone(request, droneRequestDto);
    }

    @GetMapping("/check-available")
    public DroneResponseDto checkAvailableDrones(HttpServletRequest request, @RequestBody DroneRequestDto droneRequestDto) throws Exception {
        return droneService.addDrone(request, droneRequestDto);
    }

    @PostMapping("/check-battery")
    public DroneResponseDto checkBatteryDrones(HttpServletRequest request, @RequestBody DroneRequestDto droneRequestDto) throws Exception {
        return droneService.addDrone(request, droneRequestDto);
    }
}
