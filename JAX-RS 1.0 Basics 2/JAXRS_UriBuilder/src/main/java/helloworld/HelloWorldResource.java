package helloworld;

import java.util.Date;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/users")
public class HelloWorldResource {

    @Context
    UriInfo uriInfo;
    
    static final int DUMMY_INDEX = 2;

    @GET
    @Path("/get")
    public Response getUser() {
        
        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
        uriBuilder.path("{index}");
        
        String output = uriBuilder.build(DUMMY_INDEX).toString();

        return Response.ok(output).
                type(MediaType.TEXT_PLAIN).
                lastModified(new Date()).
                header("CustomHeader", "CustomValue").
                build();

    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_XML)
    public Response createUserInXML(User user) {

      
        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
        uriBuilder.path("{index}");

        return Response.created(uriBuilder.build(DUMMY_INDEX)).
                entity(user).
                type(MediaType.APPLICATION_XML).
                lastModified(new Date()).
                header("CustomHeader", "CustomValue").
                build();

    }
}