package com.mycompany.smartcampus;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Configures JAX-RS for the application.
 * @author Juneau
 */
@ApplicationPath("/api/v1")
public class JAXRSConfiguration extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ExampleClass.class);
        return classes;
    }
}
