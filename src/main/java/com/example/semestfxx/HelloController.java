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

    private Game game;
    private Room kitchen;

    @FXML
    private StackPane rootPane;

        //Test image
    @FXML
    private ImageView image;

        //ToggleItems
    @FXML
    private ImageView LampeTændt, LampeSlukket, TvTændt, TvSlukket,
                loftLampeTændt, loftLampeSlukket, ComputerTændt, ComputerSlukket, radiator, VindueAaben, VindueLukket,
                BadeværelseLysTændt, BadeværelseLysSlukket, VandhaneTændt, VandhaneSlukket;
        //ChoiceItems
    @FXML
    private ImageView komfur

                , BadChoice;
        //Doors
    @FXML
    private ImageView kitchenToBedroom, bedroomToKitchen;

        //TrashItems
    @FXML
    private ImageView pizzabakke, mælkekarton;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("INIT");
        game = GameSingleton.getInstance().getGame();
        kitchen = game.rooms.get(0);
    }

    ///ActionEvent
    public void startGame(ActionEvent event) throws IOException {
        System.out.println("Game Started!");
        loadBedroomStart(event);
    }

    public void loadBedroomStart(ActionEvent event) throws IOException {
        System.out.println("Scene 2");
        StackPane pane = FXMLLoader.load(getClass().getResource("bedroom.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    ///MouseEvent
    public void loadBedroom(MouseEvent event) throws IOException {
        System.out.println("Scene 2");
        StackPane pane = FXMLLoader.load(getClass().getResource("bedroom.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void loadKitchen(MouseEvent event) throws IOException {
        System.out.println("Scene 3");
        StackPane pane = FXMLLoader.load(getClass().getResource("kitchen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void loadBathroom(MouseEvent event) throws IOException {
        System.out.println("Scene 4");
        StackPane pane = FXMLLoader.load(getClass().getResource("bathroom.fxml"));
        rootPane.getChildren().setAll(pane);
    }


    public void deleteItem() throws IOException {
        image.setImage(null);
    }

    // Soveværelse----------------------------------------------------------------------------------------------------
    // Soveværelse: Tænder/Slukker lys
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

    // Køkken----------------------------------------------------------------------------------------------------
    // Køkken: Tænder/Slukker køkken lys
    @FXML
    void toggleKLamp (MouseEvent event) {
        Item lamp = kitchen.getItem("køkkenlampe");

        if(LampeSlukket.isVisible()==true) {
            LampeSlukket.setVisible(false);
            LampeTændt.setVisible(true);
        } else {
            LampeSlukket.setVisible(true);
            LampeTændt.setVisible(false);
        }
    }

    // Køkken: Tænder/Slukker TV
    @FXML
    void toggleTv (MouseEvent event) {
        if(TvSlukket.isVisible()==true) {
            TvSlukket.setVisible(false);
            TvTændt.setVisible(true);
        } else {
            TvSlukket.setVisible(true);
            TvTændt.setVisible(false);
        }
    }

    // Badeværelse----------------------------------------------------------------------------------------------------
    // Badeværelse: Tænder/Slukker badeværelse lys
    @FXML
    void toggleBadeværelseLys (MouseEvent event) {
        if(BadeværelseLysSlukket.isVisible()==true) {
            BadeværelseLysSlukket.setVisible(false);
            BadeværelseLysTændt.setVisible(true);
        } else {
            BadeværelseLysSlukket.setVisible(true);
            BadeværelseLysTændt.setVisible(false);
        }
    }

    // Badeværelse: Tænder/Slukker vandhane
    @FXML
    void toggleVandhane (MouseEvent event) {
        if(VandhaneSlukket.isVisible()==true) {
            VandhaneSlukket.setVisible(false);
            VandhaneTændt.setVisible(true);
        } else {
            VandhaneSlukket.setVisible(true);
            VandhaneTændt.setVisible(false);
        }
    }

    // Badeværelse: Choise bath
    @FXML
    void badChoiceMenu (MouseEvent event) {
        ///This should be used or not
        if(BadChoice.isVisible()==false) {
            BadChoice.setVisible(true);
        } else {
            BadChoice.setVisible(false);
        }
    }


}


