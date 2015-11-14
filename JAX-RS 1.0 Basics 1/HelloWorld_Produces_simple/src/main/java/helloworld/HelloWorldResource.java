package helloworld;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;

@Path("/HelloWorld")
@Produces("text/plain")
public class HelloWorldResource {

    // defaults to the MIME type of the @Produces 
    // annotation at the class level - “test/plain”
    @GET
    public String doGetAsPlainText(@HeaderParam("accept") String accept) {
        String output = "Hello World! as plain text\n" + accept;
        return output;
    }

    @GET
    @Produces("text/html")
    public String getGetAsHtml(@HeaderParam("accept") String accept) {
        String output = "<html><body><h3>Hello World! as HTML<br/>" + accept + "</body></h3></html>";
        return output;
    }
}
