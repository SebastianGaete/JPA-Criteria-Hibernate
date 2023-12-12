package com.sebastian.apicriteria.usoapicriteria;

import java.util.List;

import com.sebastian.apicriteria.entity.Cliente;
import com.sebastian.apicriteria.jpaconnect.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HibernateCriteriaOperadores {
    public static void main(String[] args) {
        

        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> query;
        Root<Cliente> from;


        System.out.println("----------------- UTILIZACION OPERADOR gt (mayor que) NOMBRE CON MAYOR DE 6 LETRAS ---------------");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).where(criteria.ge(criteria.length(from.get("nombre")), 6) );

        List<Cliente> lista = em.createQuery(query).getResultList();
        lista.forEach(System.out::println);


        System.out.println("----------------- UTILIZACION OPERADOR ge (mayor que o igual) ID IGUAL O MAYOR A 14 ---------------");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).where(criteria.ge(from.get("id"), 14));
        lista = em.createQuery(query).getResultList();
        lista.forEach(System.out::println);


        System.out.println("----------------- UTILIZACION OPERADOR lt (menor que) NOMBRE CON MENOS DE 4 LETRAS ---------------");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).where(criteria.lt(criteria.length(from.get("nombre")),4));
        lista = em.createQuery(query).getResultList();
        lista.forEach(System.out::println);

        
        System.out.println("----------------- UTILIZACION OPERADOR le (menor o igual que) NOMBRE CON 4 O MENOSLETRAS ---------------");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).where(criteria.le(criteria.length(from.get("nombre")),4));
        lista = em.createQuery(query).getResultList();
        lista.forEach(System.out::println);
    }
}