package helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path("/HelloWorld")
public class HelloWorldResource {

    @Context
    UriInfo uriInfo;

    @GET
    @Path("/getJSONviaResponse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJSONviaResponse() {

        User user = new User();
        user.setName("Samarjit");
        user.setAge(100);
        final int DUMMY_INDEX = 2;

        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());

        return Response.created(uriBuilder.build(DUMMY_INDEX)).
                entity(user).
                type(MediaType.APPLICATION_JSON).
                build();

    }

    @GET
    @Path("/getJSONviaEntityProvider")
    @Produces(MediaType.APPLICATION_JSON)
    public User getJSONviaEntityProvider() {

        User user = new User();
        user.setName("Samarjit");
        user.setAge(100);

        return user;

    }
}