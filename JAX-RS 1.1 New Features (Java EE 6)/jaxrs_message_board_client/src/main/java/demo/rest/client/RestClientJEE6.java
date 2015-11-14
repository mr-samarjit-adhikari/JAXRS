/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author adhikari
 */
public class RestClientJEE6 {
    public static void main(String[] args) {

        final String RESOURCE_URI = "http://localhost:8080/jaxrs_message-board-war-servlet/app/messages/2";
        try {
            Client clientObj = ClientBuilder.newClient();
            Response response = clientObj.target(RESOURCE_URI)
                                          .request(MediaType.APPLICATION_XML)
                                          .get(Response.class);

		if (response.getStatus() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		String output = response.getEntity().toString();

		System.out.println("Output from Server .... \n");
		System.out.println(output);

	} catch (Exception e) {
            e.printStackTrace();
	}
    }    
}
