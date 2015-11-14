package helloworld;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

@Path("/helloWorldString")
public class HelloWorldResource {

    /** Creates a new instance of HelloWorldResource */
    public HelloWorldResource() {
    }

    /**
     * Retrieves representation of an instance of helloworld.HelloWorldResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getXml() {
        return "<html><body><h1>Hello World(samarjit)!</body></h1></html>";
    }

    @Path("/text")
    @GET
    @Produces("text/plain") 
    public String getText(){
        return "<html><body><h1>Hello World(samarjit)!</body></h1></html>";
    }
    
    /**
     * PUT method for updating or creating an instance of HelloWorldResource
     * @param content representation for the resource @Consumes("application/xml")
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putXml(String content) {
        System.out.println("Received content as "+content);
    }
}

