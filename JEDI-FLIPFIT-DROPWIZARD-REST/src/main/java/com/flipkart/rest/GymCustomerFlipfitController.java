package com.flipkart.rest;

import com.codahale.metrics.annotation.Timed;
import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.exception.CustomerNotFoundException;
import com.flipkart.exception.SlotNotFoundException;
import com.flipkart.exception.UserAlreadyExistsException;
import com.flipkart.service.CustomerFlipFitServiceImpl;
import com.flipkart.service.UserFlipFitServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/v1/customers")
@Produces(MediaType.APPLICATION_JSON)
public class GymCustomerFlipfitController {
    CustomerFlipFitServiceImpl customerBusiness = new CustomerFlipFitServiceImpl();

    // Endpoint for registering a new customer
    @POST
    @Path("/registration")
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerCustomer(Customer customer) {
        UserFlipFitServiceImpl userBusiness = new UserFlipFitServiceImpl();
        try {
            userBusiness.registerCustomer(customer);
            return Response.ok("CUSTOMER HAS BEEN REGISTERED SUCCESSFULLY!").build();
        }  catch (UserAlreadyExistsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // Endpoint for editing customer profile
    @PUT
    @Path("/{email}/profile")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editProfile(@PathParam("email") String email, Customer customer) {
        try {
            customerBusiness.editProfile(customer);
            return Response.ok("THE PROFILE HAS BEEN SUCCESSFULLY UPDATED!").build();
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // Endpoint for getting gym details based on city
    @GET
    @Path("/{email}/book")
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGymDetails(@PathParam("email") String email, @QueryParam("city") String city) {
        List<Gym> gyms = customerBusiness.getGymInCity(city);
        if(gyms.size() == 0)
            return Response.status(Response.Status.BAD_REQUEST).entity("No gyms available").build();
        return Response.ok(gyms).build();
    }

    // Endpoint for viewing customer bookings
    @GET
    @Path("/{email}/bookings")
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewBookings(@PathParam("email") String email) {
        return Response.ok(customerBusiness.getBookings(email)).build();
    }

    // Endpoint for viewing customer profile
    @GET
    @Path("/{email}/profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewProfile(@PathParam("email") String email) {
        try {
            return Response.ok(customerBusiness.getProfile(email)).build();
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // Endpoint for canceling a booking
    @PUT
    @Path("/{email}/bookings/cancel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelBooking(@PathParam("email") String email, @QueryParam("bookingId") String bookingId) {
        return Response.ok(customerBusiness.cancelBooking(bookingId, email)).build();
    }

    // Endpoint for viewing available slots in a gym
    @GET
    @Path("/{email}/book/{gymId}")
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewSlots(@PathParam("email") String email, @QueryParam("city") String city, @PathParam("gymId") String gymId) {
        try {
            List<Slot> slots = customerBusiness.getSlotInGym(gymId);
            return Response.ok(slots).build();
        } catch (SlotNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // Endpoint for booking a slot
    @POST
    @Path("/{email}/book")
    @Timed
    public Response bookSlot(@PathParam("email") String email,
                             @QueryParam("gymId") String gymId,
                             @QueryParam("date") String date,
                             @QueryParam("slotId") String slotId) {
        int bookingResponse = customerBusiness.bookSlot(gymId, slotId, email, date);

        String responseMessage;
        switch (bookingResponse) {
            case 0:
                responseMessage = "CANCELLING PREVIOUS SLOT AND BOOKING THE NEW SLOT";
                break;
            case 1:
                responseMessage = "SLOTS ARE FULL, YOU'VE BEEN ADDED TO WAITING LIST";
                break;
            case 2:
                responseMessage = "SLOT IS BOOKED!";
                break;
            default:
                responseMessage = "BOOKING HAS BEEN FAILED";
        }

        return Response.ok(responseMessage).build();
    }
}
