package com.sebastian.apicriteria;

import com.sebastian.apicriteria.entity.Usuario;
import com.sebastian.apicriteria.repository.CrudRepository;
import com.sebastian.apicriteria.services.UserServiceImpl;

public class UsuarioMain {
    public static void main(String[] args) {
        
        CrudRepository<Usuario> repository = new UserServiceImpl();

        
        repository.listAll().forEach(System.out::println);

        
    }
}