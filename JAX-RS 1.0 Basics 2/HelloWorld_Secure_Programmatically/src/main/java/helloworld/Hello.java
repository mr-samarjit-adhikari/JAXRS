package helloworld;

import com.sun.jersey.core.util.Base64;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/HelloWorld")
public class Hello {

    @Context
    SecurityContext sc;
    
    @GET
    @Path("/user")
    @Produces("text/plain")
    public Response getUser(@Context HttpHeaders headers) {
        if(sc.isUserInRole("demouser")){
            String response = "Secured with user role: " +
                                getCredentials(headers);
            return Response.status(200).entity(response).build();
        }
        else{
            return Response.status(403).entity("Unauthorized User").build();
        }
    }

    @GET
    @Path("/admin")
    @Produces("text/plain")
    public Response getAdmin(@Context HttpHeaders headers) {
        if(sc.isUserInRole("demoadmin")){
            String response = "Secured with admin role: "+
                                getCredentials(headers);
            return Response.status(200).entity(response).build();
        }
        else{
            return Response.status(403).entity("Unauthorized Admin").build();
        }
    }

    private String getCredentials(HttpHeaders headers) {
        int authSize = headers.getRequestHeader("authorization").size();
        
        String return_val = "";
        for(int count=0;count<authSize;count++){
            String auth = headers.getRequestHeader("authorization").get(count);
            System.out.println("");
            auth = auth.substring("Basic ".length());
            String[] values = new String(Base64.base64Decode(auth)).split(":");
            String username = values[0];
            String password = values[1];

            return_val = "Username = " + username + " Password = " + password; 
            System.out.println("return_val is : "+return_val);
        }

        return return_val;
    }

}
