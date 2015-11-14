package helloworld;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path("/users")
public class HelloWorldResource {

    List users;

    public HelloWorldResource() {
        // Initialize test data
        users = new ArrayList<User>();
        users.add(new User("Samarjit1", 100));
        users.add(new User("Samarjit2", 200));
    }

    @Context
    private HttpHeaders headers;

    @Path("{resourceID}")
    @GET
    public Response getResource(@PathParam("resourceID") int resourceID) {
        List<MediaType> acceptHeaders = headers.getAcceptableMediaTypes();
        if (acceptHeaders == null || acceptHeaders.size() == 0) {
            return Response.ok(users.get(resourceID)).
                    type(MediaType.APPLICATION_XML).
                    build();
        }

        for (MediaType mt : acceptHeaders) {
            String qValue = mt.getParameters().get("q");
            if (qValue != null && Double.valueOf(qValue).doubleValue() == 0.0) {
                break;
            }
            if (MediaType.APPLICATION_XML_TYPE.isCompatible(mt)) {
                return Response.ok(users.get(resourceID)).
                        type(MediaType.APPLICATION_XML).
                        build();
            } else if (MediaType.APPLICATION_JSON_TYPE.isCompatible(mt)) {
                return Response.ok(users.get(resourceID)).
                        type(MediaType.APPLICATION_JSON).
                        build();
            }
        }
        return Response.notAcceptable(Variant.mediaTypes(MediaType.APPLICATION_JSON_TYPE,
                MediaType.APPLICATION_XML_TYPE).add().build()).build();
    }
}