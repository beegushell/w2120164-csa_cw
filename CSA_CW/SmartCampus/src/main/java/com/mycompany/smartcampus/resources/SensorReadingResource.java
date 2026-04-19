/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.resources;

import com.mycompany.smartcampus.exception.DataNotFoundException;
import com.mycompany.smartcampus.dao.GenericDAO;
import com.mycompany.smartcampus.dao.MockDatabase;
import com.mycompany.smartcampus.model.SensorReading;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author DELL
 */
@Path("")
public class SensorReadingResource {
    
    private String sensorId;
    
    private GenericDAO<SensorReading> readingDAO = new GenericDAO<>(MockDatabase.READINGS);
    
    public SensorReadingResource(String sensorId){
        this.sensorId = sensorId;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReading> getReadings() {
        return readingDAO.getAll();
    }
    
    @POST
    public Response addReading(SensorReading reading) {
        
        readingDAO.add(reading);
        
        return Response
                .status(Response.Status.CREATED)
                .entity(reading)
                .build();
    }
    
}
