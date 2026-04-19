/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.resources;

import com.mycompany.smartcampus.exception.DataNotFoundException;
import com.mycompany.smartcampus.dao.GenericDAO;
import com.mycompany.smartcampus.dao.MockDatabase;
import com.mycompany.smartcampus.model.Sensor;
import com.mycompany.smartcampus.model.Room;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author DELL
 */
@Path("/sensors")
public class SensorResource {
    
    private GenericDAO<Sensor> sensorDAO = new GenericDAO<>(MockDatabase.SENSORS);
    private GenericDAO<Room> roomDAO = new GenericDAO<>(MockDatabase.ROOMS);
    
    @GET
    public List<Sensor> getSensors(@QueryParam("type") String type) {
        
        List<Sensor> allSensors = sensorDAO.getAll();
        
        if (type == null){
            return allSensors;
        }
        List<Sensor> filtered = new ArrayList<>();
        
        for (Sensor s: allSensors){
            if (type.equalsIgnoreCase(s.getType())){
                filtered.add(s);
            }
        }
        
        return filtered;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSensor(Sensor sensor) {
        
        Room room = roomDAO. getById(sensor.getRoomId());
        
        if (room == null){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Room does not exist" + sensor.getRoomId())
                    .build();
        }
        
        sensorDAO.add(sensor);
        
        return Response
                .status(Response.Status.CREATED)
                .entity(sensor)
                .build();
        
        
    }
}
