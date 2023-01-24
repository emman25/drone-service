package com.task.drones.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "drones")
public class Drone {

    @Id
    @Column(name = "serial_number")
    private String serialNumber;

    @OneToOne(fetch = FetchType.EAGER)
    private Model model;

    @Column(name = "weight_limit")
    private String weightLimit;

    @Column(name = "battery_capacity")
    private Double batteryCapacity;

    @OneToOne(fetch = FetchType.EAGER)
    private State state;

}