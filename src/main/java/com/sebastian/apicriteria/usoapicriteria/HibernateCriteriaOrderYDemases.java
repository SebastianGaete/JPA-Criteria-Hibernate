package com.sebastian.apicriteria.usoapicriteria;

import java.util.List;

import com.sebastian.apicriteria.entity.Cliente;
import com.sebastian.apicriteria.jpaconnect.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HibernateCriteriaOrderYDemases {
    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> query;
        Root<Cliente> from;
        List<Cliente> lista;

        System.out.println("----------------- ORDER BY ASC (ascedente) ---------------------");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.like(from.get("apellido"), "%s%"))

        .orderBy(criteria.asc(from.get("apellido")));

        lista = em.createQuery(query).getResultList();
        lista.forEach(System.out::println);
        

        System.out.println("----------------- ORDER BY de DESC (descedente) ---------------------");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).orderBy(criteria.desc(from.get("id")));

        lista = em.createQuery(query).getResultList();
        lista.forEach(System.out::println);
        

        System.out.println("----------------- ORDER BY de DESC (descedente) SOLO NOMBRES ---------------------");

        CriteriaQuery<String> queryNombre = criteria.createQuery(String.class);
        from = queryNombre.from(Cliente.class);
        queryNombre.select(from.get("nombre")).orderBy(criteria.desc(from.get("id")));

        List<String> listaNombres= em.createQuery(queryNombre).getResultList();
        listaNombres.forEach(System.out::println);
        


        System.out.println("------------------- LISTA DE FORMA PAGO ÚNICOS USANDO DISTINCT -----------------");

        queryNombre = criteria.createQuery(String.class);
        from = queryNombre.from(Cliente.class);

        queryNombre.select(from.get("formaPago")).distinct(true);
        listaNombres = em.createQuery(queryNombre).getResultList();
        listaNombres.forEach(System.out::println);


        System.out.println("------------------ APELLIDOS EN UPPER -----------------");

        CriteriaQuery<String> queryApellidos = criteria.createQuery(String.class);
        from = queryApellidos.from(Cliente.class);

        queryApellidos.select(criteria.upper(from.get("apellido"))).distinct(true);
        List<String> listaApellidos = em.createQuery(queryApellidos).getResultList();
        listaApellidos.forEach(System.out::println);



        System.out.println("-------------------- FUNCION CONCAT ---------------------");
        CriteriaQuery<String> queryConcat = criteria.createQuery(String.class);
        from = queryConcat.from(Cliente.class);

        queryConcat.select(criteria.concat(criteria.concat(from.get("apellido"), " "), from.get("nombre")));
        

        List<String> listaConcat = em.createQuery(queryConcat).getResultList();
        listaConcat.forEach(System.out::println);



        



        List<Object[]> ls = em.createQuery("select c.nombre, c.apellido, c.formaPago from Cliente c", Object[].class)
        .getResultList();

        ls.forEach(obj ->{
            String nombre = (String) obj[0];
            String apellido = (String) obj[1];
            String pago = (String) obj[2];
            System.out.println(nombre + " " + apellido + " " + pago);
        });
        
    }
}