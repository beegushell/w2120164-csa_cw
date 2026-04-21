package com.mycompany.smartcampus.resources;

import com.mycompany.smartcampus.exception.*;
import com.mycompany.smartcampus.filter.LoggingFilter;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api/v1")
public class MyApplication extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        
        //resources
        classes.add(Discovery.class);
        classes.add(SensorResource.class);
        classes.add(SensorRoom.class);
        
        //mappers
        classes.add(DataNotFoundExceptionMapper.class);
        classes.add(LinkedResourceNotFoundExceptionMapper.class);
        classes.add(RoomNotEmptyExceptionMapper.class);
        classes.add(SensorUnavailableExceptionMapper.class);
        classes.add(GlobalExceptionMapper.class);
        
        classes.add(LoggingFilter.class);
        
        return classes;
    }
}
