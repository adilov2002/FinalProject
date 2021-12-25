package com.example.FinalProject.repositories;

import com.example.FinalProject.entities.NewsType;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Stateless
public class NewsTypeRepository {

    private Repository repository = new Repository();

    private Connection connection = repository.getConnection();

    public NewsType getNewsTypeById(Long id){
        NewsType newsType = new NewsType();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from t_newstype where id = ?");
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                newsType.setId(set.getLong("id"));
                newsType.setType(set.getString("type"));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return newsType;
    }
}
