/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.controller;

import com.pidev.dao.EvenementService;
import com.pidev.entity.Evenement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ben Gouta Monam
 */
public class HomeEventController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField description;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField nbplace;
    @FXML
    private TextField prix;
    @FXML
    private Button btn_enregistrer;
    @FXML
    private Button btn_reinitialiser;
    @FXML
    private ComboBox<?> artist;
    @FXML
    private ComboBox<String> local;

    @FXML
    private DatePicker date_debut;
    private Button btn_addevent;
    @FXML
    private Button btn_selectevent;
    String pathimage;
    @FXML
    private ImageView imageview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("megrine", "maison de culture tunis", "Don Papa");
        local.setItems(list);
        btn_enregistrer.setOnAction((ActionEvent event) -> {
            /* Calendar calendar = Calendar.getInstance();
            Calendar calendar1 = Calendar.getInstance();
            calendar.set(date_debut.getValue().getYear(), date_debut.getValue().getMonthValue(), date_debut.getValue().getDayOfMonth());
            calendar1.set(date_fin.getValue().getYear(), date_fin.getValue().getMonthValue(), date_fin.getValue().getDayOfMonth());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formatedDate = sdf.format(calendar.getTime());
            String formatedDate1 = sdf.format(calendar1.getTime());
            
            Date date_debut1 = sdf.parse(formatedDate) ;
            Date date_fin1 =sdf.parse(formatedDate1);*/
 /*LocalDate localDate = date_debut.getValue();
            LocalDate localDate1 = date_fin.getValue();
            Date date_debut1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqldate1=new java.sql.Date(date_debut1.getTime());
            Date date_fin1 = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqldate2=new java.sql.Date(date_fin1.getTime());*/

            LocalDate value = date_debut.getValue();
            LocalDate value1 = date_fin.getValue();
            String s = String.valueOf(value.getMonthValue()) + "/" + String.valueOf(value.getDayOfMonth() + "/" + String.valueOf(value.getYear()));
            String s1 = String.valueOf(value1.getMonthValue()) + "/" + String.valueOf(value1.getDayOfMonth() + "/" + String.valueOf(value1.getYear()));
            Evenement p = new Evenement(titre.getText(), description.getText(), local.getValue(), s, s1, Integer.parseInt(nbplace.getText()), Integer.parseInt(prix.getText()), pathimage);
            EvenementService es;
            try {
                es = EvenementService.getInstance();

                es.insert(p);
            } catch (SQLException ex) {
                Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Evenement insérée avec succés!");
            alert.show();

        });
    }

    @FXML
    private void UploadImage(MouseEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        List<File> f = fc.showOpenMultipleDialog(null);
        String x = "";
        for (File file : f) {
            x = file.getAbsolutePath();
        }
        Image image = new Image(new FileInputStream(f.get(0)));
        imageview.setImage(image);
        pathimage = x;

    }

    @FXML
    private void changescenetolist(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/pidev/view/ListEvent.fxml"));
            Stage window = (Stage) btn_selectevent.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initiate(MouseEvent event) {
        titre.setText("");
        description.setText("");
        prix.setText("");
        local.setValue("Aucun");
        nbplace.setText("");
        
                
    }

}
