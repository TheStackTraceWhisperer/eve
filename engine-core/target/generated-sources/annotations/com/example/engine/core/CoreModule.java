package com.example.engine.core;

import io.avaje.inject.BeanScope;
import io.avaje.inject.InjectModule;
import io.avaje.inject.spi.AvajeModule;
import io.avaje.inject.spi.Builder;
import io.avaje.inject.spi.DependencyMeta;
import io.avaje.inject.spi.Generated;
import io.avaje.inject.spi.GenericType;
import java.lang.reflect.Type;
import com.example.engine.api.events.EngineStartedEvent$Publisher$DI;

/**
 * Avaje Inject module for Core.
 * 
 * When using the Java module system, this generated class should be explicitly
 * registered in module-info via a <code>provides</code> clause like:
 * 
 * <pre>{@code
 * 
 *   module example {
 *     requires io.avaje.inject;
 *     
 *     provides io.avaje.inject.spi.InjectExtension with com.example.engine.core.CoreModule;
 *     
 *   }
 * 
 * }</pre>
 */
@Generated("io.avaje.inject.generator")
@InjectModule()
public final class CoreModule implements AvajeModule {

  private Builder builder;

  @Override
  public Type[] autoProvides() {
    return new Type[] {
      com.example.engine.api.events.EngineStartedEvent$Publisher.class,
      com.example.engine.core.Engine.class,
      com.example.engine.core.TimingInterceptor.class,
      io.avaje.inject.aop.MethodInterceptor.class,
      new GenericType<io.avaje.inject.events.Event<com.example.engine.api.EngineStartedEvent>>(){},
    };
  }

  @Override
  public Class<?>[] classes() {
    return new Class<?>[] {
      com.example.engine.api.events.EngineStartedEvent$Publisher.class,
      com.example.engine.core.Engine.class,
      com.example.engine.core.TimingInterceptor.class,
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
    build_core_TimingInterceptor();
    build_events_EngineStartedEvent$Publisher();
    build_core_Engine();
  }

  @DependencyMeta(
      type = "com.example.engine.core.TimingInterceptor",
      provides = {"io.avaje.inject.aop.MethodInterceptor"},
      autoProvides = {
        "io.avaje.inject.aop.MethodInterceptor",
        "com.example.engine.core.TimingInterceptor"
      })
  private void build_core_TimingInterceptor() {
    TimingInterceptor$DI.build(builder);
  }

  @DependencyMeta(
      type = "com.example.engine.api.events.EngineStartedEvent$Publisher",
      provides = {"io.avaje.inject.events.Event<com.example.engine.api.EngineStartedEvent>"},
      dependsOn = {"io.avaje.inject.events.ObserverManager"},
      autoProvides = {
        "com.example.engine.api.events.EngineStartedEvent$Publisher",
        "io.avaje.inject.events.Event<com.example.engine.api.EngineStartedEvent>"
      })
  private void build_events_EngineStartedEvent$Publisher() {
    EngineStartedEvent$Publisher$DI.build(builder);
  }

  @DependencyMeta(
      type = "com.example.engine.core.Engine",
      dependsOn = {
        "soft:com.example.engine.api.Plugin",
        "io.avaje.inject.events.Event<com.example.engine.api.EngineStartedEvent>"
      },
      autoProvides = {"com.example.engine.core.Engine"})
  private void build_core_Engine() {
    Engine$DI.build(builder);
  }

}
