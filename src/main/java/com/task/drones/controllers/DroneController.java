package com.task.drones.controllers;

import com.task.drones.dtos.DroneRequestDto;
import com.task.drones.dtos.DroneResponseDto;
import com.task.drones.dtos.GenericDataResponseEntity;
import com.task.drones.dtos.LoadDroneRequestDto;
import com.task.drones.services.DroneService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drones")
public class DroneController {
    private static Logger logger = LoggerFactory.getLogger(DroneController.class);

    @Autowired
    private DroneService droneService;

    @PostMapping("/register")
    public DroneResponseDto registerDrone(HttpServletRequest request, @RequestBody DroneRequestDto droneRequestDto) throws Exception {
        logger.info("Drone registration request received");
        return droneService.addDrone(request, droneRequestDto);
    }

    @PostMapping("/load")
    public GenericDataResponseEntity loadDrone(HttpServletRequest request, @RequestBody LoadDroneRequestDto loadDroneRequestDto) throws Exception {
        return droneService.load(request, loadDroneRequestDto);
    }

    @GetMapping("/check-load")
    public GenericDataResponseEntity checkLoadDrones(HttpServletRequest request, @RequestParam("serial") String serial) throws Exception {
        return droneService.availableLoadMedication(request, serial);
    }

    @GetMapping("/check-available")
    public GenericDataResponseEntity checkAvailableDrones(HttpServletRequest request) throws Exception {
        return droneService.availableDrones(request);
    }

    @GetMapping("/battery")
    public GenericDataResponseEntity checkBatteryDrones(HttpServletRequest request, @RequestParam("serial") String serial) throws Exception {
        return droneService.batteryLevel(request, serial);
    }
}
