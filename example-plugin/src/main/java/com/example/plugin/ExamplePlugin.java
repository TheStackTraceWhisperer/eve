package com.example.plugin;

import com.example.engine.api.events.EngineStartedEvent;
import com.example.engine.api.Plugin;
import com.example.engine.api.Timed;
import io.avaje.inject.Component;
import io.avaje.inject.events.Observes;
import lombok.extern.slf4j.Slf4j;

/**
 * Example plugin implementation that demonstrates dependency injection,
 * event observation, and method interception.
 */
@Slf4j
@Component
public class ExamplePlugin implements Plugin {

  @Override
  public String getName() {
    return "ExamplePlugin";
  }

  @Override
  @Timed
  public void initialize() {
    //log.info("Initializing plugin...");
    // Simulate some work
    //log.info("Plugin initialization completed");
  }

  /**
   * Event observer method that listens to EngineStartedEvent.
   *
   * @param event the engine started event
   */
  public void onEngineStart(@Observes EngineStartedEvent event) {
    //log.info("Received EngineStartedEvent at timestamp: {}", event.startTime());
    processEngineStart(event);
  }

  /**
   * Additional method with timing interceptor to demonstrate AOP.
   *
   * @param event the engine started event
   */
  @Timed
  public void processEngineStart(EngineStartedEvent event) {
    //log.info("Processing engine start event...");
    // Simulate some processing work
    //log.info("Finished processing engine start event");
  }
}