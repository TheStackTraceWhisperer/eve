package com.example.plugin;

import io.avaje.inject.BeanScope;
import io.avaje.inject.InjectModule;
import io.avaje.inject.spi.AvajeModule;
import io.avaje.inject.spi.Builder;
import io.avaje.inject.spi.DependencyMeta;
import io.avaje.inject.spi.Generated;
import io.avaje.inject.spi.GenericType;
import java.lang.reflect.Type;

/**
 * Avaje Inject module for Plugin.
 * 
 * When using the Java module system, this generated class should be explicitly
 * registered in module-info via a <code>provides</code> clause like:
 * 
 * <pre>{@code
 * 
 *   module example {
 *     requires io.avaje.inject;
 *     
 *     provides io.avaje.inject.spi.InjectExtension with com.example.plugin.PluginModule;
 *     
 *   }
 * 
 * }</pre>
 */
@Generated("io.avaje.inject.generator")
@InjectModule()
public final class PluginModule implements AvajeModule {

  private Builder builder;

  @Override
  public Type[] autoProvides() {
    return new Type[] {
      com.example.engine.api.Plugin.class,
      com.example.plugin.ExamplePlugin.class,
    };
  }

  @Override
  public Class<?>[] classes() {
    return new Class<?>[] {
      com.example.plugin.ExamplePlugin.class,
    };
  }

  /**
   * Creates all the beans in order based on constructor dependencies.
   * The beans are registered into the builder along with callbacks for
   * field/method injection, and lifecycle support.
   */
  @Override
  public void build(Builder builder) {
    this.builder = builder;
    // create beans in order based on constructor dependencies
    // i.e. "provides" followed by "dependsOn"
    build_plugin_ExamplePlugin_Example();
  }

  @DependencyMeta(
      type = "com.example.plugin.ExamplePlugin",
      name = "Example",
      provides = {"com.example.engine.api.Plugin"},
      autoProvides = {
        "com.example.engine.api.Plugin",
        "com.example.plugin.ExamplePlugin"
      })
  private void build_plugin_ExamplePlugin_Example() {
    ExamplePlugin$DI.build(builder);
  }

}
