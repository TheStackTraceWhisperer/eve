package com.example.engine.api;

/**
 * Public interface that all plugins must implement.
 * Provides a full lifecycle for plugins to manage their state.
 */
public interface Plugin {

  /**
   * Gets the name of this plugin.
   * @return the plugin name
   */
  String getName();

  /**
   * Called once when the plugin is being initialized.
   * This is the ideal place for one-time setup tasks like reading configuration.
   * The original initialize() method remains for backward compatibility and clarity.
   */
  void initialize();

  /**
   * Called to make the plugin fully operational after all plugins have been initialized.
   * Override this method to start background tasks, open network connections,
   * or begin active processing.
   * State transition: INITIALIZED -> STARTED
   */
  default void start() {
    // No-op by default
  }

  /**
   * Called to gracefully halt the plugin's active operations during a shutdown sequence.
   * Override this method to stop threads and close connections cleanly.
   * State transition: STARTED -> STOPPED
   */
  default void stop() {
    // No-op by default
  }

  /**
   * Called to release all resources before the plugin is discarded.
   * Override this method for final cleanup tasks to prevent resource leaks.
   * State transition: STOPPED -> DESTROYED
   */
  default void destroy() {
    // No-op by default
  }
}