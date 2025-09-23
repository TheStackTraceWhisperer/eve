package com.example.engine.core;

import io.avaje.inject.aop.MethodInterceptor;
import io.avaje.inject.spi.Builder;
import io.avaje.inject.spi.Generated;

/**
 * Generated source - dependency injection builder for TimingInterceptor.
 */
@Generated("io.avaje.inject.generator")
public final class TimingInterceptor$DI  {

  /**
   * Create and register TimingInterceptor.
   */
  public static void build(Builder builder) {
    if (builder.isAddBeanFor(TimingInterceptor.class, MethodInterceptor.class)) {
      var bean = new TimingInterceptor();
      builder.register(bean);
    }
  }

}
