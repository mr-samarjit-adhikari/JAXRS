package helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

// Will respond to http://example.com/users/?username=JohnMist
@Path("/users")
public class HelloWorldResource {

    @GET
    // REST filtering mechanism
    public Response getUser(@QueryParam("offset") int offset,
                            @QueryParam("limit") int limit) {        
        String output = "offset :  " + offset + "limit : "+limit;
        return Response.status(200).entity(output).build();
    }
}
