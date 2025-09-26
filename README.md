# Eve Multi-Module Project

This project is a demonstration of a multi-module Maven project using Avaje Inject, a plugin architecture, and Java 21 features.

## Project Structure

```
eve/
├── pom.xml                    # Root POM with BOM and module management
├── engine-api/                # Public contracts and interfaces (Plugin, @Timed, events)
├── engine-core/               # Core implementation (Engine, interceptors)
├── plugin-parent/             # Parent POM for all plugins
├── example-plugin/            # Example plugin implementation
├── engine-dist/               # Distribution aggregator
└── application/               # Final executable entry point
```

## Key Concepts

*   **Dependency Injection**: Uses Avaje Inject with `@Component` for bean registration. The core `Engine` receives a `List<Plugin>` via constructor injection.
*   **Plugin Architecture**: A `plugin-parent` POM simplifies creation of new plugins. Plugins are discovered at runtime and initialized on virtual threads.
*   **Event System**: Loosely coupled communication using `@Observes` on event parameters (e.g., `onEngineStart(@Observes EngineStartedEvent event)`).
*   **AOP**: Method interception is implemented via an `@Aspect` annotation (`@Timed`) and a corresponding `MethodInterceptor` (`TimingInterceptor`).
*   **Build**: A multi-module Maven build with a Bill of Materials (BOM) in the root `pom.xml` for centralized dependency version management.

## Building and Running

### Prerequisites

*   Java 21+
*   Maven 3.6+

### Build

```bash
mvn clean install
```

### Run

```bash
cd application
mvn dependency:build-classpath -Dmdep.outputFile=classpath.txt
java -cp "target/classes:$(cat classpath.txt)" eve.app.Main
```

## Expected Output

```
Starting Eve Application...
[main] INFO eve.engine.core.Engine - Engine initialized with 1 plugins.
[main] INFO eve.engine.core.Engine - Starting engine...
[#VThread-1] INFO eve.engine.core.Engine - Initializing plugin: ExamplePlugin
[#VThread-1] INFO eve.plugin.ExamplePlugin - Initializing plugin...
[#VThread-1] INFO eve.plugin.ExamplePlugin - Plugin initialization completed
[#VThread-1] INFO eve.engine.core.Engine - Plugin initialized: ExamplePlugin
[main] INFO eve.engine.core.Engine - Firing EngineStartedEvent
[main] INFO eve.plugin.ExamplePlugin - Received EngineStartedEvent at timestamp: 1727304314120
[main] INFO eve.plugin.ExamplePlugin - Processing engine start event...
[main] INFO eve.plugin.ExamplePlugin - Finished processing engine start event
[main] INFO eve.engine.core.Engine - Engine started successfully.
Eve Application completed successfully!
```
