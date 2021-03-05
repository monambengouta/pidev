/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.dao;

import com.pidev.entity.Evenement;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Ben Gouta Monam
 */
public interface EService <T> {
  
   

    public void insert(T o) throws SQLException ;
    public void delete(T o) throws SQLException;
    public ObservableList<Evenement> displayAll() throws SQLException;
    public T displayById(int id) throws SQLException;
    
    public boolean update(T os) throws SQLException;
}
