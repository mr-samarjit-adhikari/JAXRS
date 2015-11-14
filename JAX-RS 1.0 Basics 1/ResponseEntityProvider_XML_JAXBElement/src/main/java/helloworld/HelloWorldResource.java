package helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Path("/HelloWorld")
public class HelloWorldResource {

    @Context
    UriInfo uriInfo;

//    @GET
//    @Path("/getXMLviaResponse")
//    @Produces(MediaType.APPLICATION_XML)
//    public Response getXMLviaResponse() {
//
//        User user = new User();
//        user.setName("Sang Shin");
//        user.setAge(100);
//        final int DUMMY_INDEX = 2;
//
//        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
//
//        return Response.created(uriBuilder.build(DUMMY_INDEX)).
//                entity(user).
//                type(MediaType.APPLICATION_XML).
//                build();
//
//    }
    
    @GET
    @Path("/getXMLviaResponse_JAXBElement")
    @Produces(MediaType.APPLICATION_XML)
    public Response getXMLviaResponse_JAXBElement() {

        User user = new User();
        user.setName("Sang Shin");
        user.setAge(100);
        final int DUMMY_INDEX = 2;
        
        JAXBElement<User> jaxbelementUser = new JAXBElement<User>(new QName("user"), User.class, user);

        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());

        return Response.created(uriBuilder.build(DUMMY_INDEX)).
                entity(jaxbelementUser).
                type(MediaType.APPLICATION_XML).
                build();

    }

//    @GET
//    @Path("/getXMLviaEntityProvider")
//    @Produces(MediaType.APPLICATION_XML)
//    public User getXMLviaEntityProvider() {
//
//        User user = new User();
//        user.setName("Sang Shin");
//        user.setAge(100);
//
//        return user;
//
//    }
    
    @GET
    @Path("/getXMLviaEntityProvider_JAXBElement")
    @Produces(MediaType.APPLICATION_XML)
    public JAXBElement<User> getXMLviaEntityProvider_JAXBElement() {

        User user = new User();
        user.setName("Sang Shin");
        user.setAge(100);

        return new JAXBElement<User>(new QName("user"), User.class, user);

    }
}