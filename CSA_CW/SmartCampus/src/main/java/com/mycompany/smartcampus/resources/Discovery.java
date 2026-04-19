/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author DELL
 */
@Path("/")
public class Discovery {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getApiMetadata(){
    
        Map<String, Object> response = new HashMap<>();
        
        response.put("version", "v1");
        response.put("contact_info", "Smart Campus");
        
        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");
        resources.put("readings", "/api/v1/readings");
        
        response.put("resources", resources);
        
        return response;    
    }
}
