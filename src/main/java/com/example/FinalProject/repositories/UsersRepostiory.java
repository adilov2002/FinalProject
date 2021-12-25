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

    public boolean addUser(Users user){
        int res = 0;
        boolean addGroups = false;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "insert into t_users values (?, ?, ?, ?)");
            statement.setLong(1, user.getId());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUsername());
            statement.setLong(4, user.getRole().getId());

            res = statement.executeUpdate();

            addGroups = usersGroupsRepository.addGroupsForUserId(user.getId(), user.getGroups());

            statement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return res > 0 && addGroups;
    }


    public boolean deleteUserById(Long id){
        int res = 0;
        try {
            if (usersGroupsRepository.deleteUser(id)) {
                PreparedStatement statement = connection.prepareStatement("" +
                        "delete from t_users where id = ? ");
                statement.setLong(1, id);

                res = statement.executeUpdate();

                statement.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return res > 0;
    }

    public boolean updateUser(Users user){
        int res = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "update t_users set password = ?, username = ?, role_id = ? where id = ? ");
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getUsername());
            statement.setLong(3, user.getRole().getId());
            statement.setLong(4, user.getId());

            res = statement.executeUpdate();

            statement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return res > 0;
    }
}
