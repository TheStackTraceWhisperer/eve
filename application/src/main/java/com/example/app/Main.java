package com.example.app;

import com.example.engine.core.Engine;
import io.avaje.inject.BeanScope;

/**
 * Main entry point for the application using Java 21's instance main method.
 */
public class Main {
    
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
        System.out.println("Starting EVE Application...");
        
        // Create the bean scope
        var beanScope = BeanScope.builder().build();

        // Get the engine instance from the scope
        Engine engine = beanScope.get(Engine.class);

        // Call the main business logic
        engine.start();
        
        System.out.println("EVE Application completed successfully!");
    }
}