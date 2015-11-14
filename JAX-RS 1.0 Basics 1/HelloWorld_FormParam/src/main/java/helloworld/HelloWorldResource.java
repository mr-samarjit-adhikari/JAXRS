package helloworld;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/users")
public class HelloWorldResource {

    @POST
    public Response addUser(
            @FormParam("nameVar") String name,
            @FormParam("ageVar") int age) {
        String output = "A User is added: " + name + ", age : " + age;
        return Response.status(200).entity(output).build();
    }
}
