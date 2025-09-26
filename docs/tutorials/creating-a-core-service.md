# Tutorial: Creating a Core Service

This tutorial explains how to add a new "Core Service" to the Eve framework. Unlike a Plugin, which is an optional, soft-dependency, a Core Service is an integral part of the engine's functionality (a hard-dependency).

We will create a simple `ClockService` that provides the current time. This demonstrates how to create a public API and a core implementation.

## 1. Define the Public API

A Core Service must have a clean public interface. All interfaces for core services belong in the `engine-api` module.

1.  **Create the interface file** inside the `engine-api` module:

    **`engine-api/src/main/java/eve/engine/api/ClockService.java`**
    ```java
    package eve.engine.api;

    import java.time.Instant;

    /**
     * A service that provides access to the current time.
     */
    public interface ClockService {

        /**
         * Gets the current time as an Instant.
         * @return the current Instant
         */
        Instant now();
    }
    ```

This interface defines the public contract. Any component in the system can depend on this interface without knowing about its implementation.

## 2. Create the Core Implementation

The implementation of the service belongs in the `engine-core` module.

1.  **Create the implementation file** inside the `engine-core` module:

    **`engine-core/src/main/java/eve/engine/core/DefaultClockService.java`**
    ```java
    package eve.engine.core;

    import eve.engine.api.ClockService;
    import io.avaje.inject.Component;

    import java.time.Instant;

    /**
     * Default implementation of the ClockService.
     */
    @Component
    public class DefaultClockService implements ClockService {

        @Override
        public Instant now() {
            return Instant.now();
        }
    }
    ```

2.  **Make it a Component**: By annotating the class with `@Component`, Avaje Inject will automatically discover it, instantiate it as a singleton, and make it available for injection wherever `ClockService` is requested.

## 3. Using the New Service

Your new service is now a part of the engine. Any other component (including other core services or any plugin) can use it by simply injecting it.

For example, let's modify the `ExamplePlugin` to use the `ClockService`.

**`example-plugin/src/main/java/eve/plugin/ExamplePlugin.java`**
```java
package eve.plugin;

import eve.engine.api.ClockService; // Import the new service
import eve.engine.api.Plugin;
import eve.engine.api.events.EngineStartedEvent;
import io.avaje.inject.Component;
import io.avaje.inject.events.Observes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ExamplePlugin implements Plugin {

    private static final Logger log = LoggerFactory.getLogger(ExamplePlugin.class);
    private final ClockService clockService;

    // Inject the service via the constructor
    public ExamplePlugin(ClockService clockService) {
        this.clockService = clockService;
    }

    @Override
    public String getName() {
        return "ExamplePlugin";
    }

    public void onEngineStart(@Observes EngineStartedEvent event) {
        // Use the service
        log.info("Engine started event observed at: {}", clockService.now());
    }

    // ... other methods
}
```

## Summary: Service vs. Plugin

This tutorial highlights the key difference in our architecture:

*   **Plugins** are self-contained extensions that are discovered at runtime. They are ideal for optional or third-party features.
*   **Core Services** are fundamental capabilities of the engine. They follow a pattern of defining a contract in `engine-api` and an implementation in `engine-core`, making them available to the entire application.

By following this pattern, you can systematically grow the core capabilities of the framework while maintaining a clean and decoupled architecture.
