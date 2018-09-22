package be.wyfrel.helloworld.application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

/**
 * @Author ahc
 */
@Component
@Path("healthCheck")
@Produces(MediaType.TEXT_XML)
public class HealthCheckController {
    
    @GET
    @Path("/readinessProbe")
    @Produces(MediaType.TEXT_PLAIN)
    public Response readinessProbe(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return Response.ok("Application is ready").build();    
    }
    
    @GET
    @Path("/livenessProbe")
    @Produces(MediaType.TEXT_PLAIN)
    public Response livenessProbe(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return Response.ok("Application is alive").build();    
    }

}