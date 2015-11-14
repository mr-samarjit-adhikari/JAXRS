package helloworld;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.*;

@Path("/users")
public class HelloWorldResource {

    List users;

    public HelloWorldResource() {
        // Initialize test data
        users = new ArrayList<User>();
        users.add(new User("Sang Shin", 100));
        users.add(new User("Jo Masian", 200));
    }

    @Path("{resourceID}.{type}")
    @GET
    public Response getResource(@PathParam("resourceID") int resourceID, @PathParam("type") String type) {
        if ("xml".equals(type)) {
            return Response.ok(users.get(resourceID)).
                    type(MediaType.APPLICATION_XML).
                    build();
        } else if ("json".equals(type)) {
            return Response.ok(users.get(resourceID)).
                    type(MediaType.APPLICATION_JSON).
                    build();
        }

        return Response.status(404).build();
    }
}