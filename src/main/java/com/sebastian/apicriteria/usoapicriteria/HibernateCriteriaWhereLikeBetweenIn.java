package com.sebastian.apicriteria.usoapicriteria;

import java.util.List;

import com.sebastian.apicriteria.entity.Cliente;
import com.sebastian.apicriteria.jpaconnect.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HibernateCriteriaWhereLikeBetweenIn {
    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder criteria = em.getCriteriaBuilder();

        
        System.out.println("------------- BUSCAR FORMA PAGO CON WHERE LIKE ------------------");
        CriteriaQuery<Cliente> query = criteria.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);

        /* En el método like como primer parámetro colocamos el atributo con el cual trabajaremos y como segundo la cadena de carácteres */
        query.select(from).where(criteria.like(from.get("formaPago"), "%at%"));
        
        List<Cliente> lista = em.createQuery(query).getResultList();
        lista.forEach(System.out::println);


        System.out.println("------------- BUSCAR FORMA PAGO CON WHERE BETWEEN ------------------");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        /* En el método Between pasamos comoprimer parámetro el atributo numérico o de fecha, y los demas parámetros son los rangos
         * en los cuales estaremos buscando.
         */
        query.select(from).where(criteria.between(from.get("id"), 5, 10));
        lista = em.createQuery(query).getResultList();
        lista.forEach(System.out::println); 


        System.out.println("------------- BUSCAR FORMA PAGO CON WHERE IN ------------------");
        
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        /* en el where pasamos el atributo con el que trabajaremos y utilizamos a la vez en el método IN para seleccionar valores */
        query.select(from).where(from.get("formaPago").in("match", "credito"));
        lista = em.createQuery(query).getResultList();
        lista.forEach(System.out::println);

        

        em.close();
    }
}