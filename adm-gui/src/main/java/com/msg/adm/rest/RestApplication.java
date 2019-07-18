package com.msg.adm.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Initializes the Rest Application.
 * Assigns the JAX RS resource classes
 *  to the configuration.
 * 
 * @author User
 *
 */
@ApplicationPath("rest")
public class RestApplication extends Application {

    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        // register root resource
//        classes.add(InitialContextResource.class);
        return classes;
    }
	
}
