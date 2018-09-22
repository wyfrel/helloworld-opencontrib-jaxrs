/**
 */
package be.wyfrel.helloworld.application.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
@Path("helloworld")
@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
public class HelloWorldController {
    
    @GET
    @Path("/")
    public String getHelloWorld () {
        return "HelloWorld";
    }
}
