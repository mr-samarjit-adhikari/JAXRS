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
    @Path("/getXMLviaResponse")
    @Produces(MediaType.APPLICATION_XML)
    public Response getXMLviaResponse() {

        User user = new User();
        user.setName("Samarjit-XMLviaResponse");
        user.setAge(100);
        final int DUMMY_INDEX = 2;

        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());

        return Response.created(uriBuilder.build(DUMMY_INDEX)).
                entity(user).
                type(MediaType.APPLICATION_XML).
                build();

    }

    @GET
    @Path("/getXMLviaEntityProvider")
    @Produces(MediaType.APPLICATION_XML)
    public User getXMLviaEntityProvider() {

        User user = new User();
        user.setName("Samarjit-XMLviaEntityProvider");
        user.setAge(100);

        return user;

    }
}