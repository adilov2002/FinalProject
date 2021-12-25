package com.example.FinalProject.controller;

import com.example.FinalProject.entities.*;
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

    @GET
    @Produces(value = "application/json")
    @Path(value = "/users")
    @RolesAllowed("ROLE_ADMIN")
    public ArrayList<Users> allUsers(){
        return service.getAllUsers();
    }

    @POST
    @Produces(value = "plain/text")
    @Path(value = "/users")
    @RolesAllowed("ROLE_ADMIN")
    public String addUser(Users user){
        return service.addNewUser(user) ? messageService.sendJmsLoggingMessage(user, "addUser") : "failed adding user";
    }

    @DELETE
    @Produces(value = "plain/text")
    @Path(value = "/users/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public String deleteUser(@PathParam("id") Long id){
        return service.deleteUser(id) ? messageService.sendJmsLoggingMessage(id, "deleteUser") : "failed deleting user";
    }

    @PUT
    @Produces(value = "plain/text")
    @Path(value = "/users")
    @RolesAllowed("ROLE_ADMIN")
    public String updateUser(Users user){
        return service.updateUser(user) ? messageService.sendJmsLoggingMessage(user, "updateUser") : "failed updating user";
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
    @Produces(value = "application/json")
    @Path(value = "/logs")
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

    // universities
    // news
    // hospitals
    // hotels
    // vacancies

    @GET
    @Produces(value = "application/json")
    @Path(value = "/place-category/{id}")
    @PermitAll
    public ArrayList<Places> getPlacesByCategory(@PathParam("id") Long id){
        return service.getPlacesByCategoryId(id);
    }

    @GET
    @Produces(value = "application/json")
    @Path(value = "/news")
    @PermitAll
    public ArrayList<News> getAllNews(){
        return service.getAllNews();
    }

    @GET
    @Produces(value = "application/json")
    @Path(value = "/news/{id}")
    @PermitAll
    public ArrayList<News> getNewsByTypeId(@PathParam("id") Long id){
        return service.getNewsByTypeId(id);
    }

}
