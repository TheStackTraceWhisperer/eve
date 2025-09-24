package com.example.engine.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.Assertions.assertThat;
import java.lang.annotation.Annotation;

/**
 * Unit tests for the @Timed annotation.
 */
@DisplayName("@Timed Annotation Tests")
class TimedTest {

    @Test
    @DisplayName("Should have correct annotation properties")
    void shouldHaveCorrectAnnotationProperties() {
        // when
        Class<Timed> annotationClass = Timed.class;
        
        // then
        assertThat(annotationClass.isAnnotation()).isTrue();
        assertThat(annotationClass.getCanonicalName()).isEqualTo("com.example.engine.api.Timed");
    }
    
    @Test
    @DisplayName("Should be runtime retained")
    void shouldBeRuntimeRetained() throws NoSuchMethodException {
        // given
        Timed annotation = TimedTest.class.getDeclaredMethod("dummyTimedMethod").getAnnotation(Timed.class);
        
        // then - if this test method is annotated, we should be able to retrieve it at runtime
        // This validates that @Retention(RetentionPolicy.RUNTIME) is working
        assertThat(annotation).isNotNull();
    }
    
    @Test
    @DisplayName("Should have @Aspect annotation")
    void shouldHaveAspectAnnotation() {
        // when
        Annotation[] annotations = Timed.class.getAnnotations();
        
        // then
        assertThat(annotations).isNotEmpty();
        boolean hasAspectAnnotation = false;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().getSimpleName().equals("Aspect")) {
                hasAspectAnnotation = true;
                break;
            }
        }
        assertThat(hasAspectAnnotation).isTrue();
    }
    
    // Test method that is annotated with @Timed for runtime retention test
    @Timed
    public void dummyTimedMethod() {
        // This method exists solely to test runtime retention
    }
}