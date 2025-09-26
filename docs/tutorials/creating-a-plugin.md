# Tutorial: Creating a Plugin

This tutorial will guide you through the process of creating a new plugin for the Eve framework. Our new plugin, `GreetingPlugin`, will listen for the `EngineStartedEvent` and print a simple greeting to the console.

## 1. Create a New Maven Module

First, create a new directory for your plugin alongside the other modules like `engine-core` and `example-plugin`.

```bash
mkdir greeting-plugin
```

## 2. Create the `pom.xml`

Every Maven module needs a `pom.xml`. Create a new `pom.xml` file inside the `greeting-plugin` directory. The key here is to set the `<parent>` to our `plugin-parent`.

**`greeting-plugin/pom.xml`**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <groupId>eve</groupId>
    <artifactId>plugin-parent</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <relativePath>../plugin-parent/pom.xml</relativePath>
  </parent>

  <artifactId>greeting-plugin</artifactId>
  <name>Greeting Plugin</name>
  <description>A simple plugin that prints a greeting.</description>

</project>
```

By inheriting from `plugin-parent`, this POM automatically gets all the necessary dependencies (like `engine-api`, `engine-core`, `avaje-inject`) and the complex compiler configuration needed for annotation processing.

## 3. Create the Plugin Class

Now, create the Java source directory and the plugin class itself.

**`greeting-plugin/src/main/java/eve/plugin/greeting/GreetingPlugin.java`**
```java
package eve.plugin.greeting;

import eve.engine.api.Plugin;
import eve.engine.api.events.EngineStartedEvent;
import io.avaje.inject.Component;
import io.avaje.inject.events.Observes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class GreetingPlugin implements Plugin {

    private static final Logger log = LoggerFactory.getLogger(GreetingPlugin.class);

    @Override
    public String getName() {
        return "GreetingPlugin";
    }

    public void onEngineStarted(@Observes EngineStartedEvent event) {
        log.info("Hello from the GreetingPlugin! The engine has started.");
    }
}
```

**Key Points:**
*   `@Component`: This tells Avaje Inject to create and manage this class as a singleton bean.
*   `implements Plugin`: This marks the class as a plugin, although the methods from the interface (`initialize`, `shutdown`) are optional with default implementations.
*   `onEngineStarted(@Observes ...)`: This method will be automatically called when the `EngineStartedEvent` is fired.

## 4. Add the Module to the Main Build

Finally, you need to tell Maven about your new module. Open the root `pom.xml` file and add `greeting-plugin` to the `<modules>` section.

**`pom.xml`**
```xml
  <modules>
    <module>engine-api</module>
    <module>engine-core</module>
    <module>plugin-parent</module>
    <module>example-plugin</module>
    <module>greeting-plugin</module> <!-- Add this line -->
    <module>engine-dist</module>
    <module>application</module>
  </modules>
```

## 5. Build and Run

Now, run the full build and then start the application as you did in the Getting Started guide.

```bash
# From the project root
mvn clean verify

# Run the application
cd application
java -cp "target/classes:$(cat classpath.txt)" eve.app.Main
```

You should now see the log output from your new plugin in the console!

```
... (other logs)
[main] INFO eve.plugin.greeting.GreetingPlugin - Hello from the GreetingPlugin! The engine has started.
... (other logs)
```

Congratulations, you have successfully created and integrated a new plugin into the Eve framework.
