package com.sebastian.apicriteria.usoapicriteria;

import java.util.List;

import com.sebastian.apicriteria.entity.Cliente;
import com.sebastian.apicriteria.jpaconnect.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class HibernateCriteriaOrYAnd {
    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> query;
        Root<Cliente> from;

        /* Para la utilizacion del operador OR o el operador AND es necesario colocar las condiciones en objetos de tipo
         * Predicate para luego estas almacenarlas en el método or o and de criteria.
         */

        System.out.println("------------------ UTILIZACION DE OPERADOR OR -------------------");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        
        Predicate con1 = criteria.equal(from.get("nombre"), "melanee");
        Predicate con2 = criteria.equal(from.get("apellido"), "fuentes");
        Predicate con3 = criteria.equal(from.get("id"), 16);
        query.select(from).where(criteria.or(con1, con2, con3));

        List<Cliente> lista = em.createQuery(query).getResultList();
        lista.forEach(System.out::println);



        System.out.println("------------------ UTILIZACION DE OPERADOR AND -------------------");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        con1 = criteria.equal(from.get("nombre"), "melanee");
        con2 = criteria.equal(from.get("apellido"), "ramos");
        con3 = criteria.lt(from.get("id"), 3); // Que el id sea menor a tres
        query.select(from).where(criteria.and(con1, con2, con3));
        
        lista = em.createQuery(query).getResultList();
        lista.forEach(System.out::println);



        System.out.println("-------------------------------------");
        lista = em.createQuery("select c from Cliente c where c.nombre = :nombre1 or c.nombre = :nombre2", Cliente.class)
        .setParameter("nombre1", "sebastian")
        .setParameter("nombre2", "kym")
        .getResultList();
        lista.forEach(System.out::println);


        
    }
}