package com.example.plugin;

import com.example.engine.api.EngineStartedEvent;
import com.example.engine.api.Plugin;
import io.avaje.inject.events.Observer;
import io.avaje.inject.events.ObserverManager;
import io.avaje.inject.spi.Builder;
import io.avaje.inject.spi.Generated;
import java.util.function.Consumer;

/**
 * Generated source - dependency injection builder for ExamplePlugin.
 */
@Generated("io.avaje.inject.generator")
public final class ExamplePlugin$DI  {

  /**
   * Create and register ExamplePlugin.
   */
  public static void build(Builder builder) {
    if (builder.isAddBeanFor("Example", ExamplePlugin.class, Plugin.class)) {
      var bean = new ExamplePlugin();
      builder.register(bean);
      Consumer<EngineStartedEvent> onEngineStart = bean::onEngineStart;
      builder
          .get(ObserverManager.class)
          .<EngineStartedEvent>registerObserver(
              EngineStartedEvent.class, new Observer<>(1000, false, onEngineStart, ""));
    }
  }

}
