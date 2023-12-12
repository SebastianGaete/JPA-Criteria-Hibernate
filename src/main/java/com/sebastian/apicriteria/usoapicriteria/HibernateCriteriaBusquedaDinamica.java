package com.sebastian.apicriteria.usoapicriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sebastian.apicriteria.entity.Cliente;
import com.sebastian.apicriteria.jpaconnect.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class HibernateCriteriaBusquedaDinamica {
    public static void main(String[] args) {
        
        /* Obtenemos la data de los filtros */
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Filtro por apellido: ");
        String apellido = sc.nextLine();
        
        System.out.print("Filtro por nombre: ");
        String nombre = sc.nextLine();
        

        /* Creamos el EntityManager y el CriteriaBuilder */
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder criteria = em.getCriteriaBuilder();

        CriteriaQuery<Cliente> query = criteria.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);

        /* Establecemos una lista con los predicados o condiciones para buscar y validamos conforme a nuestro criterio */
        List<Predicate> condiciones = new ArrayList<>();

        if(nombre != null && !nombre.isEmpty()){
            condiciones.add(criteria.equal(from.get("nombre"), nombre));
        }
        
        if(apellido != null && !apellido.isEmpty()){
            condiciones.add(criteria.equal(from.get("apellido"), apellido));
        }
        
        /* Por ultimo en la Query utilizamos el and en donde creamos un array de predicados con la cantidad de elementos
         * de la Lista de condiciones.
         */
        query.select(from).where(criteria.and(condiciones.toArray(new Predicate[condiciones.size()])));

        List<Cliente> lista = em.createQuery(query).getResultList();
        lista.forEach(System.out::println);

        sc.close();
        em.close();

    }
}