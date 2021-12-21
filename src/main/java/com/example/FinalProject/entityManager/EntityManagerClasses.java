package com.example.FinalProject.entityManager;

import com.example.FinalProject.entities.Roles;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Ignore
public class EntityManagerClasses {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("final");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Roles role = entityManager.find(Roles.class, 2L);
        System.out.println(role);

    }
}
