# EVE Multi-Module Maven Project

A demonstration of a multi-module Maven project using the Avaje Inject dependency injection framework, showcasing modern Java features and architectural patterns.

## Features

- **Multi-module Maven architecture** with proper dependency management
- **Avaje Inject** dependency injection framework
- **Event-driven architecture** with @Observes pattern  
- **Plugin architecture** for extensible components
- **Java 21** with virtual threads for modern concurrency
- **Method interception** framework (TimingInterceptor setup)
- **Bill of Materials (BOM)** for version management
- **Unified logging** via SLF4J with JUL-to-SLF4J bridge for consistent log formatting

## Project Structure

```
eve/
├── pom.xml                    # Root POM with BOM and module management
├── engine-api/                # Public contracts and interfaces
│   ├── Plugin.java           # Plugin interface
│   ├── EngineStartedEvent.java # Event records
│   └── @Timed.java           # Method interception annotations
├── engine-core/               # Main application logic
│   ├── Engine.java           # Main engine with DI and virtual threads
│   └── TimingInterceptor.java # AOP interceptor implementation
├── example-plugin/            # Runtime extension example
│   └── ExamplePlugin.java    # Plugin implementation with event observation
├── engine-dist/               # Bundle aggregator (POM packaging)
└── application/               # Final executable entry point
    └── Main.java             # Java 21 instance main + traditional main
```

## Building and Running

### Prerequisites
- Java 21+
- Maven 3.6+

### Build
```bash
mvn clean install
```

### Run
```bash
cd application
mvn dependency:build-classpath -Dmdep.outputFile=classpath.txt
java -cp "target/classes:$(cat classpath.txt)" com.example.app.Main
```

## Expected Output

The application demonstrates all major features:

```
Starting EVE Application...
[main] INFO com.example.engine.core.Engine - Engine initialized with 0 plugins in constructor
[main] INFO com.example.engine.core.Engine - Starting engine with 1 plugins (runtime discovery)
[] INFO com.example.engine.core.Engine - Initializing plugin: ExamplePlugin
[] INFO com.example.plugin.ExamplePlugin - ExamplePlugin: Initializing plugin...
[] INFO com.example.plugin.ExamplePlugin - ExamplePlugin: Plugin initialization completed
[] INFO com.example.engine.core.Engine - Plugin initialized: ExamplePlugin
[main] INFO com.example.engine.core.Engine - Firing EngineStartedEvent
[main] INFO com.example.plugin.ExamplePlugin - ExamplePlugin: Received EngineStartedEvent at timestamp: [timestamp]
[main] INFO com.example.plugin.ExamplePlugin - ExamplePlugin: Processing engine start event...
[main] INFO com.example.plugin.ExamplePlugin - ExamplePlugin: Finished processing engine start event
[main] INFO com.example.engine.core.Engine - Engine started successfully
EVE Application completed successfully!
```

## Architecture Highlights

1. **Dependency Injection**: Engine receives `List<Plugin>` and `Event<EngineStartedEvent>` via constructor injection
2. **Event System**: EngineStartedEvent is fired and observed by plugins using `@Observes` annotation
3. **Plugin Discovery**: Runtime plugin discovery ensures all modules are loaded before plugin enumeration
4. **Virtual Threads**: Plugin initialization uses `Executors.newVirtualThreadPerTaskExecutor()` 
5. **Module Isolation**: Clean separation between API, core logic, plugins, distribution, and application layers

## Technical Implementation Notes

- Uses Avaje Inject `@Component` instead of `@Singleton` for bean registration
- Event observation requires `@Observes` on parameter, not method
- BeanScope provides runtime access to registered beans
- Virtual thread executors showcase Java 21 concurrency features
- AOP/Method interception setup requires additional configuration (TimingInterceptor implemented but not yet connected)
