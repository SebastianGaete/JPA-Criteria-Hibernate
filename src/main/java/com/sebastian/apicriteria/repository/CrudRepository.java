package com.sebastian.apicriteria.repository;

import java.util.List;

public interface CrudRepository<T> {
    
    List<T> listAll();
    T find(Integer id);
    void create(T t);
    void delete(Integer id);
    
}