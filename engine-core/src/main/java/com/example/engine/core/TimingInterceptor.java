package com.example.engine.core;

import com.example.engine.api.Timed;
import io.avaje.inject.aop.AspectProvider;
import io.avaje.inject.aop.MethodInterceptor;
import io.avaje.inject.aop.Invocation;
import io.avaje.inject.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Interceptor that times method execution for methods annotated with @Timed.
 * Implements AspectProvider to provide timing interception for @Timed methods.
 */
@Component
public class TimingInterceptor implements AspectProvider<Timed> {
    
    private static final Logger log = LoggerFactory.getLogger(TimingInterceptor.class);
    
    @Override
    public MethodInterceptor interceptor(Method method, Timed timed) {
        return new TimingMethodInterceptor(method);
    }
    
    /**
     * The actual method interceptor that does the timing.
     */
    private static class TimingMethodInterceptor implements MethodInterceptor {
        
        private final Method method;
        private final Logger log = LoggerFactory.getLogger(TimingMethodInterceptor.class);
        
        public TimingMethodInterceptor(Method method) {
            this.method = method;
        }
        
        @Override
        public void invoke(Invocation invocation) throws Throwable {
            String methodName = method.getName();
            String className = method.getDeclaringClass().getSimpleName();
            
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
}