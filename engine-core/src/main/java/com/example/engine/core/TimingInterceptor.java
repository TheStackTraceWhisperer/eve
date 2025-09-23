package com.example.engine.core;

import com.example.engine.api.Timed;
import io.avaje.inject.aop.MethodInterceptor;
import io.avaje.inject.aop.Invocation;
import io.avaje.inject.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interceptor that times method execution for methods annotated with @Timed.
 * Note: AOP configuration for @Timed annotation needs additional setup in Avaje Inject.
 */
@Component
public class TimingInterceptor implements MethodInterceptor {
    
    private static final Logger log = LoggerFactory.getLogger(TimingInterceptor.class);
    
    @Override
    public void invoke(Invocation invocation) throws Throwable {
        String methodName = invocation.method().getName();
        String className = invocation.method().getDeclaringClass().getSimpleName();
        
        log.info("TimingInterceptor: Starting to time method {}.{}", className, methodName);
        long start = System.currentTimeMillis();
        
        try {
            invocation.invoke();
        } finally {
            long duration = System.currentTimeMillis() - start;
            log.info("TimingInterceptor: Method {}.{} took {} ms", className, methodName, duration);
        }
    }
}