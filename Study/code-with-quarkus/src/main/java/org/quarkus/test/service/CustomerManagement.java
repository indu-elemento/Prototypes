package org.quarkus.test.service;

import org.jboss.logging.annotations.Param;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.quarkus.test.entity.Customer;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customer")
public class CustomerManagement {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello..This is Customer management..";
    }

    @POST
    @Path("/create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Customer createCustomer(@Param Customer customer) {

        Customer cust = new Customer(customer);
        cust.persist();

        return getCustomer(Long.valueOf(cust.custId));
    }

    @GET
    @Path("/get/{custId}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@PathParam Long custId) {

        /*Customer  cust = new Customer();
        cust.name = "ABC";
        cust.custId = String.valueOf(custId);
        cust.region = "India";
        cust.loyaltyPoints = 1000;
        cust.persist();*/

        Customer customer = Customer.findById(custId);
        if (customer == null) {
            throw new WebApplicationException("Customer with id of " + custId + " does not exist.", 404);
        }
        return customer;
    }

    @GET
    @Path("/count")
    @Transactional
    public long getCustomerCount() {

        return Customer.count();
    }

    @GET
    @Path("/filter/{region}")
    @Transactional
    public List<Customer> getRegionBasedCustomer(@PathParam String region) {

        return Customer.list("region", region);
    }
}