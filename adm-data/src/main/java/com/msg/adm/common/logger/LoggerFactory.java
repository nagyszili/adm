package com.msg.adm.common.logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;

/**
 * 
 * Provides the Producer for org.slf4j.Logger.
 *          The Logger can be injected this way.
 * 
 */
public class LoggerFactory {
    
    /**
     * Creates a Logger. Will be used in Classes which can inject a Logger.
     *        
     * @param injectionPoint
     * 
     * @return Logger
     * 
     */
    @SuppressWarnings("static-method")
    @Produces
    public Logger getLogger(InjectionPoint injectionPoint) {
        return getLogger(injectionPoint.getMember().getDeclaringClass());
    }

    /**
     * Creates a Logger. Will be used in Classes which can not inject a Logger.
     *        
     * @param clazz
     * 
     * @return Logger
     * 
     */
    public static Logger getLogger(Class<?> clazz) {
        String name = clazz.getCanonicalName();
        return org.slf4j.LoggerFactory.getLogger(name);
    }
}
