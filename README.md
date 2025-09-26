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

*   **Dependency Injection**: Uses Avaje Inject with `@Component` for bean registration.
*   **Plugin Architecture**: A `plugin-parent` POM simplifies creation of new plugins. Plugins are discovered at runtime.
*   **Event System**: Loosely coupled communication using `@Observes` on event parameters (e.g., `onEngineStart(@Observes EngineStartedEvent event)`).
*   **AOP**: Method interception is implemented via an `@Aspect` annotation (`@Timed`) and a corresponding `MethodInterceptor` (`TimingInterceptor`).
*   **Build**: A multi-module Maven build with a Bill of Materials (BOM) in the root `pom.xml` for centralized dependency version management.
