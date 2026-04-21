/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.resources;

import com.mycompany.smartcampus.exception.*;
import com.mycompany.smartcampus.dao.GenericDAO;
import com.mycompany.smartcampus.dao.MockDatabase;
import com.mycompany.smartcampus.model.*;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author DELL
 */
public class SensorReadingResource {
    
    private String sensorId;
    private Sensor parentSensor;
    
    private GenericDAO<SensorReading> readingDAO = new GenericDAO<>(MockDatabase.READINGS);
    
    public SensorReadingResource() {}
    
    public SensorReadingResource(String sensorId, Sensor parentSensor){
        this.sensorId = sensorId;
        this.parentSensor = parentSensor;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReading> getReadings() {
        List<SensorReading> allReadings = readingDAO.getAll();
        List<SensorReading> filtered = new ArrayList<>();
        for (SensorReading r : allReadings) {
            if (sensorId.equals(r.getSensorId())){
                filtered.add(r);
            }
        }
        return filtered;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReading(SensorReading reading) {
        
        if (parentSensor.getStatus().equalsIgnoreCase("MAINTENANCE")){
            throw new SensorUnavailableException("Sensor " + sensorId + " is under MAINTENANCE");
        }
        
        reading.setSensorId(sensorId);
        readingDAO.add(reading);
        
        parentSensor.setCurrentValue(reading.getValue());
        
        return Response
                .status(Response.Status.CREATED)
                .entity(reading)
                .build();
    }
    
}
