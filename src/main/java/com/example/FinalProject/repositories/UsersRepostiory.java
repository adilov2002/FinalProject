package com.example.FinalProject.repositories;

import com.example.FinalProject.entities.Groups;
import com.example.FinalProject.entities.Roles;
import com.example.FinalProject.entities.Users;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UsersRepostiory {

    private Repository repository = new Repository();

    private RolesRepository rolesRepository = new RolesRepository();

    private UsersGroupsRepository usersGroupsRepository = new UsersGroupsRepository();

    private Connection connection = repository.getConnection();

    public ArrayList<Users> getAllUsers(){
        ArrayList<Users> users = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from t_users");
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Long id = set.getLong("id");
                String password = set.getString("password");
                String username = set.getString("username");
                Long role_id = set.getLong("role_id");
                Roles role = rolesRepository.getRoleById(role_id);
                List<Groups> groups = usersGroupsRepository.getGroupsByUserId(id);
                users.add(new Users(id, password, username, role, groups));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
}
