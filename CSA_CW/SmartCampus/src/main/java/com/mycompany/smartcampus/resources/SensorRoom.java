/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.resources;

import com.mycompany.smartcampus.exception.DataNotFoundException;
import com.mycompany.smartcampus.exception.RoomNotEmptyException;
import com.mycompany.smartcampus.dao.GenericDAO;
import com.mycompany.smartcampus.dao.MockDatabase;
import com.mycompany.smartcampus.model.Room;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author DELL
 */
@Path("/rooms")
public class SensorRoom {
    
    private GenericDAO<Room> roomDAO = new GenericDAO<>(MockDatabase.ROOMS);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRooms() {
        List<Room> rooms = roomDAO.getAll();
        return Response.ok(rooms).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoom(Room room) {
        roomDAO.add(room);
        return Response.status(Response.Status.CREATED)
                .entity(room)        
                .build();
    }
    
    @GET
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoomById(@PathParam("roomId") String roomId) {
        Room room = roomDAO.getById(roomId);
        if (room != null){
            return Response.ok(room).build();
        } else {
            throw new DataNotFoundException("Room with ID " + roomId + " not found.");
        }
    }
    
    @DELETE
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRoom(@PathParam("roomId") String roomId) {
        Room existingRoom = roomDAO.getById(roomId);
        if (existingRoom == null) {
            throw new DataNotFoundException("Room with ID " + roomId + " not found.");
        }
        if (!existingRoom.getSensorIds().isEmpty()){
            throw new RoomNotEmptyException("Room is currently occupied by active hardware.");
        }
        roomDAO.delete(roomId);
        return Response.ok(roomDAO).build();
    }
    
}
