package com.flipkart.rest;

import com.codahale.metrics.annotation.Timed;
import com.flipkart.bean.Customer;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;
import com.flipkart.exception.UserAlreadyExistsException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.UserFlipFitServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class GymUserFlipfitCustomer {
    UserFlipFitServiceImpl userBusiness = new UserFlipFitServiceImpl();

    // Endpoint for displaying menu
    @GET
    @Path("/")
    @Timed
    public Response menu() {
        // Display menu options
        String menu = "1. CUSTOMER REGISTRATION\n2. GYM OWNER REGISTRATION\n3. LOGIN";
        return Response.ok(menu).build();
    }

    // Endpoint for customer registration
    @POST
    @Path("/customerRegistration")
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerCustomer(Customer customer) {
        try {
            // Attempt to register the customer
            userBusiness.registerCustomer(customer);
            return Response.ok("CUSTOMER HAS BEEN REGISTERED SUCCESSFULLY!").build();
        } catch (UserAlreadyExistsException e) {
            // Handle the case where customer registration fails due to existing user
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // Endpoint for gym owner registration
    @POST
    @Path("/gymOwnerRegistration")
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerGymOwner(GymOwner gymOwner) {
        try {
            // Attempt to register the gym owner
            userBusiness.registerGymOwner(gymOwner);
            return Response.ok("GYM OWNER HAS BEEN REGISTERED SUCCESSFULLY!").build();
        } catch (UserAlreadyExistsException e) {
            // Handle the case where gym owner registration fails due to existing user
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // Endpoint for user authentication
    @GET
    @Path("/login/{email}")
    public Response authorizeUser(@PathParam("email") String email, User user) {
        try {
            // Attempt to authenticate the user
            userBusiness.authenticateUser(user);
            return Response.ok("USER HAS BEEN AUTHENTICATED SUCCESSFULLY!").build();
        } catch (UserNotFoundException e) {
            // Handle the case where user authentication fails due to user not found
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // Endpoint for user logout
    @GET
    @Path("/logout/{email}")
    public Response LogOut(@PathParam("email") String email, User user) {
        // Perform user logout
        userBusiness.logout(user);
        return Response.ok("USER HAS BEEN LOGGED OUT SUCCESSFULLY!").build();
    }
}
