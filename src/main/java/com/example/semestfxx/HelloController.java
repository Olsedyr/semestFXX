package com.example.semestfxx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private ImageView image;

        //ToggleItems
    @FXML
    private ImageView LampeTændt, LampeSlukket, TvTændt, TvSlukket;
        //ChoiceItems
    @FXML
    private ImageView Komfur;
        //Doors
    @FXML
    private ImageView kitchenToBedroom, bedroomToKitchen;

    @FXML
    private ImageView loftLampeTændt, loftLampeSlukket, ComputerTændt, ComputerSlukket, radiator, VindueAaben, VindueLukket;




    @Override
    public void initialize(URL url, ResourceBundle rb){

    }


    public void loadBedroom(ActionEvent event) throws IOException {

        System.out.println("Scene 2");
        StackPane pane = FXMLLoader.load(getClass().getResource("bedroom.fxml"));
        rootPane.getChildren().setAll(pane);

    }

    public void loadBedroom1(MouseEvent event) throws IOException {

        System.out.println("Scene 2");
        StackPane pane = FXMLLoader.load(getClass().getResource("bedroom.fxml"));
        rootPane.getChildren().setAll(pane);

    }


    public void loadKitchen(MouseEvent event) throws IOException {

        System.out.println("Scene 3");
        StackPane pane = FXMLLoader.load(getClass().getResource("kitchen.fxml"));
        rootPane.getChildren().setAll(pane);

    }

    public void deleteItem() throws IOException {
        image.setImage(null);
    }

    @FXML
    void showImage (MouseEvent event) {
        if(LampeSlukket.isVisible()==true) {
            LampeSlukket.setVisible(false);
            LampeTændt.setVisible(true);
        } else {
            LampeSlukket.setVisible(true);
            LampeTændt.setVisible(false);
        }
    }




    // Soveværelse: Tænder/Slukker computer
    @FXML
    void showComputer (MouseEvent event) {
        if(ComputerSlukket.isVisible()==true) {
            ComputerSlukket.setVisible(false);
            ComputerTændt.setVisible(true);
        } else {
            ComputerSlukket.setVisible(true);
            ComputerTændt.setVisible(false);
        }
    }

    //Soveværelse: Åbner/Lukker vindue

    @FXML
    void showWindow (MouseEvent event) {
        if(VindueLukket.isVisible()==true) {
            VindueLukket.setVisible(false);
            VindueAaben.setVisible(true);
        } else {
            VindueLukket.setVisible(true);
            VindueAaben.setVisible(false);
        }
    }

    @FXML
    void showCeilingLight (MouseEvent event) {
        if(loftLampeSlukket.isVisible()==true) {
            loftLampeSlukket.setVisible(false);
            loftLampeTændt.setVisible(true);
        } else {
            loftLampeSlukket.setVisible(true);
            loftLampeTændt.setVisible(false);
        }
    }



}


