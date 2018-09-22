package be.wyfrel.helloworld.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import io.opentracing.contrib.jaxrs2.server.ServerTracingDynamicFeature;

public class HelloWorldApplication extends Application {

    private Set<Class<?>> classes = new HashSet<>();
    
    private Set<Object> singletons = new HashSet<Object>();
    
    public HelloWorldApplication() {
        classes.add(CorsFeature.class);
        classes.add(ServerTracingDynamicFeature.class); // ServerTracingDynamicFeature Working config
        
        // Not working config 
        // 1- Using Default constructor
//        singletons.add(new ServerTracingDynamicFeature());
        
        // 2- Using Builder
//        ServerTracingDynamicFeature.Builder builder = new ServerTracingDynamicFeature.Builder(GlobalTracer.get());
//        builder.withSkipPattern("healthcheck/readinessProbe");
//        singletons.add(builder.build());
        
        
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
    
    
    public Set<Object> getSingletons() {
        return singletons;
    }
}
