package be.wyfrel.helloworld.servletcontext;

import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;


/**
 * @author gw
 *
 */
public class JaegerConstSamplerOpenTracingContextInitializer implements ServletContextListener {
    
    public JaegerConstSamplerOpenTracingContextInitializer() {}
    
    /**
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if(!GlobalTracer.isRegistered()) {
            
            ServletContext servletContext = servletContextEvent.getServletContext();
            Configuration configuration = new Configuration(servletContext.getInitParameter("ServiceName"));
            Tracer jaegerTracer = configuration
                .withReporter(new ReporterConfiguration()
                    .withLogSpans(true)
                    .withSender(new SenderConfiguration()
                        .withEndpoint(servletContext.getInitParameter("CollectorUrl")))
                        .withFlushInterval(1)
                        .withMaxQueueSize(1))
                .withSampler(new SamplerConfiguration().withType(ConstSampler.TYPE).withParam(1))
                .getTracer();
            GlobalTracer.register(jaegerTracer);
        }
        
        Pattern pattern = Pattern.compile("/healthCheck/readinessProbe|/healthCheck/livenessProbe");
        servletContextEvent.getServletContext().setAttribute("io.opentracing.contrib.jaxrs2.server.ServerTracingFilter.skipPattern", pattern);
    }
    
    /**
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {}


}
