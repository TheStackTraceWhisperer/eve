package com.example.engine.api;

/**
 * Public interface that all plugins must implement.
 */
public interface Plugin {
    
    /**
     * Gets the name of this plugin.
     * @return the plugin name
     */
    String getName();
    
    /**
     * Called when the plugin is initialized.
     */
    void initialize();
}