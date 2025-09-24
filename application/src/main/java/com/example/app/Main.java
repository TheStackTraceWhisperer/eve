package com.example.app;

import com.example.engine.core.Engine;
import io.avaje.inject.BeanScope;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main entry point for the application using Java 21's instance main method.
 */
public class Main {
    
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    
    /**
     * Traditional main method for compatibility with Maven exec plugin.
     */
    public static void main(String[] args) {
        new Main().main();
    }
    
    /**
     * Instance main method - a Java 21 feature for simpler entry points.
     */
    void main() {
        // Install JUL-to-SLF4J bridge to route java.util.logging calls through SLF4J
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        log.info("Starting EVE Application...");
        
        // Create the bean scope
        var beanScope = BeanScope.builder().build();

        // Get the engine instance from the scope
        Engine engine = beanScope.get(Engine.class);

        // Call the main business logic
        engine.start();
        
        log.info("EVE Application completed successfully!");
    }
}