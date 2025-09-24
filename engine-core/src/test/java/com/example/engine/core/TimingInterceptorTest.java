package com.example.engine.core;

import com.example.engine.api.Timed;
import io.avaje.inject.aop.MethodInterceptor;
import io.avaje.inject.aop.Invocation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the TimingInterceptor class.
 */
@DisplayName("TimingInterceptor Tests")
@ExtendWith(MockitoExtension.class)
class TimingInterceptorTest {

    @Mock
    private Timed timedAnnotation;
    
    @Mock
    private Invocation invocation;
    
    private TimingInterceptor timingInterceptor;

    @BeforeEach
    void setUp() {
        timingInterceptor = new TimingInterceptor();
    }

    @Test
    @DisplayName("Should create method interceptor for timed method")
    void shouldCreateMethodInterceptorForTimedMethod() throws NoSuchMethodException {
        // given
        Method method = TimingInterceptorTest.class.getDeclaredMethod("dummyTimedMethod");
        
        // when
        MethodInterceptor interceptor = timingInterceptor.interceptor(method, timedAnnotation);
        
        // then
        assertThat(interceptor).isNotNull();
    }
    
    @Test
    @DisplayName("Should intercept method execution and measure time")
    void shouldInterceptMethodExecutionAndMeasureTime() throws Throwable {
        // given
        Method method = TimingInterceptorTest.class.getDeclaredMethod("dummyTimedMethod");
        MethodInterceptor interceptor = timingInterceptor.interceptor(method, timedAnnotation);
        
        // when
        interceptor.invoke(invocation);
        
        // then
        verify(invocation).invoke();
    }
    
    @Test
    @DisplayName("Should handle method execution exception gracefully")
    void shouldHandleMethodExecutionExceptionGracefully() throws Throwable {
        // given
        Method method = TimingInterceptorTest.class.getDeclaredMethod("dummyTimedMethod");
        MethodInterceptor interceptor = timingInterceptor.interceptor(method, timedAnnotation);
        RuntimeException expectedException = new RuntimeException("Method failed");
        doThrow(expectedException).when(invocation).invoke();
        
        // when & then
        try {
            interceptor.invoke(invocation);
        } catch (RuntimeException e) {
            assertThat(e).isSameAs(expectedException);
        }
        
        // The interceptor should still call the original method even if it throws
        verify(invocation).invoke();
    }
    
    @Test
    @DisplayName("Should work with different method signatures")
    void shouldWorkWithDifferentMethodSignatures() throws NoSuchMethodException {
        // given
        Method methodWithParams = TimingInterceptorTest.class.getDeclaredMethod("dummyMethodWithParams", String.class, int.class);
        Method methodWithReturnValue = TimingInterceptorTest.class.getDeclaredMethod("dummyMethodWithReturnValue");
        
        // when
        MethodInterceptor interceptor1 = timingInterceptor.interceptor(methodWithParams, timedAnnotation);
        MethodInterceptor interceptor2 = timingInterceptor.interceptor(methodWithReturnValue, timedAnnotation);
        
        // then
        assertThat(interceptor1).isNotNull();
        assertThat(interceptor2).isNotNull();
    }
    
    // Dummy methods for testing
    @Timed
    private void dummyTimedMethod() {
        // Test method
    }
    
    private void dummyMethodWithParams(String param1, int param2) {
        // Test method with parameters
    }
    
    private String dummyMethodWithReturnValue() {
        return "test";
    }
}