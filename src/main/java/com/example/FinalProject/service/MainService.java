package com.example.FinalProject.service;

import com.example.FinalProject.entities.Categories;
import com.example.FinalProject.entities.Places;
import com.example.FinalProject.repositories.CategoryRepository;
import com.example.FinalProject.repositories.PlacesRepository;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.ArrayList;

@Stateful
public class MainService {

    @EJB
    private CategoryRepository categoryRepository;

    @EJB
    private PlacesRepository placesRepository;

    public ArrayList<Places> getAllPlaces(){
        return placesRepository.getAllPlaces();
    }

    public Boolean addNewPlace(Places place){
        return placesRepository.addPlace(place);
    }

    public ArrayList<Categories> getAllCategories(){
        return categoryRepository.getAllCategories();
    }

}
