package com.example.engine.api.events;

import com.example.engine.api.EngineStartedEvent;
import io.avaje.inject.Component;
import io.avaje.inject.events.Event;
import io.avaje.inject.events.ObserverManager;
import io.avaje.inject.spi.Generated;
import java.lang.reflect.Type;

@Component
@Generated("avaje-inject-generator")
public class EngineStartedEvent$Publisher extends Event<EngineStartedEvent> {

  private static final Type TYPE = EngineStartedEvent.class;

  public EngineStartedEvent$Publisher(ObserverManager manager) {
    super(manager, TYPE, "");
  }
}
