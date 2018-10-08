package be.wyfrel.helloworld.application;

import io.opentracing.contrib.jaxrs2.server.OperationNameProvider.ClassNameOperationName;
import io.opentracing.contrib.jaxrs2.server.ServerTracingDynamicFeature;
import io.opentracing.util.GlobalTracer;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

/**
 * @author Pavol Loffay
 */
@Provider
public class TracingInitializer implements DynamicFeature {

  private final ServerTracingDynamicFeature serverTracingDynamicFeature =
      new ServerTracingDynamicFeature.Builder(GlobalTracer.get())
          .withOperationNameProvider(ClassNameOperationName.newBuilder())
          .withTraceSerialization(false)
      .build();

  @Override
  public void configure(ResourceInfo resourceInfo, FeatureContext context) {
    serverTracingDynamicFeature.configure(resourceInfo, context);
  }
}
