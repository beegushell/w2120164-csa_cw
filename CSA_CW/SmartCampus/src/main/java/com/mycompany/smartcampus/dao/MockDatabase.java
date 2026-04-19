/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.dao;

import com.mycompany.smartcampus.model.Room;
import com.mycompany.smartcampus.model.Sensor;
import com.mycompany.smartcampus.model.SensorReading;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class MockDatabase {
    public static final List<Room> ROOMS = new ArrayList<>();
    public static final List<Sensor> SENSORS = new ArrayList<>();
    public static final List<SensorReading> READINGS = new ArrayList<>();
    
    static {
        // Initialise Teachers
        ROOMS.add(new Room("LIB-301", "Library Quiet Study", 30));
        ROOMS.add(new Room("CAF-607", "Cafeteria", 120));

        // Initialise Students
        SENSORS.add(new Sensor("TEMP-001", "Temperature", "ACTIVE", 30.2, "LIB-301"));
        SENSORS.add(new Sensor("OCP-001", "Occupancy", "MAINTENANCE", 95.0, "CAF-607"));
        SENSORS.add(new Sensor("CO2-001", "Temperature", "OFFLINE", 398.7, "LIB-301"));

        // Initialise Modules
        READINGS.add(new SensorReading("18ad3220-8cad-45fd-a9a8-9fb35b118d87", 1776570284, 30.2));
        READINGS.add(new SensorReading("19a787e8-49cb-4be8-878c-202f5eb05a6e", 1776570456, 398.7));
    }
    
}