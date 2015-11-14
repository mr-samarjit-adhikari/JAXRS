package helloworld;

import com.sun.jersey.core.util.Base64;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

@Path("/HelloWorld")
public class Hello {
 
    @GET
    @Path("/user")
    @Produces("text/plain")
    @RolesAllowed("demouser")
    public String getUser(@Context HttpHeaders headers) {
        System.out.println(getCredentials(headers));

        return "Secured with user role";
    }

    @GET
    @Path("/admin")
    @RolesAllowed("demoadmin")
    @Produces("text/plain")
    public String getAdmin(@Context HttpHeaders headers) {
        System.out.println(getCredentials(headers));

        return "Secured with admin role";
    }

    private String getCredentials(HttpHeaders headers) {
        String auth = headers.getRequestHeader("authorization").get(0);
                  
        System.out.println("");
        auth = auth.substring("Basic ".length());
        String[] values = new String(Base64.base64Decode(auth)).split(":");
        String username = values[0];
        String password = values[1];

        String return_val = "Username = " + username + " Password = " + password; 

        return return_val;
    }

}
