package eve.app;

import io.avaje.inject.BeanScope;
import org.junit.jupiter.api.Test;

class MainIT {

  @Test
  void testMain() {
    // Arrange & Act & Assert
    // The application should start and stop without throwing any exceptions.
    try (var beanScope = BeanScope.builder().build()) {
      beanScope.get(eve.engine.core.Engine.class).start();
    }
  }
}
