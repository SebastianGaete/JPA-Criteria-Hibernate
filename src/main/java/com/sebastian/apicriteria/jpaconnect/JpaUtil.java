package com.sebastian.apicriteria.jpaconnect;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil{

    private static EntityManagerFactory entityManager = buildEntityManager();

    private static EntityManagerFactory buildEntityManager(){
        return Persistence.createEntityManagerFactory("ejemploJPACriteria");
    }

    public static EntityManager getEntityManager(){
        return entityManager.createEntityManager();
    }

}