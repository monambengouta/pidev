/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.controller;

import com.pidev.dao.EvenementService;
import com.pidev.entity.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ben Gouta Monam
 */
public class ListEventController implements Initializable  {


    private final ListData listdata = new ListData();
    @FXML
    private AnchorPane anchorparent;
    @FXML
    private TableView<Evenement> eventable;
    @FXML
    private TableColumn<Evenement,String> id_titre;
    @FXML
    private TableColumn<Evenement,String> id_local;
    @FXML
    private TableColumn<Evenement,String> id_prix;
    @FXML
    private TableColumn<Evenement,String> id_descreption;
    ListData listdate = new ListData();
     ObservableList<Evenement> lsit=listdata.getEvents();
    @FXML
    private Button btn_addevent;
    @FXML
    private Button btn_update;

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
        id_titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
         id_descreption.setCellValueFactory(new PropertyValueFactory<>("Description"));
          id_local.setCellValueFactory(new PropertyValueFactory<>("Locall"));
           id_prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        eventable.setItems(lsit);
        
               
    } 

    @FXML
    private void Changescenetoadd(MouseEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/com/pidev/view/HomeEvent.fxml"));
             Stage window =(Stage)btn_addevent.getScene().getWindow();
             window.setScene(new Scene(root));
             
        } catch (IOException ex) {
            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       

    @FXML
    private void deleteRowFromTable(MouseEvent event) {
         
         
             
             Evenement e=eventable.getSelectionModel().getSelectedItems().get(0);
            EvenementService es;
            try {
                es = EvenementService.getInstance();
                es.delete(e);
                 Evenement selectedItem = eventable.getSelectionModel().getSelectedItem();
                 eventable.getItems().remove(selectedItem);
                
            } catch (SQLException ex) {
                Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
           
         
    }

    @FXML
    private void UpdateEvent(MouseEvent event) {
         try {
             Evenement e=eventable.getSelectionModel().getSelectedItems().get(0);
              if(e.equals(null)){
         } else {
                  EventUpdate.liste.clear();
                  EventUpdate.liste.add(e);
                  Parent root= FXMLLoader.load(getClass().getResource("/com/pidev/view/UpdateEvent.fxml"));
                  Stage window =(Stage)btn_update.getScene().getWindow();
                  window.setScene(new Scene(root));
             }
        } catch (IOException ex) {
            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
