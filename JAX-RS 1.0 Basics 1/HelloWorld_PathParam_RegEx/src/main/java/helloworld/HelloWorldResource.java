package helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/users")
public class HelloWorldResource {
    
    @GET
    @Path("/{username: [a-zA-Z][a-zA-Z_0-9]+}")
    public Response getUserRegExpression(@PathParam("username") String userName) {
        String output = "User's name is " + userName;
        return Response.status(200).entity(output).build();
    }
}
