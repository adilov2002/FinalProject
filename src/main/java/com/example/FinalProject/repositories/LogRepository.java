package com.example.FinalProject.repositories;

import com.example.FinalProject.entities.CustomLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LogRepository {

    private Repository repository = new Repository();

    private Connection connection = repository.getConnection();


    public ArrayList<CustomLog> getAllCustomLogs(){
        ArrayList<CustomLog> customLogs = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from t_customlog ");
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Long id = set.getLong("id");
                String log = set.getString("log");
                customLogs.add(new CustomLog(id, log));
            }
        } catch (Exception ignored){}
        return customLogs;
    }

    public boolean addCustomLog(CustomLog customLog){
        int res = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "insert into t_suctomlog (log) values (?) ");
            statement.setString(1, customLog.getLog());

            res = statement.executeUpdate();

            statement.close();
        } catch (Exception ignored){}
        return res > 0;
    }

}
