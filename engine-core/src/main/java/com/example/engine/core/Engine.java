package com.example.engine.core;

import com.example.engine.api.EngineStartedEvent;
import com.example.engine.api.Plugin;
import io.avaje.inject.events.Event;
import io.avaje.inject.Component;
import io.avaje.inject.BeanScope;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * Main engine class that manages plugins and fires events.
 */
@Slf4j
@Component
public class Engine {
    
    private final List<Plugin> plugins;
    private final Event<EngineStartedEvent> engineStartedEvent;
    private final BeanScope beanScope;
    
    /**
     * Constructor with dependency injection.
     * 
     * @param plugins list of all available plugins (may be empty if injected before plugin modules are processed)
     * @param engineStartedEvent event publisher for engine started events
     * @param beanScope the bean scope for runtime plugin discovery
     */
    public Engine(List<Plugin> plugins, Event<EngineStartedEvent> engineStartedEvent, BeanScope beanScope) {
        this.plugins = plugins;
        this.engineStartedEvent = engineStartedEvent;
        this.beanScope = beanScope;
        
        log.info("Engine initialized with {} plugins in constructor", plugins.size());
    }
    
    /**
     * Starts the engine, initializes plugins, and fires the engine started event.
     * Uses virtual threads for plugin initialization to showcase modern concurrency.
     */
    public void start() {
        // Get plugins at runtime to ensure all modules are loaded
        List<Plugin> runtimePlugins = beanScope.list(Plugin.class);
        log.info("Starting engine with {} plugins (runtime discovery)", runtimePlugins.size());
        
        long startTime = System.currentTimeMillis();
        
        // Initialize plugins using virtual threads for modern concurrency
        List<CompletableFuture<Void>> pluginFutures = runtimePlugins.stream()
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