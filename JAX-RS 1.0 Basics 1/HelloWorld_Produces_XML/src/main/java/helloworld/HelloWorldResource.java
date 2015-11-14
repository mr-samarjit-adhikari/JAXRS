package helloworld;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path("/HelloWorld")
public class HelloWorldResource {

    @Context
    UriInfo uriInfo;

    @GET
    @Path("/getxml")
    @Produces(MediaType.APPLICATION_XML)
    public User getUserInXMLImplicitly() {

        User user = new User();
        user.setName("Samarjit1");
        user.setAge(100);

        return user;

    }

    @GET
    @Path("/getxml2")
    @Produces(MediaType.APPLICATION_XML)
    public Response getUserInXMLThroughResponse() {

        User user = new User();
        user.setName("Samarjit2");
        user.setAge(200);
        final int DUMMY_INDEX = 2;

        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());

        return Response.created(uriBuilder.build(DUMMY_INDEX)).
                entity(user).
                type(MediaType.APPLICATION_XML).
                build();

    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_XML)
    public Response createUserInXML(User user) {

        String result = "User saved : " + user;
        return Response.status(201).entity(result).build();

    }
}