package com.example.engine.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for EngineStartedEvent record.
 */
@DisplayName("EngineStartedEvent Tests")
class EngineStartedEventTest {

    @Test
    @DisplayName("Should create event with start time")
    void shouldCreateEventWithStartTime() {
        // given
        long startTime = 1234567890L; // Use a fixed timestamp for testing
        
        // when
        EngineStartedEvent event = new EngineStartedEvent(startTime);
        
        // then
        assertThat(event.startTime()).isEqualTo(startTime);
    }
    
    @Test
    @DisplayName("Should have proper toString representation")
    void shouldHaveProperToStringRepresentation() {
        // given
        long startTime = 1234567890L;
        EngineStartedEvent event = new EngineStartedEvent(startTime);
        
        // when
        String toString = event.toString();
        
        // then
        assertThat(toString).contains("EngineStartedEvent");
        assertThat(toString).contains("startTime=" + startTime);
    }
    
    @Test
    @DisplayName("Should have proper equals and hashCode")
    void shouldHaveProperEqualsAndHashCode() {
        // given
        long startTime = 1234567890L;
        EngineStartedEvent event1 = new EngineStartedEvent(startTime);
        EngineStartedEvent event2 = new EngineStartedEvent(startTime);
        EngineStartedEvent event3 = new EngineStartedEvent(startTime + 1000);
        
        // then
        assertThat(event1).isEqualTo(event2);
        assertThat(event1).isNotEqualTo(event3);
        assertThat(event1.hashCode()).isEqualTo(event2.hashCode());
        assertThat(event1.hashCode()).isNotEqualTo(event3.hashCode());
    }
}