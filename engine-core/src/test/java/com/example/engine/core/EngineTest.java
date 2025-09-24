package com.example.engine.core;

import com.example.engine.api.EngineStartedEvent;
import com.example.engine.api.Plugin;
import io.avaje.inject.events.Event;
import io.avaje.inject.BeanScope;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the Engine class.
 */
@DisplayName("Engine Tests")
@ExtendWith(MockitoExtension.class)
class EngineTest {

    @Mock
    private Plugin plugin1;
    
    @Mock
    private Plugin plugin2;
    
    @Mock
    private Event<EngineStartedEvent> engineStartedEvent;
    
    @Mock
    private BeanScope beanScope;
    
    private Engine engine;

    @BeforeEach
    void setUp() {
        lenient().when(plugin1.getName()).thenReturn("Plugin1");
        lenient().when(plugin2.getName()).thenReturn("Plugin2");
        
        List<Plugin> plugins = Arrays.asList(plugin1, plugin2);
        engine = new Engine(plugins, engineStartedEvent, beanScope);
    }

    @Test
    @DisplayName("Should initialize with provided plugins")
    void shouldInitializeWithProvidedPlugins() {
        // given - engine is created in setUp
        
        // when - constructor is called
        
        // then - plugins are stored (verified by start() method behavior)
        assertThat(engine).isNotNull();
    }
    
    @Test
    @DisplayName("Should start engine with runtime plugin discovery")
    void shouldStartEngineWithRuntimePluginDiscovery() {
        // given
        List<Plugin> runtimePlugins = Arrays.asList(plugin1, plugin2);
        when(beanScope.list(Plugin.class)).thenReturn(runtimePlugins);
        
        // when
        engine.start();
        
        // then
        verify(beanScope).list(Plugin.class);
        verify(plugin1).initialize();
        verify(plugin2).initialize();
        verify(engineStartedEvent).fire(any(EngineStartedEvent.class));
    }
    
    @Test
    @DisplayName("Should handle empty plugin list gracefully")
    void shouldHandleEmptyPluginListGracefully() {
        // given
        when(beanScope.list(Plugin.class)).thenReturn(Collections.emptyList());
        
        // when
        engine.start();
        
        // then
        verify(beanScope).list(Plugin.class);
        verify(engineStartedEvent).fire(any(EngineStartedEvent.class));
        verifyNoInteractions(plugin1, plugin2);
    }
    
    @Test
    @DisplayName("Should fire engine started event with correct timestamp")
    void shouldFireEngineStartedEventWithCorrectTimestamp() {
        // given
        List<Plugin> runtimePlugins = Arrays.asList(plugin1);
        when(beanScope.list(Plugin.class)).thenReturn(runtimePlugins);
        long beforeStart = System.currentTimeMillis();
        
        // when
        engine.start();
        
        // then
        long afterStart = System.currentTimeMillis();
        verify(engineStartedEvent).fire(argThat(event -> 
            event.startTime() >= beforeStart && event.startTime() <= afterStart
        ));
    }
    
    @Test
    @DisplayName("Should complete even if plugin throws exception")
    void shouldCompleteEvenIfPluginThrowsException() {
        // given
        List<Plugin> runtimePlugins = Arrays.asList(plugin1, plugin2);
        when(beanScope.list(Plugin.class)).thenReturn(runtimePlugins);
        doThrow(new RuntimeException("Plugin1 failed")).when(plugin1).initialize();
        
        // when
        engine.start();
        
        // then
        verify(plugin1).initialize();
        verify(plugin2).initialize();
        verify(engineStartedEvent).fire(any(EngineStartedEvent.class));
    }
}