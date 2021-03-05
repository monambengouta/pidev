/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.controller;

import com.pidev.dao.EvenementService;
import com.pidev.entity.Evenement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ben Gouta Monam
 */
public class ListData {
    
     private ObservableList<Evenement> events=FXCollections.observableArrayList();

    public ListData()   {
        try{
            
        
        EvenementService pdao=EvenementService.getInstance();
        events= pdao.displayAll();
        System.out.println(events);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Evenement> getEvents(){
        return events;
    }
    
}
