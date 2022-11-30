package com.example.semestfxx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private Game game;
    private Room thisRoom;

    @FXML
    private StackPane rootPane;

        //ToggleItems
    @FXML
    private ImageView lampOn, LampeSlukket, TvTændt, TvSlukket,
                loftLampeTændt, loftLampeSlukket, ComputerTændt, ComputerSlukket, radiator, VindueAaben, VindueLukket,
                BadeværelseLysSlukket, BadeværelseLysTændt, VandhaneTændt, VandhaneSlukket;
        //ChoiceItems
    @FXML
    private ImageView komfur, transport;
        //Doors
    @FXML
    private ImageView kitchenToBedroom, bedroomToKitchen, bedroomToBathroom, bathroomToBedroom, kitchenToCity, cityToKitchen, cityToBeach, beachToCity;

        //TrashItems
    @FXML
    private ImageView pizzabakke, mælkekarton, silkepapir;

    @FXML
    private Button Quit, Help;

    @FXML
    private Text display;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("INIT");
        game = GameSingleton.getInstance().getGame();
        thisRoom = game.rooms.get(0);
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
        System.out.println("Bedroom");
        StackPane pane = FXMLLoader.load(getClass().getResource("bedroom.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void loadKitchen(MouseEvent event) throws IOException {
        System.out.println("Kitchen");
        StackPane pane = FXMLLoader.load(getClass().getResource("kitchen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void loadBathroom(MouseEvent event) throws IOException {
        System.out.println("Bathroom");
        StackPane pane = FXMLLoader.load(getClass().getResource("bathroom.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    public void loadCity(MouseEvent event) throws IOException {
        System.out.println("City");
        StackPane pane = FXMLLoader.load(getClass().getResource("city.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    public void loadBeach(MouseEvent event) throws IOException {
        System.out.println("Beach");
        StackPane pane = FXMLLoader.load(getClass().getResource("beach.fxml"));
        rootPane.getChildren().setAll(pane);
    }


    public void deleteItem() throws IOException {
        // relevantfx:id.setImage(null);
    }


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

    // Køkken: Tænder/Slukker køkken lys
    @FXML
    void toggleKLamp (MouseEvent event) {
        Item lamp = thisRoom.getItem("køkkenlampe");

        if(LampeSlukket.isVisible()==true) {
            LampeSlukket.setVisible(false);
            lampOn.setVisible(true);
        } else {
            LampeSlukket.setVisible(true);
            lampOn.setVisible(false);
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

    @FXML
    void quit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void help(ActionEvent event) {
        if (display.isVisible()==false) {
            display.setVisible(true);
            display.setText(GameText.textHelp());
        } else {
            display.setVisible(false);
        }

    }


}


