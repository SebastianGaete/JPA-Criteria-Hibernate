package com.sebastian.apicriteria.usoapicriteria;

import java.util.List;
import com.sebastian.apicriteria.entity.Cliente;
import com.sebastian.apicriteria.jpaconnect.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HibernateCriteriaMultiSelect {
    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder criteria = em.getCriteriaBuilder();


        System.out.println("----------------- consulta MULTI SELECT ------------------ ");

        CriteriaQuery<Object[]> query = criteria.createQuery(Object[].class);
        Root<Cliente> from = query.from(Cliente.class);

        query.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"))
        .orderBy(criteria.desc(from.get("id")));

        List<Object[]> lista = em.createQuery(query).getResultList();
        lista.forEach(obj ->{
            Integer id = (Integer) obj[0];
            String nombre = (String) obj[1];
            String apellido = (String) obj[2];
            System.out.println(id +" " + nombre + " " + apellido);
        });


        System.out.println("----------------- consulta MULTI SELECT CON LIKE ------------------ ");
        query = criteria.createQuery(Object[].class);
        from = query.from(Cliente.class);

        query.multiselect(from.get("nombre"), from.get("formaPago"))
        .where(criteria.like(from.get("nombre"), "%u%"));

        lista = em.createQuery(query).getResultList();
        lista.forEach(obj ->{
            String nombre = (String) obj[0];
            String pago = (String) obj[1];
            System.out.println(nombre +" " + pago );
        });


    }
}