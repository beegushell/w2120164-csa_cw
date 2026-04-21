/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.model;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Room implements BaseModel{
    private String id; //unique identifier (ex: "LIB-301")
    private String name; //human readable name (ex: "Library Quiet Study")
    private int capacity; //maximum occupancy for safety regulations
    private List<String> sensorIds = new ArrayList<>(); //Collection of IDs of sensors deploted in this room

    public Room() {}

    public Room(String id, String name, int capacity, List<String> sensorIds) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.sensorIds = sensorIds;
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public List<String> getSensorIds() { return sensorIds; }
    public void setSensorIds(List<String> sensorIds) { this.sensorIds = sensorIds; }
    
}
