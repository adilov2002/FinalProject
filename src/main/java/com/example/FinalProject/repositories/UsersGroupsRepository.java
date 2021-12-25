package com.example.FinalProject.repositories;

import com.example.FinalProject.entities.Groups;
import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentParser;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UsersGroupsRepository {

    private Repository repository = new Repository();

    private Connection connection = repository.getConnection();

    public List<Groups> getGroupsByUserId(Long userId){
        List<Groups> groups = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select id, name from t_groups inner join t_users_t_groups tutg on t_groups.id = tutg.groups_id where tutg.users_id = ? ");
            statement.setLong(1, userId);
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                Long groupId = Long.valueOf(set.getString("id"));
                String name = set.getString("name");
                groups.add(new Groups(groupId, name));
            }

        } catch (Exception ignores){}
        return groups;
    }

    public boolean addGroupsForUserId(Long userId, List<Groups> groups){
        int res = 0;
        try {
            for (Groups g : groups){
                PreparedStatement statement = connection.prepareStatement("" +
                        "insert into t_users_t_groups values (?, ?) ");
                statement.setLong(1, userId);
                statement.setLong(2, g.getId());

                res = statement.executeUpdate();

                statement.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return res > 0;
    }

    public boolean deleteUser(Long id){
        int res = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "delete from t_users_t_groups where users_id = ? ");
            statement.setLong(1, id);

            res = statement.executeUpdate();

            statement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return res > 0;
    }

}
