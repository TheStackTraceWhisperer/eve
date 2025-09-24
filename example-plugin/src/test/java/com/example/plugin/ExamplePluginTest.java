package com.example.plugin;

import com.example.engine.api.EngineStartedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Unit tests for the ExamplePlugin class.
 */
@DisplayName("ExamplePlugin Tests")
@ExtendWith(MockitoExtension.class)
class ExamplePluginTest {

    private ExamplePlugin examplePlugin;

    @BeforeEach
    void setUp() {
        examplePlugin = new ExamplePlugin();
    }

    @Test
    @DisplayName("Should return correct plugin name")
    void shouldReturnCorrectPluginName() {
        // when
        String name = examplePlugin.getName();
        
        // then
        assertThat(name).isEqualTo("ExamplePlugin");
    }
    
    @Test
    @DisplayName("Should initialize without throwing exception")
    void shouldInitializeWithoutThrowingException() {
        // when & then
        assertDoesNotThrow(() -> examplePlugin.initialize());
    }
    
    @Test
    @DisplayName("Should handle engine start event")
    void shouldHandleEngineStartEvent() {
        // given
        long startTime = 1234567890L; // Use fixed timestamp for testing
        EngineStartedEvent event = new EngineStartedEvent(startTime);
        
        // when & then
        assertDoesNotThrow(() -> examplePlugin.onEngineStart(event));
    }
    
    @Test
    @DisplayName("Should process engine start event")
    void shouldProcessEngineStartEvent() {
        // given
        long startTime = 1234567890L; // Use fixed timestamp for testing
        EngineStartedEvent event = new EngineStartedEvent(startTime);
        
        // when & then
        assertDoesNotThrow(() -> examplePlugin.processEngineStart(event));
    }
    
    @Test
    @DisplayName("Should be annotated with @Component")
    void shouldBeAnnotatedWithComponent() {
        // when
        Class<?> pluginClass = ExamplePlugin.class;
        
        // then
        boolean hasComponentAnnotation = false;
        for (var annotation : pluginClass.getAnnotations()) {
            if (annotation.annotationType().getSimpleName().equals("Component")) {
                hasComponentAnnotation = true;
                break;
            }
        }
        assertThat(hasComponentAnnotation).isTrue();
    }
    
    @Test
    @DisplayName("Initialize method should be annotated with @Timed")
    void initializeMethodShouldBeAnnotatedWithTimed() throws NoSuchMethodException {
        // given
        var initializeMethod = ExamplePlugin.class.getMethod("initialize");
        
        // when
        boolean hasTimedAnnotation = false;
        for (var annotation : initializeMethod.getAnnotations()) {
            if (annotation.annotationType().getSimpleName().equals("Timed")) {
                hasTimedAnnotation = true;
                break;
            }
        }
        
        // then
        assertThat(hasTimedAnnotation).isTrue();
    }
    
    @Test
    @DisplayName("ProcessEngineStart method should be annotated with @Timed") 
    void processEngineStartMethodShouldBeAnnotatedWithTimed() throws NoSuchMethodException {
        // given
        var processMethod = ExamplePlugin.class.getMethod("processEngineStart", EngineStartedEvent.class);
        
        // when
        boolean hasTimedAnnotation = false;
        for (var annotation : processMethod.getAnnotations()) {
            if (annotation.annotationType().getSimpleName().equals("Timed")) {
                hasTimedAnnotation = true;
                break;
            }
        }
        
        // then
        assertThat(hasTimedAnnotation).isTrue();
    }
    
    @Test
    @DisplayName("OnEngineStart method should exist and be public")
    void onEngineStartMethodShouldExistAndBePublic() throws NoSuchMethodException {
        // given
        var onEngineStartMethod = ExamplePlugin.class.getMethod("onEngineStart", EngineStartedEvent.class);
        
        // when
        int modifiers = onEngineStartMethod.getModifiers();
        
        // then
        assertThat(java.lang.reflect.Modifier.isPublic(modifiers)).isTrue();
        assertThat(onEngineStartMethod.getParameterCount()).isEqualTo(1);
        assertThat(onEngineStartMethod.getParameterTypes()[0]).isEqualTo(EngineStartedEvent.class);
    }
}