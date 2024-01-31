package com.flipkart.rest;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * Main controller class for the Flipfit application.
 * Extends the Dropwizard Application class.
 */
public class FlipfitController extends Application<FlipfitControllerConfiguration> {

    /**
     * Main method to start the Flipfit application.
     * @param args Command-line arguments
     * @throws Exception Exception thrown if an error occurs during application startup
     */
    public static void main(String[] args) throws Exception {
        new FlipfitController().run(args);
    }

    /**
     * Run method of the Dropwizard application.
     * Registers resource classes with the Jersey environment.
     * @param flipfitControllerConfiguration Configuration for the FlipfitController
     * @param environment Dropwizard environment
     * @throws Exception Exception thrown if an error occurs during application runtime
     */
    @Override
    public void run(FlipfitControllerConfiguration flipfitControllerConfiguration, Environment environment) throws Exception {
        // Registering resource classes with Jersey environment
        environment.jersey().register(GymUserFlipfitCustomer.class);
        environment.jersey().register(GymAdminFlipfitController.class);
        environment.jersey().register(GymCustomerFlipfitController.class);
        environment.jersey().register(GymOwnerFlipfitController.class);
    }
}
