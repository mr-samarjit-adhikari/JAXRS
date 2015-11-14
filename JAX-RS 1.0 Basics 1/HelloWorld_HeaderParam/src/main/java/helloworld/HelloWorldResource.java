package helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/users")
public class HelloWorldResource {

    @GET
    @Path("/acceptHeader")
    public Response addUser(@HeaderParam("accept") String accept) {
        String output = "addUser is called, accept header: " + accept;
        return Response.status(200).entity(output).build();

    }
}
