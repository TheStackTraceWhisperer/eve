# Annotations Guide

This guide provides a reference for the key annotations used within the Eve framework. Understanding these is crucial for both using and extending the engine.

## Eve Custom Annotations

These annotations are specific to the Eve framework itself.

### `@Timed`

*   **Package**: `eve.engine.api`
*   **Target**: `Method`
*   **Purpose**: Marks a method for execution time monitoring. When a method is annotated with `@Timed`, the `TimingInterceptor` will automatically wrap the method call, logging the total execution time.

**Usage:**

```java
import eve.engine.api.Timed;

@Component
public class MyService {

    @Timed
    public void performSlowOperation() {
        // Business logic here
    }
}
```

---

## Avaje Inject & Event Annotations

Eve uses Avaje Inject for dependency injection and its related event system. The following annotations are fundamental to the framework's operation.

### `@Component`

*   **Package**: `io.avaje.inject`
*   **Target**: `Class`
*   **Purpose**: Marks a class as a singleton bean to be managed by the dependency injection container. Any class annotated with `@Component` will be instantiated once and can be injected into other components.

**Usage:**

```java
import io.avaje.inject.Component;

@Component
public class ExamplePlugin implements Plugin {
    // ...
}
```

### `@Observes`

*   **Package**: `io.avaje.inject.events`
*   **Target**: `Parameter`
*   **Purpose**: Marks a method parameter as a recipient for a specific event type. The method will be automatically invoked when an event matching the parameter's type is fired.

**Usage:**

Note that the annotation is placed on the method's *parameter*, not the method itself.

```java
import io.avaje.inject.events.Observes;

public class MyPluginListener {

    public void onEngineStart(@Observes EngineStartedEvent event) {
        System.out.println("Engine has started at timestamp: " + event.startTime());
    }
}
```

### `@Aspect`

*   **Package**: `io.avaje.inject.aop`
*   **Target**: `Annotation`
*   **Purpose**: A meta-annotation used to create new interceptor annotations. In Eve, `@Timed` is itself annotated with `@Aspect`, which tells Avaje Inject to look for a corresponding `AspectProvider` implementation (like `TimingInterceptor`).

**Usage:**

This is an advanced annotation used for creating new AOP interceptors, as seen in the definition of `@Timed`.

```java
import io.avaje.inject.aop.Aspect;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Aspect // This is the key meta-annotation
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Timed {
}
```
