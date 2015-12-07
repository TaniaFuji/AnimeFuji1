/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tania.anime.model;

import java.util.List;

/**
 *
 * @author Seb
 */
public interface CRUDDB <T> {
    
    //CREATE
    public void insert(T dto);
    
    //READ
    public T select(String campos,String condicion);
    public List<T> selectAll(String campos,String condicion);
    
    //Update
    public void update(T dto);
    
    //Delete
    public void delete(T dto);
}
