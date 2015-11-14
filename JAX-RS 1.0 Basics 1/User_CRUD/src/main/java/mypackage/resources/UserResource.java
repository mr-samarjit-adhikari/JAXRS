package mypackage.resources;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import mypackage.model.User;

public class UserResource {
    
    List<User> users;
    UriInfo uriInfo;
    int id;
    User user;


    public UserResource(UriInfo uriInfo, List users, int id) {
        this.uriInfo = uriInfo;
        this.users = users;
        this.id = id;
    }
    
    // Get a particular user
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User getUser() throws NotFoundException {
 
            User user = users.get(id);
            return user;
    }

    // Update an existing user 
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response putUser(User user) throws NotFoundException {

        users.set(id, user);

        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
        uriBuilder.path("{id}");
        return Response.created(uriBuilder.build(id)).
                entity(user).
                type(MediaType.APPLICATION_XML).
                build();
    }

    @DELETE
    public Response deleteUser() throws NotFoundException {
        Response response;       
        response = Response.status(204).build();
        
        users.remove(id);
        return response;
    }
}
