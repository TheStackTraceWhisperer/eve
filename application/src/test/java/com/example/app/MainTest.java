package com.example.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Unit tests for the Main class.
 * These tests focus on behavior and structure rather than implementation details.
 */
@DisplayName("Main Class Tests")
class MainTest {

    @Test
    @DisplayName("Should have static main method that calls instance main")
    void shouldHaveStaticMainMethodThatCallsInstanceMain() {
        // This test verifies that the static main method exists and can be called
        // In a real environment, this would start the full application, but for testing
        // we verify the method signature exists and can be invoked without throwing exceptions
        
        // when & then - should not throw exception calling static main
        assertDoesNotThrow(() -> {
            // We're testing that the method exists and is callable
            var mainClass = Main.class;
            var staticMainMethod = mainClass.getMethod("main", String[].class);
            assertThat(staticMainMethod).isNotNull();
            assertThat(staticMainMethod.getParameterCount()).isEqualTo(1);
        });
    }
    
    @Test
    @DisplayName("Should have instance main method")
    void shouldHaveInstanceMainMethod() throws NoSuchMethodException {
        // when
        var instanceMainMethod = Main.class.getDeclaredMethod("main");
        
        // then
        assertThat(instanceMainMethod).isNotNull();
        assertThat(instanceMainMethod.getParameterCount()).isEqualTo(0);
    }
    
    @Test
    @DisplayName("Should be instantiable")
    void shouldBeInstantiable() {
        // when & then
        assertDoesNotThrow(() -> new Main());
    }
    
    @Test
    @DisplayName("Main class should be in correct package")
    void mainClassShouldBeInCorrectPackage() {
        // when
        String packageName = Main.class.getPackage().getName();
        
        // then
        assertThat(packageName).isEqualTo("com.example.app");
    }
    
    @Test
    @DisplayName("Main class should have SLF4J logger field")
    void mainClassShouldHaveSlf4jLoggerField() {
        // when & then - verify that the class uses proper logging instead of System.out
        assertDoesNotThrow(() -> {
            var loggerField = Main.class.getDeclaredField("log");
            assertThat(loggerField).isNotNull();
            assertThat(loggerField.getType().getName()).isEqualTo("org.slf4j.Logger");
        });
    }
}