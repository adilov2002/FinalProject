package com.example.FinalProject.service;

import com.example.FinalProject.entities.Categories;
import com.example.FinalProject.entities.News;
import com.example.FinalProject.entities.Places;
import com.example.FinalProject.entities.Users;
import com.example.FinalProject.repositories.*;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.ArrayList;

@Stateful
public class MainService {

    @EJB
    private CategoryRepository categoryRepository;

    @EJB
    private PlacesRepository placesRepository;

    @EJB
    private UsersRepostiory usersRepostiory;

    @EJB
    private NewsRepository newsRepository;

    public ArrayList<Places> getAllPlaces(){
        return placesRepository.getAllPlaces();
    }

    public Boolean addNewPlace(Places place){
        return placesRepository.addPlace(place);
    }

    public Boolean deletePlace(Long id){
        return placesRepository.deletePlaceById(id);
    }

    public Boolean updatePlace(Places place){
        return placesRepository.updatePlace(place);
    }

    public ArrayList<Users> getAllUsers(){
        return usersRepostiory.getAllUsers();
    }

    public Boolean addNewUser(Users user){
        return usersRepostiory.addUser(user);
    }

    public Boolean deleteUser(Long id){
        return usersRepostiory.deleteUserById(id);
    }

    public Boolean updateUser(Users user){
        return usersRepostiory.updateUser(user);
    }


    public ArrayList<Categories> getAllCategories(){
        return categoryRepository.getAllCategories();
    }

    public ArrayList<Places> getPlacesByCategoryId(Long id){
        return placesRepository.getPlacesByCategoryId(id);
    }

    public ArrayList<News> getAllNews(){
        return newsRepository.getAllNews();
    }

    public ArrayList<News> getNewsByTypeId(Long id){
        return newsRepository.getNewsByTypeId(id);
    }
}
