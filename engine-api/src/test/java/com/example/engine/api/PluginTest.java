package com.example.engine.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the Plugin interface.
 * Since Plugin is an interface, we test it using a mock implementation.
 */
@DisplayName("Plugin Interface Tests")
class PluginTest {

    private Plugin plugin;

    @BeforeEach
    void setUp() {
        plugin = mock(Plugin.class);
    }

    @Test
    @DisplayName("Should have getName method")
    void shouldHaveGetNameMethod() {
        // given
        String expectedName = "TestPlugin";
        when(plugin.getName()).thenReturn(expectedName);
        
        // when
        String actualName = plugin.getName();
        
        // then
        assertThat(actualName).isEqualTo(expectedName);
        verify(plugin).getName();
    }
    
    @Test
    @DisplayName("Should have initialize method")
    void shouldHaveInitializeMethod() {
        // when
        plugin.initialize();
        
        // then
        verify(plugin).initialize();
    }
    
    @Test
    @DisplayName("Plugin interface should be implementable")
    void shouldBeImplementable() {
        // given
        Plugin testPlugin = new TestPluginImplementation();
        
        // when & then
        assertThat(testPlugin.getName()).isEqualTo("TestPlugin");
        
        // Should not throw exception
        testPlugin.initialize();
    }
    
    /**
     * Simple test implementation of Plugin interface
     */
    private static class TestPluginImplementation implements Plugin {
        @Override
        public String getName() {
            return "TestPlugin";
        }

        @Override
        public void initialize() {
            // Test implementation - does nothing
        }
    }
}