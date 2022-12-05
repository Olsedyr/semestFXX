package com.example.semestfxx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
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

        Iterator it = game.inventory.trash.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
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
        game.currentRoom = game.rooms.get(0);
        showRoomDescription(game.currentRoom);
    }

    //------------------------------------------------------------------------------------------------------------------
    ///MouseEvent
    public void loadBedroom(MouseEvent event) throws IOException {
        System.out.println("Bedroom");
        StackPane pane = FXMLLoader.load(getClass().getResource("bedroom.fxml"));
        rootPane.getChildren().setAll(pane);
        game.currentRoom = game.rooms.get(0);

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
        if(event.getButton() == MouseButton.SECONDARY){
            showDescription(game.currentItem);
        } else {
            game.switchItemState();

            if(game.currentItem.getItemState()==false) {
                soveværelseLampeSlukket.setVisible(true);
                soveværelseLampeTændt.setVisible(false);
            } else {
                soveværelseLampeSlukket.setVisible(false);
                soveværelseLampeTændt.setVisible(true);
            }

            System.out.println(game.currentItem.toggleState);
        }
    }

    // Soveværelse: Tænder/Slukker computer
    @FXML
    void showComputer (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("computer");
        if(event.getButton() == MouseButton.SECONDARY){
            showDescription(game.currentItem);
        } else {
            game.switchItemState();

            if(game.currentItem.getItemState()==false) {
                computerSlukket.setVisible(true);
                computerTændt.setVisible(false);
            } else {
                computerSlukket.setVisible(false);
                computerTændt.setVisible(true);
            }

            System.out.println(game.currentItem.toggleState);
        }
    }

    //Soveværelse: Åbner/Lukker vindue
    @FXML
    void showWindow (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("vindue");
        if(event.getButton() == MouseButton.SECONDARY){
            showDescription(game.currentItem);
        } else {
            game.switchItemState();

            if(game.currentItem.getItemState()==false) {
                vindueLukket.setVisible(true);
                vindueÅben.setVisible(false);
            } else {
                vindueLukket.setVisible(false);
                vindueÅben.setVisible(true);
            }

            System.out.println(game.currentItem.toggleState);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // Badeværelse: Tænder/Slukker badeværelse lys
    @FXML
    void toggleBadeværelseLys (MouseEvent event) {
        game.currentRoom = game.rooms.get(2);
        game.currentItem = game.currentRoom.getItem("badeværelseLys");
        if(event.getButton() == MouseButton.SECONDARY){
            showDescription(game.currentItem);
        } else {
            game.switchItemState();

            if(game.currentItem.getItemState()==false) {
                badeværelseLysSlukket.setVisible(true);
                badeværelseLysTændt.setVisible(false);
            } else {
                badeværelseLysSlukket.setVisible(false);
                badeværelseLysTændt.setVisible(true);
            }

            System.out.println(game.currentItem.toggleState);
        }
    }

    // Badeværelse: Tænder/Slukker vandhane
    @FXML
    void toggleVandhane (MouseEvent event) {
        game.currentRoom = game.rooms.get(2);
        game.currentItem = game.currentRoom.getItem("vandhane");
        if(event.getButton() == MouseButton.SECONDARY){
            showDescription(game.currentItem);
        } else {
            game.switchItemState();

            if(game.currentItem.getItemState()==false) {
                vandhaneSlukket.setVisible(true);
                vandhaneTændt.setVisible(false);
            } else {
                vandhaneSlukket.setVisible(false);
                vandhaneTændt.setVisible(true);
            }

            System.out.println(game.currentItem.toggleState);
        }
    }

    // Badeværelse: Bad choice
    @FXML
    void choiceBad (MouseEvent event) {
        if(badChoice.isVisible()==true) {
            badChoice.setVisible(false);
        } else {
            badChoice.setVisible(true);
        }
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

    // Trash Items
    public void collectSilkepapir(MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("silkepapir");
        game.switchItemState();
        silkepapir.setImage(null);
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

    // Does not work fully yet
    public void showRoomDescription(Room currentRoom) {
        display.setVisible(true);
        display.setText(currentRoom.getLongDescription());
    }

    public void showDescription(Item currentItem) {
        if (display.isVisible()==false) {
            display.setVisible(true);
            display.setText(currentItem.getItemDescription());
        } else {
            display.setVisible(false);
        }
    }


}


