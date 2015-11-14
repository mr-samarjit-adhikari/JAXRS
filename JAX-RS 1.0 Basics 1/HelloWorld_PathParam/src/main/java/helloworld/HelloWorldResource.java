package helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/users")
public class HelloWorldResource {

    @GET
    @Path("{username}")
    //  In order to obtain the value of the username variable, @PathParam 
    //  is used on a method parameter   
    public Response getUser(@PathParam("username") String userName) {
        // 'userName' variable has value of 'Samarjit'
        String output = "User's name is  " + userName;
        return Response.status(200).entity(output).build();
    }

}
