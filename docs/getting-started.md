# Getting Started with Eve

This guide will walk you through the process of setting up, building, and running the Eve framework for the first time.

## 1. Prerequisites

Before you begin, ensure you have the following software installed on your system:

*   **Java 21 or later**: Eve is built on modern Java and requires a recent JDK.
*   **Maven 3.6 or later**: The project uses Maven for dependency management and the build lifecycle.

## 2. Getting the Code

Clone the project repository from your source control system. For example, using Git:

```bash
git clone <your-repository-url>
cd eve
```

## 3. Building the Framework

The project is built using a standard Maven command. Run this from the root directory of the project:

```bash
# This command cleans the project, compiles all modules, runs tests,
# and verifies the build without installing it to the local repository.
mvn clean verify
```

A successful build indicates that all modules are correctly configured and all tests, including the architectural tests, are passing.

## 4. Running the Application

Once the build is complete, you can run the main application.

1.  Navigate to the `application` module:
    ```bash
    cd application
    ```

2.  (First time only) Build the classpath file. This collects all necessary runtime dependencies into a single file for convenience:
    ```bash
    mvn dependency:build-classpath -Dmdep.outputFile=classpath.txt
    ```

3.  Run the main class using the generated classpath:
    ```bash
    java -cp "target/classes:$(cat classpath.txt)" eve.app.Main
    ```

## Expected Output

You should see output in your console confirming that the engine has started, discovered the `ExamplePlugin`, and processed the `EngineStartedEvent`.

```
Starting Eve Application...
[main] INFO eve.engine.core.Engine - Engine initialized with 1 plugins.
[main] INFO eve.engine.core.Engine - Starting engine...
[#VThread-1] INFO eve.engine.core.Engine - Initializing plugin: ExamplePlugin
...
[main] INFO eve.engine.core.Engine - Engine started successfully.
Eve Application completed successfully!
```

With the application running, you have successfully set up your development environment. You are now ready to explore the tutorials.
