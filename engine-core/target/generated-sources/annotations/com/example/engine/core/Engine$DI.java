package com.example.engine.core;

import com.example.engine.api.EngineStartedEvent;
import com.example.engine.api.Plugin;
import io.avaje.inject.BeanScope;
import io.avaje.inject.events.Event;
import io.avaje.inject.spi.Builder;
import io.avaje.inject.spi.Generated;
import io.avaje.inject.spi.GenericType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Generated source - dependency injection builder for Engine.
 */
@Generated("io.avaje.inject.generator")
public final class Engine$DI  {

  public static final Type TYPE_EventEngineStartedEvent =
      new GenericType<Event<EngineStartedEvent>>(){}.type();

  /**
   * Create and register Engine.
   */
  public static void build(Builder builder) {
    if (builder.isAddBeanFor(Engine.class)) {
      var bean = new Engine(builder.list(Plugin.class), builder.get(TYPE_EventEngineStartedEvent), builder.get(BeanScope.class,"!beanScope"));
      builder.register(bean);
    }
  }

}
