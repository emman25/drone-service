package com.task.drones.services;

import com.task.drones.dtos.DroneRequestDto;
import com.task.drones.dtos.DroneResponseDto;
import com.task.drones.dtos.MedicationRequestDto;
import com.task.drones.dtos.MedicationResponseDto;
import com.task.drones.models.Drone;
import com.task.drones.models.Medication;
import com.task.drones.repositories.DroneRepository;
import com.task.drones.repositories.MedicationRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneService {
    public Logger logger = LoggerFactory.getLogger(DroneService.class);

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public DroneResponseDto addDrone(HttpServletRequest request, DroneRequestDto droneRequestDto) throws Exception {
        try{
            Drone drone = new Drone();
            drone.setSerialNumber(droneRequestDto.getSerialNumber());
            drone.setModel(droneRequestDto.getModel());
            drone.setBatteryCapacity(droneRequestDto.getBatteryCapacity());
            drone.setState(droneRequestDto.getState());

            Drone savedDrone = droneRepository.save(drone);

            return modelMapper.map(savedDrone, DroneResponseDto.class);
        } catch (Exception e){
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public MedicationResponseDto createMedication(HttpServletRequest request, MedicationRequestDto medicationRequestDto) throws Exception {
        try{
            Medication medication = new Medication();
            medication.setCode(medicationRequestDto.getCode());
            medication.setName(medicationRequestDto.getName());
            medication.setWeight(medicationRequestDto.getWeight());
            medication.setImage(medicationRequestDto.getImage());

            Medication savedMedication = medicationRepository.save(medication);

            return modelMapper.map(savedMedication, MedicationResponseDto.class);
        } catch (Exception e){
            throw new Exception(e.getLocalizedMessage());
        }
    }
}
