package com.example.plugin;

import com.example.engine.api.EngineStartedEvent;
import com.example.engine.api.Plugin;
import com.example.engine.api.Timed;
import io.avaje.inject.events.Observes;
import io.avaje.inject.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Example plugin implementation that demonstrates dependency injection,
 * event observation, and method interception.
 */
@Component
public class ExamplePlugin implements Plugin {
    
    private static final Logger log = LoggerFactory.getLogger(ExamplePlugin.class);
    
    @Override
    public String getName() {
        return "ExamplePlugin";
    }
    
    @Override
    @Timed
    public void initialize() {
        log.info("ExamplePlugin: Initializing plugin...");
        // Simulate some work
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("ExamplePlugin: Plugin initialization completed");
    }
    
    /**
     * Event observer method that listens to EngineStartedEvent.
     * 
     * @param event the engine started event
     */
    public void onEngineStart(@Observes EngineStartedEvent event) {
        log.info("ExamplePlugin: Received EngineStartedEvent at timestamp: {}", event.startTime());
        processEngineStart(event);
    }
    
    /**
     * Additional method with timing interceptor to demonstrate AOP.
     * 
     * @param event the engine started event
     */
    @Timed
    private void processEngineStart(EngineStartedEvent event) {
        log.info("ExamplePlugin: Processing engine start event...");
        // Simulate some processing work
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("ExamplePlugin: Finished processing engine start event");
    }
}