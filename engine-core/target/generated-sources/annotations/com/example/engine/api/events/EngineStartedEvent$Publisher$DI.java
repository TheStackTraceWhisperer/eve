package com.example.engine.api.events;

import com.example.engine.api.EngineStartedEvent;
import io.avaje.inject.events.Event;
import io.avaje.inject.events.ObserverManager;
import io.avaje.inject.spi.Builder;
import io.avaje.inject.spi.Generated;
import io.avaje.inject.spi.GenericType;
import java.lang.reflect.Type;

/**
 * Generated source - dependency injection builder for EngineStartedEvent$Publisher.
 */
@Generated("io.avaje.inject.generator")
public final class EngineStartedEvent$Publisher$DI  {

  public static final Type TYPE_EventEngineStartedEvent =
      new GenericType<Event<EngineStartedEvent>>(){}.type();

  /**
   * Create and register EngineStartedEvent$Publisher.
   */
  public static void build(Builder builder) {
    if (builder.isAddBeanFor(EngineStartedEvent$Publisher.class, TYPE_EventEngineStartedEvent)) {
      var bean = new EngineStartedEvent$Publisher(builder.get(ObserverManager.class,"!manager"));
      builder.register(bean);
    }
  }

}
