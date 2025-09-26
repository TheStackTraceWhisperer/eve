package eve.plugin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ExamplePluginTest {

  @Test
  void testInitialize() {
    ExamplePlugin plugin = new ExamplePlugin();
    assertDoesNotThrow(plugin::initialize);
  }

}
