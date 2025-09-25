package com.example.engine.core;

import com.example.engine.api.events.EngineStartedEvent;
import com.example.engine.api.Plugin;
import io.avaje.inject.events.Event;
import io.avaje.inject.Component;
import io.avaje.inject.BeanScope;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * Main engine class that manages the full lifecycle of plugins.
 */
@Slf4j
@Component
public class Engine {

  private final Event<EngineStartedEvent> engineStartedEvent;
  private final BeanScope beanScope;
  private List<Plugin> runtimePlugins;

  /**
   * Constructor with dependency injection.
   *
   * @param engineStartedEvent event publisher for engine started events
   * @param beanScope the bean scope for runtime plugin discovery
   */
  public Engine(Event<EngineStartedEvent> engineStartedEvent, BeanScope beanScope) {
    this.engineStartedEvent = engineStartedEvent;
    this.beanScope = beanScope;
  }

  /**
   * Starts the engine, initializes and starts plugins, and fires the engine started event.
   */
  public void start() {
    this.runtimePlugins = beanScope.list(Plugin.class);
    long startTime = System.currentTimeMillis();

    List<CompletableFuture<Void>> pluginFutures = runtimePlugins.stream()
      .map(plugin -> CompletableFuture.runAsync(
        plugin::initialize,
        Executors.newVirtualThreadPerTaskExecutor()
      ))
      .toList();
    CompletableFuture.allOf(pluginFutures.toArray(new CompletableFuture[0])).join();

    runtimePlugins.forEach(Plugin::start);

    engineStartedEvent.fire(new EngineStartedEvent(startTime));

    log.info("started successfully");
  }

  /**
   * Stops the engine, gracefully stopping and destroying all plugins.
   */
  public void stop() {
    if (runtimePlugins == null) {
      return;
    }

    List<Plugin> reversedPlugins = new java.util.ArrayList<>(runtimePlugins);
    Collections.reverse(reversedPlugins);

    reversedPlugins.forEach(plugin -> {
      try {
        plugin.stop();
      } catch (Exception e) {
        // Silently ignore exceptions during stop
      }
    });

    reversedPlugins.forEach(plugin -> {
      try {
        plugin.destroy();
      } catch (Exception e) {
        // Silently ignore exceptions during destroy
      }
    });
  }
}