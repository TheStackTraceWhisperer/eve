package com.example.engine.core;

import com.example.engine.api.EngineStartedEvent;
import com.example.engine.api.Plugin;
import io.avaje.inject.events.Event;
import io.avaje.inject.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * Main engine class that manages plugins and fires events.
 */
@Component
public class Engine {
    
    private static final Logger log = LoggerFactory.getLogger(Engine.class);
    
    private final List<Plugin> plugins;
    private final Event<EngineStartedEvent> engineStartedEvent;
    
    /**
     * Constructor with dependency injection.
     * 
     * @param plugins list of all available plugins
     * @param engineStartedEvent event publisher for engine started events
     */
    public Engine(List<Plugin> plugins, Event<EngineStartedEvent> engineStartedEvent) {
        this.plugins = plugins;
        this.engineStartedEvent = engineStartedEvent;
    }
    
    /**
     * Starts the engine, initializes plugins, and fires the engine started event.
     * Uses virtual threads for plugin initialization to showcase modern concurrency.
     */
    public void start() {
        log.info("Starting engine with {} plugins", plugins.size());
        
        long startTime = System.currentTimeMillis();
        
        // Initialize plugins using virtual threads for modern concurrency
        List<CompletableFuture<Void>> pluginFutures = plugins.stream()
            .map(plugin -> CompletableFuture.runAsync(() -> {
                log.info("Initializing plugin: {}", plugin.getName());
                plugin.initialize();
                log.info("Plugin initialized: {}", plugin.getName());
            }, Executors.newVirtualThreadPerTaskExecutor()))
            .toList();
        
        // Wait for all plugins to initialize
        CompletableFuture.allOf(pluginFutures.toArray(new CompletableFuture[0])).join();
        
        // Fire the engine started event
        EngineStartedEvent event = new EngineStartedEvent(startTime);
        log.info("Firing EngineStartedEvent");
        engineStartedEvent.fire(event);
        
        log.info("Engine started successfully");
    }
}