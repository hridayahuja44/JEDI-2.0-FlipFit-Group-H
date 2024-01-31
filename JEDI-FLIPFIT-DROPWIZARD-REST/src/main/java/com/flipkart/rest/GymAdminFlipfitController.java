package com.flipkart.rest;

import com.flipkart.service.AdminFlipFitServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * RESTful API Controller for Gym Administration in FlipFit
 */
@Path("/v1/admin")
@Produces(MediaType.APPLICATION_JSON)
public class GymAdminFlipfitController {
    // Creating an instance of AdminFlipFitServiceImpl for handling business logic
    AdminFlipFitServiceImpl adminBusiness = new AdminFlipFitServiceImpl();

    /**
     * Retrieves a list of all gyms
     * @return Response with the list of Gym objects
     */
    @GET
    @Path("/viewGyms")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getGym() {
        try {
            return Response.ok(adminBusiness.getGym()).build();
        } catch (Exception exception) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }
    }

    /**
     * Retrieves a list of all gym owners
     * @return Response with the list of GymOwner objects
     */
    @GET
    @Path("/viewGymOwners")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getGymOwners() {
        try {
            return Response.ok(adminBusiness.getGymOwners()).build();
        } catch (Exception exception) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }
    }

    /**
     * Retrieves a list of all pending gym owner requests
     * @return Response with the list of pending GymOwner requests
     */
    @GET
    @Path("/viewPendingGymOwners")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response viewAllPendingGymOwnerRequests() {
        try {
            return Response.ok(adminBusiness.viewAllPendingGymOwnerRequests()).build();
        } catch (Exception exception) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }
    }

    /**
     * Retrieves a list of all pending gym requests
     * @return Response with the list of pending Gym requests
     */
    @GET
    @Path("/viewPendingGyms")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response viewAllPendingGymRequests() {
        try {
            return Response.ok(adminBusiness.viewAllPendingGymRequests()).build();
        } catch (Exception exception) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }
    }

    /**
     * Approves a single gym owner request
     * @param email The email of the gym owner to be approved
     * @return Response indicating the success or failure of the operation
     */
    @PUT
    @Path("/approveGymOwner/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response approveSingleGymOwnerRequest(@PathParam("email") String email) {
        try {
            adminBusiness.approveSingleGymOwnerRequest(email);
            return Response.ok("Successfully Approved " + email + "!").build();
        } catch (Exception exception) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }
    }

    /**
     * Approves all pending gym owner requests
     * @return Response indicating the success or failure of the operation
     */
    @PUT
    @Path("/approveGymOwners")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response approveAllPendingGymOwnerRequests() {
        try {
            adminBusiness.approveAllPendingGymOwnerRequests();
            return Response.ok("Successfully Approved all Gym Owners!").build();
        } catch (Exception exception) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }
    }

    /**
     * Approves a single gym request
     * @param gymId The ID of the gym to be approved
     * @return Response indicating the success or failure of the operation
     */
    @PUT
    @Path("/approveGym/{gymId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response approveSingleGymRequest(@PathParam("gymId") String gymId) {
        try {
            adminBusiness.approveSingleGymRequest(gymId);
            return Response.ok("Successfully Approved " + gymId + "!").build();
        } catch (Exception exception) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }
    }

    /**
     * Approves all pending gym requests
     * @return Response indicating the success or failure of the operation
     */
    @PUT
    @Path("/approveGyms")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response approveAllPendingGymRequests() {
        try {
            adminBusiness.approveAllPendingGymRequests();
            return Response.ok("Successfully Approved all Gym!").build();
        } catch (Exception exception) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
        }
    }
}
