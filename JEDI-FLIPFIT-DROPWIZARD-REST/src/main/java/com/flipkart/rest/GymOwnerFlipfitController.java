package com.flipkart.rest;

import com.codahale.metrics.annotation.Timed;
import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.exception.GymNotFoundException;
import com.flipkart.exception.GymOwnerNotFoundException;
import com.flipkart.exception.UnauthorizedAccessException;
import com.flipkart.exception.UserAlreadyExistsException;
import com.flipkart.service.GymOwnerFlipFitServiceImpl;
import com.flipkart.service.UserFlipFitServiceImpl;
import com.flipkart.utils.IdGenerator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Controller class for handling Gym Owner related operations in FlipFit application.
 */
@Path("/v1/gymowners")
@Produces(MediaType.APPLICATION_JSON)
public class GymOwnerFlipfitController {
    GymOwnerFlipFitServiceImpl gymOwnerBusiness = new GymOwnerFlipFitServiceImpl();

    /**
     * Registers a new Gym Owner.
     *
     * @param gymOwner The GymOwner object to be registered.
     * @return Response indicating the success or failure of the registration process.
     */
    @POST
    @Path("/registration")
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerGymOwner(GymOwner gymOwner) {
        UserFlipFitServiceImpl userBusiness = new UserFlipFitServiceImpl();
        try {
            userBusiness.registerGymOwner(gymOwner);
            return Response.ok("GYM OWNER HAS BEEN REGISTERED!").build();
        } catch (UserAlreadyExistsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Edits the profile of a Gym Owner.
     *
     * @param email     The email of the Gym Owner whose profile needs to be edited.
     * @param gymOwner  The updated GymOwner object.
     * @return Response indicating the success or failure of the profile editing process.
     */
    @PUT
    @Path("/{email}/profile")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editProfile(@PathParam("email") String email, GymOwner gymOwner) {
        try {
            gymOwnerBusiness.editProfile(gymOwner);
            return Response.ok("YOUR PROFILE IS SUCCESSFULLY EDITED!").build();
        } catch (GymOwnerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /**
     * Retrieves the profile of a Gym Owner.
     *
     * @param email The email of the Gym Owner.
     * @return Response containing the Gym Owner's profile information.
     */
    @GET
    @Path("/{email}/profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewProfile(@PathParam("email") String email) {
        try {
            return Response.ok(gymOwnerBusiness.getProfile(email)).build();
        } catch (GymOwnerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /**
     * Adds a new Gym to a Gym Owner's account.
     *
     * @param email The email of the Gym Owner.
     * @param gym   The Gym object to be added.
     * @return Response indicating the success or failure of the Gym addition process.
     */
    @POST
    @Path("/{email}/addGym")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGym(@PathParam("email") String email, Gym gym) {
        gym.setGymId(IdGenerator.generateId("Gym"));
        gymOwnerBusiness.addGym(gym);
        return Response.ok("Gym Added Successfully").build();
    }

    /**
     * Edits the details of an existing Gym.
     *
     * @param email The email of the Gym Owner.
     * @param gym   The updated Gym object.
     * @return Response indicating the success or failure of the Gym editing process.
     */
    @PUT
    @Path("/{email}/editGym")
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editGym(@PathParam("email") String email, Gym gym) {
        try {
            gymOwnerBusiness.editGym(gym);
            return Response.ok("Gym edited successfully!").build();
        } catch (GymNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /**
     * Retrieves details of all Gyms associated with a Gym Owner.
     *
     * @param email The email of the Gym Owner.
     * @return Response containing the details of all Gyms associated with the Gym Owner.
     */
    @GET
    @Path("/{email}/gyms")
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGymDetails(@PathParam("email") String email) {
        return Response.ok(gymOwnerBusiness.getGymDetail(email)).build();
    }

    /**
     * Adds a new Slot to a Gym Owner's Gym.
     *
     * @param slot  The Slot object to be added.
     * @param email The email of the Gym Owner.
     * @return Response indicating the success or failure of the Slot addition process.
     */
    @POST
    @Path("/{email}/addSlot")
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSlot(Slot slot, @PathParam("email") String email) {
        try {
            slot.setSlotId(IdGenerator.generateId("Slot"));
            gymOwnerBusiness.addSlot(slot, email);
            return Response.ok("Slot added successfully!").build();
        } catch (GymNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (UnauthorizedAccessException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
}
