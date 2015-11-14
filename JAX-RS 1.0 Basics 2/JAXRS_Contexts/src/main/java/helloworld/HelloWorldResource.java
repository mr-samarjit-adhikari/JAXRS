package helloworld;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Providers;

@Path("/users")
public class HelloWorldResource {

    @Context
    UriInfo uriInfo;
    @Context
    HttpHeaders httpHeaders;
    @Context
    SecurityContext securityContext;
    @Context
    Request request;
    @Context
    Providers providers;

    @GET
    @Path("/getUriInfo_AbsolutePath")
    public String getUriInfo_AbsolutePath() {
        return uriInfo.getAbsolutePath().toString();
    }

    @GET
    @Path("/getHttpHeaders_Accept")
    public String getHttpHeaders_Accept() {
        return httpHeaders.getRequestHeaders().get("Accept").get(0);
    }

    @GET
    @Path("/getSecurityContext_isSecure")
    public String getSecurityContext_isSecure() {
        return securityContext.isSecure()?"true":"false";
    }
    
    @GET
    @Path("/getMethodOfRequest_Method")
    public String getMethodOfRequest_Method() {
        return request.getMethod();
    }    
    
}