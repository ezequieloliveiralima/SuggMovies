package com.br.suggmovies.ws;

import java.util.Set;
import javax.ws.rs.core.Application;


/**
 *
 * @author ezequieloliveira
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        
        resources.add(com.br.suggmovies.ws.Service.class);
        
        return resources;
    }
    
}
