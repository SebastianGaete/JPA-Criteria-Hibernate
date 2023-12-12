package com.sebastian.apicriteria.usoapicriteria;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import com.sebastian.apicriteria.entity.Cliente;
import com.sebastian.apicriteria.jpaconnect.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

public class HibernateCriteriaFuncionesAgregacion {
    public static void main(String[] args) {
        

        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        Root<Cliente> from;


        System.out.println(" -------------------  FUNCION AGREGACION COUNT -----------------");
        CriteriaQuery<Long> queryCount = criteria.createQuery(Long.class);
        from = queryCount.from(Cliente.class);

        ParameterExpression<String> param = criteria.parameter(String.class, "param"); 

        queryCount.select(criteria.count(from)).where(criteria.equal(from.get("nombre"), param));

        Long cantidad = em.createQuery(queryCount).setParameter("param", "sebastian").getSingleResult();
        System.out.println("Nombres identicos a sebastian: " + cantidad);



        System.out.println(" -------------------  FUNCION AGREGACION SUM (SUMA LONGUITUDES DE NOMBRES) -----------------");
        
        CriteriaQuery<Integer> querySum = criteria.createQuery(Integer.class);
        from = querySum.from(Cliente.class);

        querySum.select(criteria.sum(criteria.length(from.get("nombre"))));
        Integer sumaLongitudNombres = em.createQuery(querySum).getSingleResult();
        System.out.println("Suma total de las longitudes de los nombres: " + sumaLongitudNombres);


        System.out.println("--------------------- FUNCION AGREGACION MAX -------------------- ");
        CriteriaQuery<Integer> queryMax = criteria.createQuery(Integer.class);
        from = queryMax.from(Cliente.class);

        queryMax.select(criteria.max(from.get("id")));
        Integer ultimoId = em.createQuery(queryMax).getSingleResult();
        System.out.println("Último id: " + ultimoId);


        System.out.println("--------------------- FUNCION AGREGACION MIN -------------------- ");
        CriteriaQuery<Integer> queryMin = criteria.createQuery(Integer.class);
        from = queryMin.from(Cliente.class);

        queryMin.select(criteria.min(from.get("id")));
        Integer primerId = em.createQuery(queryMin).getSingleResult();
        System.out.println("Primer id: " + primerId);



        LocalDate fecha = LocalDate.now();
        System.out.println(fecha);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        System.out.println(fecha.format(format));


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM:YYYY");
        System.out.println(formatter.format(new Date()));
    }
}