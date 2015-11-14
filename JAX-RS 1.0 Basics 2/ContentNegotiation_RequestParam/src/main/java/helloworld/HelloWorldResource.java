package helloworld;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

@Path("/users")
public class HelloWorldResource {

    List users;

    public HelloWorldResource() {
        // Initialize test data
        users = new ArrayList<User>();
        users.add(new User("Samarjit1", 100));
        users.add(new User("Samarjit2", 200));
    }

    @Path("{resourceID}")
    @GET
    public Response getResource(@PathParam("resourceID") int resourceID,
            @QueryParam("format") String format) {
        if (format == null || "xml".equals(format)) {
            return Response.ok(users.get(resourceID)).
                    type(MediaType.APPLICATION_XML).
                    build();
        } else if ("json".equals(format)) {
            return Response.ok(users.get(resourceID)).
                    type(MediaType.APPLICATION_JSON).
                    build();
        }

        return Response.notAcceptable(Variant.mediaTypes(MediaType.APPLICATION_XML_TYPE,
                MediaType.APPLICATION_JSON_TYPE).add().build()).build();
    }
}