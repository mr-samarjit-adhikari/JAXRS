package helloworld;

import java.io.File;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/HelloWorld")
public class HelloWorldResource {

    private static final String FILE_PATH = "c:\\test_files\\duke.png";

    @GET
    @Path("/getFileViaResponse")
    @Produces("image/png")
    public Response getFileViaResponse() {
        Response response;

        try {
            File file = new File(FILE_PATH);

            ResponseBuilder responseBuilder = Response.ok((Object) file);

            // Setting "Content-Disposition" lets the user save the file to 
            // their computer and then decide how to use it, instead of the 
            // browser trying to use the file
            //responseBuilder.header("Content-Disposition",
            //        "attachment; filename=image_from_server.png");

            return responseBuilder.build();

        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @GET
    @Path("/getFileViaEntityProvider")
    @Produces("image/png")
    public File getFileViaEntityProvider() {

            File file = new File(FILE_PATH);
            return file;

    }
}
