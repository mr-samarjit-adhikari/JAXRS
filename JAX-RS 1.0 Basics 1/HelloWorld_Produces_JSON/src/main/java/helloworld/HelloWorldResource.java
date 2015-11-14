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
    @Path("/jsonString")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserInJSON() {

        User user = new User();
        user.setName("Samarjit");
        user.setAge(100);

        return user;

    }

    @GET
    @Path("/jsonString2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInJSONThroughResponse() {

        User user = new User();
        user.setName("Samarjit Adhikari");
        user.setAge(200);
        final int DUMMY_INDEX = 3;

        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());

        return Response.created(uriBuilder.build(DUMMY_INDEX)).
                entity(user).
                type(MediaType.APPLICATION_JSON).
                build();

    }

    @POST
    @Path("/jsonUsers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUserInJSON(User user) {

        String result = "User saved : " + user.getName()
                +" With Age : "+user.getAge();
        return Response.status(201).entity(result).build();

    }
}