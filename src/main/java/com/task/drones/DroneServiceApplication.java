package com.task.drones;

import com.task.drones.models.Model;
import com.task.drones.models.State;
import com.task.drones.repositories.DroneRepository;
import com.task.drones.repositories.ModelsRepository;
import com.task.drones.repositories.StatesRepository;
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

    @Autowired
    StatesRepository statesRepository;

    @Autowired
    ModelsRepository modelsRepository;

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
        long numberOfModels = modelsRepository.count();
        long numberOfStates = statesRepository.count();

        if (numberOfModels == 0) {
            Model lightweight = new Model();
            lightweight.setName("Lightweight");
            modelsRepository.save(lightweight);

            logger.info("Lightweight model created");

            Model middleweight = new Model();
            middleweight.setName("Middleweight");
            modelsRepository.save(middleweight);

            logger.info("Middleweight model created");

            Model cruiserweight = new Model();
            cruiserweight.setName("Cruiserweight");
            modelsRepository.save(cruiserweight);

            logger.info("Cruiserweight model created");

            Model heavyweight = new Model();
            heavyweight.setName("Heavyweight");
            modelsRepository.save(heavyweight);

            logger.info("Heavyweight model created");
        }

        if (numberOfStates == 0){
            State idle = new State();
            idle.setName("IDLE");
            statesRepository.save(idle);

            logger.info("IDLE state created");

            State loading = new State();
            loading.setName("LOADING");
            statesRepository.save(loading);

            logger.info("LOADING state created");

            State loaded = new State();
            loaded.setName("LOADED");
            statesRepository.save(loaded);

            logger.info("LOADED state created");

            State delivering = new State();
            delivering.setName("DELIVERING");
            statesRepository.save(delivering);

            logger.info("DELIVERING state created");

            State delivered = new State();
            delivered.setName("DELIVERED");
            statesRepository.save(delivered);

            logger.info("DELIVERED state created");

            State returning = new State();
            returning.setName("RETURNING");
            statesRepository.save(returning);

            logger.info("RETURNING state created");
        }

        logger.info("Running DroneServiceApplication...");
    }

}
