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
@RolesAllowed({"ADMIN", "USER"})
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
    @RolesAllowed("ADMIN")
    public String addPlace(Places place){
        return service.addNewPlace(place) ? messageService.sendJmsLoggingMessage(place) : "failed adding place";
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
