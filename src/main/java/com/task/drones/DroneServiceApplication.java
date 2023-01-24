package com.task.drones;

import com.task.drones.repositories.DroneRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DroneServiceApplication implements CommandLineRunner {

    @Autowired
    DroneRepository droneRepository;

    Logger logger = LoggerFactory.getLogger(DroneServiceApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(DroneServiceApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Override
    public void run(String... params) throws Exception {
//        long numberOfModels = droneRepository.count()
    }

}
