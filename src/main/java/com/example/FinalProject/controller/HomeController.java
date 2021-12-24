package com.example.FinalProject.controller;

import com.example.FinalProject.entities.Categories;
import com.example.FinalProject.entities.CustomLog;
import com.example.FinalProject.entities.Places;
import com.example.FinalProject.service.MainService;
import com.example.FinalProject.service.MessageService;
import lombok.SneakyThrows;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.ArrayList;

@Path(value = "/")
@RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
public class HomeController {

    @EJB
    MainService service;

    @EJB
    MessageService messageService;


    @GET
    @Produces(value = "application/json")
    @Path(value = "/places")
    @PermitAll
    public ArrayList<Places> allPlaces(){
        return service.getAllPlaces();
    }

    @POST
    @Produces(value = "plain/text")
    @Path(value = "/places")
    @RolesAllowed("ROLE_ADMIN")
    public String addPlace(Places place){
        return service.addNewPlace(place) ? messageService.sendJmsLoggingMessage(place, "add") : "failed adding place";
    }

    @DELETE
    @Produces(value = "plain/text")
    @Path(value = "/places/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public String deletePlace(@PathParam("id") Long id){
        return service.deletePlace(id) ? messageService.sendJmsLoggingMessage(id, "delete") : "failed deleting place";
    }

    @PUT
    @Produces(value = "plain/text")
    @Path(value = "/places")
    @RolesAllowed("ROLE_ADMIN")
    public String updatePlace(Places place){
        return service.updatePlace(place) ? messageService.sendJmsLoggingMessage(place, "update") : "failed updating place";
    }

    @SneakyThrows
    @GET
    @Produces(value = "plain/text")
    @Path(value = "/messages")
    @PermitAll
    public String getAllMessages(){
        return messageService.getMessage();
    }

    @SneakyThrows
    @GET
    @Produces(value = "plain/text")
    @Path(value = "/messages")
    @PermitAll
    public ArrayList<CustomLog> getAllLogs(){
        return messageService.getAllLogs();
    }

    @GET
    @Produces(value = "application/json")
    @Path(value = "/categories")
    public ArrayList<Categories> allCategories(){
        return service.getAllCategories();
    }

}
