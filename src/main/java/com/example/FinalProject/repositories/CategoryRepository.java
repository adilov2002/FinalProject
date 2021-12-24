package com.example.FinalProject.repositories;

import com.example.FinalProject.entities.Categories;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Stateless
public class CategoryRepository {

    private Repository repository = new Repository();

    private Connection connection = repository.getConnection();

    public ArrayList<Categories> getAllCategories(){
        ArrayList<Categories> categories = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from t_categories");
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Long id = set.getLong("id");
                String name = set.getString("name");
                categories.add(new Categories(id, name));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return categories;
    }

    public Categories getCategoryById(Long id){
        Categories category = new Categories();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from t_categories where id = ? ");
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                category.setId(set.getLong("id"));
                category.setName(set.getString("name"));
            }
            statement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }
}
