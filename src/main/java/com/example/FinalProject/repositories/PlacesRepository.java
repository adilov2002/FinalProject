package com.example.FinalProject.repositories;

import com.example.FinalProject.entities.Categories;
import com.example.FinalProject.entities.Places;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Stateless
public class PlacesRepository {

    private Repository repository = new Repository();

    @EJB
    private CategoryRepository categoryRepository;

    private Connection connection = repository.getConnection();

    public ArrayList<Places> getAllPlaces(){
        ArrayList<Places> places = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from t_places");
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Long id = set.getLong("id");
                String address = set.getString("address");
                String description = set.getString("description");
                String name = set.getString("name");
                Long categoryId = set.getLong("category_id");
                Categories category = categoryRepository.getCategoryById(categoryId);
                places.add(new Places(id, name, address, description, category));
            }
        }catch (Exception ignored){}
        return places;
    }

    public boolean addPlace(Places place) {
        int res = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "insert into t_places (id, address, description, name, category_id) values (DEFAULT, ?, ?, ?, ?) ");
            statement.setString(1, place.getAddress());
            statement.setString(2, place.getDescription());
            statement.setString(3, place.getName());
            statement.setLong(4, place.getCategory().getId());

            res = statement.executeUpdate();

            statement.close();
        } catch (Exception ignored) { }
        return res > 0;
    }

}
