package com.example.FinalProject.repositories;

import com.example.FinalProject.entities.Roles;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Stateless
public class RolesRepository {

    private Repository repository = new Repository();

    private Connection connection = repository.getConnection();

    public Roles getRoleById(Long id){
        Roles role = new Roles();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from t_roles where id = ? ");
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                role.setId(set.getLong("id"));
                role.setName(set.getString("name"));
            }
            statement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return role;
    }
}
