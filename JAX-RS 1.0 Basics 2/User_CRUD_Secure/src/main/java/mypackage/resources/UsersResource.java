package mypackage.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import mypackage.model.User;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

// Will map the resource to the URL users
@Path("/users")
public class UsersResource {

    // Initialize test data through the constructor
    static List<User> users;
    static boolean isUsersCreated = false;

    public UsersResource() {

        if (isUsersCreated == false) {
            users = new ArrayList<User>();
            users.add(new User("Sang Shin", "Swimming"));   // Index 0
            users.add(new User("Tiger Woods", "Golf"));     // Index 1
            users.add(new User("Bill Clinton", "Gardening"));   // Index 2
            isUsersCreated = true;
        }
    }
    
    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;

    // Get list of users in APPLICATION_XML or APPLICATION_JSON
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> getusers() {
        return users;
    }

    // Add a new user from a form data.
    // Return the location of the newly created user and
    // user data in JSON format in the message body.
    @RolesAllowed("admin")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addUserFromFormData(
            @FormParam("name") String name,
            @FormParam("hobby") String hobby) throws IOException {

        User user = new User(name, hobby);
        users.add(user);
        int index = users.indexOf(user);

        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
        uriBuilder.path("{id}");
        return Response.created(uriBuilder.build(index)).
                entity(user).
                type(MediaType.APPLICATION_JSON).
                build();

    }

    // Add a new user from XML data.  The Content-Type
    // of the request should be set to "application/xml".
    // Return the location of the newly created user and
    // user data in XML format in the message body.
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response addUserFromXMLData(User user) throws IOException {

        users.add(user);
        int index = users.indexOf(user);

        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
        uriBuilder.path("{id}");
        return Response.created(uriBuilder.build(index)).
                entity(user).
                type(MediaType.APPLICATION_XML).
                build();

    }

    // Add a new user from JSON data.  The Content-Type
    // of the request should be set to "application/json".
    // Return the location of the newly created user and
    // user data in JSON format in the message body.
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserFromJSONData(User user) throws IOException {

        users.add(user);
        int index = users.indexOf(user);

        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
        uriBuilder.path("{id}");
        return Response.created(uriBuilder.build(index)).
                entity(user).
                type(MediaType.APPLICATION_JSON).
                build();

    }

    // Add a new user from XML data.  The Content-Type
    // of the request should be set to "application/xml".
    // Return the location of the newly created user and
    // user data in JSON format in the message body.
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_XML)
    public Response addUserFromXMLDataReturnInJSONFormat(User user) throws IOException {

        users.add(user);
        int index = users.indexOf(user);

        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
        uriBuilder.path("{id}");
        return Response.created(uriBuilder.build(index)).
                entity(user).
                type(MediaType.APPLICATION_JSON).
                build();

    }

    // Return a sub-resource.
    //
    // Defines that the next path parameter after users is
    // treated as a parameter and passed to the UserResources
    // Allows to type http://localhost:8080/User_CRUD/rest/users/1
    // 1 will be treaded as parameter user and passed to UserResource
    @Path("{user}")
    public UserResource getUser(@PathParam("user") int id) {
        return new UserResource(uriInfo, users, id);
    }

    // Get a count of users.
    // Use http://localhost:8080/User_CRUD/rest/users/count
    // to get the total number of records
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = users.size();
        return String.valueOf(count);
    }
}
