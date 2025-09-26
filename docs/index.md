# Welcome to the Eve Framework

Eve is a lightweight, modern Java framework designed for building modular, event-driven applications. It combines a powerful dependency injection container (Avaje Inject) with a simple yet effective plugin architecture, making it easy to create extensible and maintainable systems.

## Core Philosophy

The design of Eve is guided by a few key principles:

*   **Modularity First**: The framework is built around a clean separation of concerns. A clear distinction is made between the core engine and external plugins, enforced by architectural tests using ArchUnit.
*   **Developer Experience**: A primary goal is to make development intuitive. This is achieved through a clear naming convention, a simple API, and comprehensive documentation.
*   **Modern Java**: Eve is built on Java 21 and leverages modern features like virtual threads to provide high performance for concurrent tasks like plugin initialization.
*   **Loosely Coupled**: Communication between different parts of the system is handled through an event-driven architecture, minimizing direct dependencies and improving flexibility.

## How to Use These Docs

*   **If you're new to Eve**, start with the [Getting Started](./getting-started.md) guide.
*   **To learn how to extend the framework**, follow the tutorials on [Creating a Plugin](./tutorials/creating-a-plugin.md) or [Creating a Core Service](./tutorials/creating-a-core-service.md).
*   **For a quick reference on available annotations**, see the [Annotations Guide](./annotations.md).
