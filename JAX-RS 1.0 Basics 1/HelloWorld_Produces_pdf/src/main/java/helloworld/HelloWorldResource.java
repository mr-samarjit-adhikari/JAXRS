package helloworld;

import java.io.File;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/HelloWorld")
public class HelloWorldResource {

    private static final String FILE_PATH = "c:\\test_files\\test.pdf";

    @GET
    @Path("/testpdf")
    @Produces("application/pdf")
    public Response getFile() {
        Response response;

        try {
            File file = new File(FILE_PATH);
            ResponseBuilder responseBuilder = Response.ok((Object) file);
            return responseBuilder.build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
