package helloworld;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.*;

@Path("/users")
public class HelloWorldResource {

    List<User> users;

    public HelloWorldResource() {
        // Initialize test data
        users = new ArrayList<User>();
        users.add(new User("Samarjit1", 100));
        users.add(new User("Samarjit2", 200));
    }

    @Path("{resourceID}.xml")
    @GET
    public Response getResourceInXML(@PathParam("resourceID") int resourceID) {
        try {
            User user = users.get(resourceID);
            return Response.ok(users.get(resourceID)).
                    type(MediaType.APPLICATION_XML).
                    build();
        } catch (IndexOutOfBoundsException e) {
              throw new javax.ws.rs.NotFoundException("User " + resourceID + ", does not found");
        }
    }

}