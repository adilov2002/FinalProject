package com.example.FinalProject.repositories;

import java.sql.Connection;
import java.sql.DriverManager;

public class Repository {

    private Connection connection;

    public Repository(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/final", "postgres", "postgres");
            System.out.println("connected");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
