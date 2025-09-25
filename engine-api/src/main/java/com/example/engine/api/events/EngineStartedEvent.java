package com.example.engine.api.events;

/**
 * Event payload for when the engine starts.
 *
 * @param startTime the timestamp when the engine started
 */
public record EngineStartedEvent(long startTime) {
}