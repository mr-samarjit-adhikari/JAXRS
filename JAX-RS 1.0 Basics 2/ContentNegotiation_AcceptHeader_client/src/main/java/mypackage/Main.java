package mypackage;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Main {

    public static void main(String[] args) {
        
        final String RESOURCE_URL = "http://localhost:8080/ContentNegotiation_AcceptHeader/resources/users/1";
        
        Client client = Client.create();
        WebResource webResource = client.resource(RESOURCE_URL);

        ClientResponse response =
                webResource.accept("application/xml;q=1.0, application/json;q=0.8").
                get(ClientResponse.class); 

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String output = response.getEntity(String.class);

        System.out.println("Output from Server .... \n");
        System.out.println(output);

    }
}
