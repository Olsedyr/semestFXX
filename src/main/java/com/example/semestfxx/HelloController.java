package com.example.semestfxx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private Game game;
    private Room currentRoom;
    public Item currentItem;

    @FXML
    private StackPane rootPane;

        //ToggleItems
    @FXML

    private ImageView soveværelseLampeTændt, soveværelseLampeSlukket, computerTændt, computerSlukket, radiator, vindueÅben, vindueLukket,
                badeværelseLysSlukket, badeværelseLysTændt, vandhaneTændt, vandhaneSlukket, bad,
                køkkenLampeTændt, køkkenLampeSlukket, tvTændt, tvSlukket;

        //ChoiceItems
    @FXML
    private ImageView komfur, transport;
    @FXML
    private AnchorPane badChoice, køleskabChoice, komfurChoice, transportChoice;

        //Doors
    @FXML
    private ImageView bedroomToBathroom, bathroomToBedroom,
                kitchenToBedroom, bedroomToKitchen,
                kitchenToCity, cityToKitchen,
                cityToBeach, beachToCity;

        //TrashItems
    @FXML
    private ImageView silkepapir,
                pizzabakke, mælkekarton;
    @FXML
    private Button quitGame, helpGame;

    @FXML
    private Text display;


    //------------------------------------------------------------------------------------------------------------------


    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("INIT");
        game = GameSingleton.getInstance().getGame();
        currentRoom = GameSingleton.getInstance().getGame().currentRoom;
        currentItem = GameSingleton.getInstance().getGame().currentItem;
    }

    //------------------------------------------------------------------------------------------------------------------
    ///ActionEvent
    public void startGame(ActionEvent event) throws IOException {
        System.out.println("Game Started!");
        loadBedroomStart(event);

        game.currentRoom = game.rooms.get(0);

    }

    public void loadBedroomStart(ActionEvent event) throws IOException {
        System.out.println("Start Bedroom");
        StackPane pane = FXMLLoader.load(getClass().getResource("bedroom.fxml"));
        rootPane.getChildren().setAll(pane);
        currentRoom = game.rooms.get(0);

        System.out.println(currentRoom);
    }

    //------------------------------------------------------------------------------------------------------------------
    ///MouseEvent
    public void loadBedroom(MouseEvent event) throws IOException {
        System.out.println("Bedroom");
        StackPane pane = FXMLLoader.load(getClass().getResource("bedroom.fxml"));
        rootPane.getChildren().setAll(pane);
        currentRoom = game.rooms.get(0);System.out.println(currentRoom);

    }

    public void loadKitchen(MouseEvent event) throws IOException {
        System.out.println("Kitchen");
        StackPane pane = FXMLLoader.load(getClass().getResource("kitchen.fxml"));
        rootPane.getChildren().setAll(pane);
        currentRoom = game.rooms.get(1);System.out.println(currentRoom);
    }

    public void loadBathroom(MouseEvent event) throws IOException {
        System.out.println("Bathroom");
        StackPane pane = FXMLLoader.load(getClass().getResource("bathroom.fxml"));
        rootPane.getChildren().setAll(pane);
        currentRoom = game.rooms.get(2);System.out.println(currentRoom);
    }
    public void loadCity(MouseEvent event) throws IOException {
        System.out.println("City");
        StackPane pane = FXMLLoader.load(getClass().getResource("city.fxml"));
        rootPane.getChildren().setAll(pane);
        currentRoom = game.rooms.get(3);System.out.println(currentRoom);
    }
    public void loadBeach(MouseEvent event) throws IOException {
        System.out.println("Beach");
        StackPane pane = FXMLLoader.load(getClass().getResource("beach.fxml"));
        rootPane.getChildren().setAll(pane);
        currentRoom = game.rooms.get(4);
    }

    //------------------------------------------------------------------------------------------------------------------
    public void deleteItem() throws IOException {
        // relevantfx:id.setImage(null);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Soveværelse: Tænder/Slukker lys
    @FXML
    void showCeilingLight (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("soveværelseLampe");
        game.switchItemState();

        if(game.currentItem.getItemState()==false) {
            soveværelseLampeSlukket.setVisible(true);
            soveværelseLampeTændt.setVisible(false);
        } else {
            soveværelseLampeSlukket.setVisible(false);
            soveværelseLampeTændt.setVisible(true);
        }

//        game.switchItemState();
        System.out.println(game.currentItem.toggleState);
    }

    // Soveværelse: Tænder/Slukker computer
    @FXML
    void showComputer (MouseEvent event) {
        if(computerSlukket.isVisible()==true) {
            computerSlukket.setVisible(false);
            computerTændt.setVisible(true);
        } else {
            computerSlukket.setVisible(true);
            computerTændt.setVisible(false);
        }
    }

    //Soveværelse: Åbner/Lukker vindue
    @FXML
    void showWindow (MouseEvent event) {
        if(vindueLukket.isVisible()==true) {
            vindueLukket.setVisible(false);
            vindueÅben.setVisible(true);
        } else {
            vindueLukket.setVisible(true);
            vindueÅben.setVisible(false);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // Badeværelse: Tænder/Slukker badeværelse lys
    @FXML
    void toggleBadeværelseLys (MouseEvent event) {
        if(badeværelseLysSlukket.isVisible()==true) {
            badeværelseLysSlukket.setVisible(false);
            badeværelseLysTændt.setVisible(true);
        } else {
            badeværelseLysSlukket.setVisible(true);
            badeværelseLysTændt.setVisible(false);
        }
    }

    // Badeværelse: Tænder/Slukker vandhane
    @FXML
    void toggleVandhane (MouseEvent event) {
        if(vandhaneSlukket.isVisible()==true) {
            vandhaneSlukket.setVisible(false);
            vandhaneTændt.setVisible(true);
        } else {
            vandhaneSlukket.setVisible(true);
            vandhaneTændt.setVisible(false);
        }
    }

    // Badeværelse: Bad choice
    @FXML
    void choiceBad (MouseEvent event) {
        game.currentRoom = game.rooms.get(2);
        game.currentItem = game.currentRoom.getItem("bad");
        if(game.currentItem.getItemUsed()==false) {
            if (badChoice.isVisible() == true) {
                badChoice.setVisible(false);
            } else {
                badChoice.setVisible(true);
            }
        }else{
            badChoice.setVisible(false);
        }
    }
    @FXML
    void choiceBruser (ActionEvent event) {
        game.currentItem = game.currentRoom.getItem("bad");
        badChoice.setVisible(false);
        game.switchItemState();
        game.score_list.add(currentItem.getItemPoints());
    }
    @FXML
    void choiceBadekar (ActionEvent event) {
        game.currentItem = game.currentRoom.getItem("bad");
        badChoice.setVisible(false);
        game.switchItemState();
    }


    //------------------------------------------------------------------------------------------------------------------
    // Køkken: Tænder/Slukker køkken lys
    @FXML
    void toggleKLamp (MouseEvent event) {
        if(køkkenLampeSlukket.isVisible()==true) {
            køkkenLampeSlukket.setVisible(false);
            køkkenLampeTændt.setVisible(true);
        } else {
            køkkenLampeSlukket.setVisible(true);
            køkkenLampeTændt.setVisible(false);
        }
    }

    // Køkken: Tænder/Slukker TV
    @FXML
    void toggleTv (MouseEvent event) {
        if(tvSlukket.isVisible()==true) {
            tvSlukket.setVisible(false);
            tvTændt.setVisible(true);
        } else {
            tvSlukket.setVisible(true);
            tvTændt.setVisible(false);
        }
    }

    // Køkken Choice: Køleskab
    @FXML
    void choiceKøleskab (MouseEvent event) {
        if(køleskabChoice.isVisible()==true) {
            køleskabChoice.setVisible(false);
        } else {
            køleskabChoice.setVisible(true);
        }
    }

    // Køkken Choice: Komfur
    @FXML
    void choiceKomfur (MouseEvent event) {
        if(komfurChoice.isVisible()==true) {
            komfurChoice.setVisible(false);
        } else {
            komfurChoice.setVisible(true);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // Byen Choice:
    @FXML
    void choiceTransport (MouseEvent event) {
        if (transportChoice.isVisible() == true) {
            transportChoice.setVisible(false);
        } else {
            transportChoice.setVisible(true);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // Strand:


    //------------------------------------------------------------------------------------------------------------------
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


