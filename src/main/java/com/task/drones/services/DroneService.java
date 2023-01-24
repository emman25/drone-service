package com.task.drones.services;

import com.task.drones.dtos.*;
import com.task.drones.models.Drone;
import com.task.drones.models.Medication;
import com.task.drones.models.Model;
import com.task.drones.models.State;
import com.task.drones.repositories.DroneRepository;
import com.task.drones.repositories.MedicationRepository;
import com.task.drones.repositories.ModelRepository;
import com.task.drones.repositories.StateRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DroneService {
    public Logger logger = LoggerFactory.getLogger(DroneService.class);

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ModelMapper modelMapper;

    public DroneResponseDto addDrone(HttpServletRequest request, DroneRequestDto droneRequestDto) throws Exception {
        try{
            Drone drone = new Drone();
            drone.setSerialNumber(droneRequestDto.getSerialNumber());

            Model model = modelRepository.findByName(droneRequestDto.getModel().substring(0, 1).toUpperCase() + droneRequestDto.getModel().substring(1).toLowerCase());
            if (model == null){
                throw new Exception("Model not found");
            }

            drone.setModel(model);
            drone.setBatteryCapacity(droneRequestDto.getBatteryCapacity());

            if(droneRequestDto.getWeightLimit() > 500){
                throw new Exception("Weight limit cannot be more than 500");
            }

            drone.setWeightLimit(droneRequestDto.getWeightLimit());

            State state = stateRepository.findByName(droneRequestDto.getState().toUpperCase());
            if (state == null){
                throw new Exception("State not found");
            }
            drone.setState(state);

            Drone savedDrone = droneRepository.save(drone);

            logger.info("Drone registration request completed");

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

    public GenericDataResponseEntity load(HttpServletRequest request, LoadDroneRequestDto loadDroneRequestDto) throws Exception {
        try{
            Medication medication = medicationRepository.findByCode(loadDroneRequestDto.getMedicationCode());
            Drone drone = droneRepository.findBySerialNumber(loadDroneRequestDto.getSerialNumber());

            List<Medication> medications = medicationRepository.findAllByDrone(loadDroneRequestDto.getSerialNumber());
            //count weight of all medications
            double totalWeight = medications.stream().mapToDouble(Medication::getWeight).sum();

            if (totalWeight + medication.getWeight() > drone.getBatteryCapacity()){
                return new GenericDataResponseEntity("Drone is full");
            }

            medication.setDrone(drone);

            medications = medicationRepository.findAllByDrone(loadDroneRequestDto.getSerialNumber());

            List<LoadDroneResponseDto> listOfMedications = medications
                    .stream()
                    .map(medication1 -> modelMapper.map(medication1, LoadDroneResponseDto.class)).toList();
//            return modelMapper.map(savedMedication, LoadDroneResponseDto.class);

            return new GenericDataResponseEntity(listOfMedications);
        } catch (Exception e){
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public GenericDataResponseEntity batteryLevel(HttpServletRequest request, String serial) throws Exception {
        try{
            Drone drone = droneRepository.findBySerialNumber(serial);

            if (drone == null){
                return new GenericDataResponseEntity("Drone not found");
            }

            return new GenericDataResponseEntity(drone.getBatteryCapacity());
        } catch (Exception e){
            throw new Exception(e.getLocalizedMessage());
        }
    }
}
