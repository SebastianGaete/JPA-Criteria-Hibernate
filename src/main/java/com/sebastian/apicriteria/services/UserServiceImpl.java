package com.sebastian.apicriteria.services;

import java.util.List;
import com.sebastian.apicriteria.entity.Usuario;
import com.sebastian.apicriteria.jpaconnect.JpaUtil;
import com.sebastian.apicriteria.repository.CrudRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;


public class UserServiceImpl implements CrudRepository<Usuario> {

    private CriteriaQuery<Usuario> query;
    private Root<Usuario> from;
    private final EntityManager em = getEntityManager();
    private final CriteriaBuilder criteria = getCriteriaBuilder();
    

    private EntityManager getEntityManager(){
        EntityManager em = JpaUtil.getEntityManager();
        return em;
    }

    private CriteriaBuilder getCriteriaBuilder(){
        return getEntityManager().getCriteriaBuilder();
    }


    @Override
    public List<Usuario> listAll() {
        query = this.criteria.createQuery(Usuario.class);
        from = query.from(Usuario.class);
        query.select(from);

        List<Usuario> usuarios = this.em.createQuery(query).getResultList();
        return usuarios;
    }

    @Override
    public Usuario find(Integer id) {
        query = getCriteriaBuilder().createQuery(Usuario.class);
        from = query.from(Usuario.class);
        query.select(from).where(criteria.equal(from.get("id"), id));

        Usuario usuario = this.em.createQuery(query).setMaxResults(1).getSingleResult();
        return usuario;
    }

    @Override
    public void create(Usuario usuario) {
       try{
        this.em.getTransaction().begin();
        this.em.persist(usuario);
        this.em.getTransaction().commit();

       } catch (Exception e){
        this.em.getTransaction().rollback();
        e.getMessage();
       }
    }

    @Override
    public void delete(Integer id) {
        try{
        Usuario user = this.em.find(Usuario.class, id);
        this.em.getTransaction().begin();
        this.em.remove(user);
        this.em.getTransaction().commit();

       } catch (Exception e){
        this.em.getTransaction().rollback();
        e.getMessage();
       }
    }

}