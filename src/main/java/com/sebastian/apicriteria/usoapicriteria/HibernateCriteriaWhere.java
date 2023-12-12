package com.sebastian.apicriteria.usoapicriteria;

import java.util.List;

import com.sebastian.apicriteria.entity.Cliente;
import com.sebastian.apicriteria.jpaconnect.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

public class HibernateCriteriaWhere {
    public static void main(String[] args) {

        /* La utilización de la Api Criteria es más que nada otro forma de utilizar JPA de forma programática */

        EntityManager em = JpaUtil.getEntityManager();

        /* Para utilizar el API Criteria debemos crear un Builder por medio del EntityManager */
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        
        System.out.println("------------------  SELECT * FROM --------------------");
        /* Posteriormente creamos un objeto de tipo CriteriaQuery el cual será de la clase entity que deseemos utilizar */
        CriteriaQuery<Cliente> query = criteria.createQuery(Cliente.class);

        /* El Objeto Root<entity> sería el from  */
        Root<Cliente> from = query.from(Cliente.class);

        /* Por ultimo utilizamos el método select pasando por parámetro el from */
        query.select(from);

        /* Resta solamente crear la query en el "em" obteniendo un resultList() */
        List<Cliente> listaClientes = em.createQuery(query).getResultList();

        listaClientes.forEach(System.out::println);
        

        System.out.println("------------------  SELECT CON WHERE --------------------");

        CriteriaQuery<Cliente> query2 = criteria.createQuery(Cliente.class);
        Root<Cliente> from2 = query2.from(Cliente.class);
        query2.select(from2).where(criteria.equal(from2.get("nombre"), "Sebastián"));
        listaClientes = em.createQuery(query2).getResultList();
        listaClientes.forEach(System.out::println);
        

        System.out.println("------------------  SELECT CON WHERE Y PARAMETRO --------------------");

        query2 = criteria.createQuery(Cliente.class);
        from2 = query2.from(Cliente.class);
        ParameterExpression<String> param = criteria.parameter(String.class, "nombre");
        
        query2.select(from2).where(criteria.equal(from2.get("nombre"), param));
        listaClientes = em.createQuery(query2).setParameter("nombre","Melanee").getResultList();
        listaClientes.forEach(System.out::println);


        

        String cliente = em.createQuery("select concat(c.nombre, ' ',  c.apellido) from Cliente c where length(c.nombre) = (select max(length(c.nombre)) from Cliente c) ", String.class)
        .setMaxResults(1)
        .getSingleResult();
        System.out.println(cliente);
    }
}