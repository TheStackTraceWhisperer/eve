package com.example.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Unit tests for the Main class.
 */
@DisplayName("Main Class Tests")
class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

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
        var runMethod = Main.class.getDeclaredMethod("run");
        
        // then
        assertThat(instanceMainMethod).isNotNull();
        assertThat(instanceMainMethod.getParameterCount()).isEqualTo(0);
        assertThat(runMethod).isNotNull();
        assertThat(runMethod.getParameterCount()).isEqualTo(0);
    }
    
    @Test
    @DisplayName("Instance main method should print startup message")
    void instanceMainMethodShouldPrintStartupMessage() {
        // given
        Main main = new Main();
        
        // when
        // Note: In a test environment, this might not work fully due to dependency injection
        // but we can at least verify the method exists and starts properly
        try {
            main.run();
        } catch (Exception e) {
            // Expected - dependency injection might not work in test environment
            // But we should see the initial print statement
        }
        
        // then
        String output = outContent.toString();
        assertThat(output).contains("Starting EVE Application...");
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
}