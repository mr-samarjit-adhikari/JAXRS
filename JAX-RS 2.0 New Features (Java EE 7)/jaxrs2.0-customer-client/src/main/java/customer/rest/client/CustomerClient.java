/*
 * Copyright 2011 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */
package customer.rest.client;

import java.util.List;
import java.util.logging.Logger;
import customer.data.Address;
import customer.data.Customer;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class CustomerClient {

    public static final Logger logger = Logger.getLogger(
            CustomerClient.class.getCanonicalName());
    private static final String REST_SERVICE_URL = "http://localhost:8080/jaxrs2.0-customer/resources/Customer";

    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();

        // Test the POST method
        Customer customer1 = new Customer();
        Address address = new Address();
        address.setCity("Bangalore");
        address.setCountry("India");
        address.setNumber(23);
        address.setProvince("Providence");
        address.setStreet("some street");
        address.setZip("12345");
        customer1.setAddress(address);
        customer1.setEmail("samarjit.adhikari@gmail.com");
        customer1.setFirstname("Samarjit");
        customer1.setLastname("Adhikari");
        customer1.setPhone("123456789");

        // Perform POST operation
        Response response = client
                .target(REST_SERVICE_URL)
                .request(MediaType.APPLICATION_XML)
                .post(Entity.entity(customer1, MediaType.APPLICATION_XML),
                        Response.class);

        if (response.getStatus() == Status.CREATED.getStatusCode()) {
            System.out.println("---->POST succeeded");
        } else {
            System.out.println("---->POST failed");
        }

        // Test the POST method
        Customer customer2 = new Customer();
        address = new Address();
        address.setCity("Kolkata");
        address.setCountry("India");
        address.setNumber(232);
        address.setProvince("Providence2");
        address.setStreet("some street2");
        address.setZip("12345");
        customer2.setAddress(address);
        customer2.setEmail("samarjit2adhikari@gmail.com");
        customer2.setFirstname("Samarjit2");
        customer2.setLastname("Adhikari2");
        customer2.setPhone("123456789");

        // Perform POST operation
        Response response2 = client
                .target(REST_SERVICE_URL)
                .request(MediaType.APPLICATION_XML)
                .post(Entity.entity(customer2, MediaType.APPLICATION_XML),
                        Response.class);

        if (response2.getStatus() == Status.CREATED.getStatusCode()) {
            System.out.println("---->POST succeeded");
        } else {
            System.out.println("---->POST failed");
        }

        // Perform GET all operation
        List<Customer> customers
                = client.target(REST_SERVICE_URL)
                .path("all")
                .request(MediaType.APPLICATION_XML)
                .get(new GenericType<List<Customer>>() {
                });

        int testId = 0;
        if (customers.isEmpty() == false) {
            System.out.println("---->Get all succeeded");
            for (Customer c : customers) {
                System.out.println("---->Customer id:name: " + c.getId() + ":" + c.getFirstname() + " " + c.getLastname());
                testId = c.getId();
            }
        } else {
            System.out.println("---->Get all failed");
        }

        // Perform GET operation
        Customer c2 = client.target(REST_SERVICE_URL).path("/{id}")
                .resolveTemplate("id", String.valueOf(testId))
                .request(MediaType.APPLICATION_XML)
                .get(Customer.class);

        if (c2 != null) {
            System.out.println("---->GET succeeded, firstName is " + c2.getFirstname());
        } else {
            System.out.println("---->GET failed");
        }

        // Perform UPDATE operation
        customer2.setFirstname("Samarjit3");

        Response response3 = client.target(REST_SERVICE_URL).path("/{id}")
                .resolveTemplate("id", String.valueOf(testId))
                .request(MediaType.APPLICATION_XML)
                .put(Entity.entity(customer2, MediaType.APPLICATION_XML),
                        Response.class);

        if (response3.getStatus() == Status.OK.getStatusCode()) {
            System.out.println("---->PUT succeeded");
        } else {
            System.out.println("---->PUT failed");
        }


        
        // Perform DELETE operation
        Response response4 = client.target(REST_SERVICE_URL).path("/{id}")
                .resolveTemplate("id", String.valueOf(testId))
                .request().delete();

        if (response4.getStatus() == Status.NO_CONTENT.getStatusCode()) {
            System.out.println("---->DELETE succeeded");
        } else {
            System.out.println("---->DELETE failed");
        }

    }
}
