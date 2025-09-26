package eve.engine.core;

import eve.engine.api.Plugin;
import io.avaje.inject.test.TestBeanScope;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

class EngineTest {

  public static class TestPlugin implements Plugin {

    @Override
    public String getName() {
      return "TestPlugin";
    }

    @Override
    public void initialize() {

    }
  }

  @Test
  void test() {
    try (
      var beanScope = TestBeanScope.builder()
        .forTesting()
        .mock(TestPlugin.class)
        .build()
    ) {
      var engine = beanScope.get(Engine.class);

      engine.start();

      verify(beanScope.get(TestPlugin.class))
        .initialize();
    }
  }

}
