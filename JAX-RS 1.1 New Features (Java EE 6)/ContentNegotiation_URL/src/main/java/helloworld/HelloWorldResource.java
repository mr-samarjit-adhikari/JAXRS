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

    @Path("{resourceID}.xml")
    @GET
    public Response getResourceInXML(@PathParam("resourceID") int resourceID) {
        return Response.ok(users.get(resourceID)).
                type(MediaType.APPLICATION_XML).
                build();
    }

    @Path("{resourceID}.json")
    @GET
    public Response getResourceInJSON(@PathParam("resourceID") int resourceID) {;
        return Response.ok(users.get(resourceID)).
                type(MediaType.APPLICATION_JSON).
                build();
    }
}