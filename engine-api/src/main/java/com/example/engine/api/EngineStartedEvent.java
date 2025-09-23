package com.example.engine.api;

/**
 * Event payload for when the engine starts.
 * 
 * @param startTime the timestamp when the engine started
 */
public record EngineStartedEvent(long startTime) {
}