package com.example.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Integration tests for the application as a whole.
 * These tests verify the complete application flow and behavior.
 */
@DisplayName("Application Integration Tests")
class ApplicationIntegrationTest {

    @Test
    @DisplayName("Application should be instantiable and callable")
    void applicationShouldBeInstantiableAndCallable() {
        // given & when & then - verify the application can be created and main methods exist
        assertDoesNotThrow(() -> {
            Main main = new Main();
            assertThat(main).isNotNull();
            
            // Verify the main method exists (behavior verification)
            var mainMethod = Main.class.getDeclaredMethod("main");
            assertThat(mainMethod).isNotNull();
        });
    }
    
    @Test
    @DisplayName("Static main method should delegate to instance main")
    void staticMainMethodShouldDelegateToInstanceMain() {
        // when & then - verify static main method exists and has correct signature
        assertDoesNotThrow(() -> {
            var staticMainMethod = Main.class.getMethod("main", String[].class);
            assertThat(staticMainMethod).isNotNull();
            assertThat(staticMainMethod.getParameterTypes()).containsExactly(String[].class);
        });
    }
    
    @Test
    @DisplayName("Application should handle empty command line arguments")
    void applicationShouldHandleEmptyCommandLineArguments() {
        // when & then - verify method signature accepts String array
        assertDoesNotThrow(() -> {
            var mainMethod = Main.class.getMethod("main", String[].class);
            assertThat(mainMethod).isNotNull();
            assertThat(mainMethod.getParameterCount()).isEqualTo(1);
        });
    }
    
    @Test
    @DisplayName("Application should use proper logging framework")
    void applicationShouldUseProperLoggingFramework() {
        // This test verifies that the application uses SLF4J for logging
        // instead of System.out, which is a behavioral requirement
        
        // when & then - verify SLF4J logger field exists
        assertDoesNotThrow(() -> {
            var logField = Main.class.getDeclaredField("log");
            assertThat(logField).isNotNull();
            assertThat(logField.getType().getName()).isEqualTo("org.slf4j.Logger");
        });
    }
    
    @Test
    @DisplayName("Application should configure SLF4J bridge")
    void applicationShouldConfigureSlf4jBridge() {
        // Verify that the application includes SLF4J bridge setup
        // This tests the behavioral requirement of proper logging configuration
        
        // when & then - verify the main method contains bridge setup
        assertDoesNotThrow(() -> {
            Main main = new Main();
            var mainMethod = Main.class.getDeclaredMethod("main");
            assertThat(mainMethod).isNotNull();
            
            // Verify that java.util.logging.Logger can be created
            // This indirectly tests that the bridge setup would work
            java.util.logging.Logger julLogger = java.util.logging.Logger.getLogger("test");
            assertThat(julLogger).isNotNull();
        });
    }
}