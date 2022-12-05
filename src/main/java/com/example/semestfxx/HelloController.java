package com.example.semestfxx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController implements Initializable {

    private Game game;
    private Room currentRoom;
    public Item currentItem;

    @FXML
    private StackPane rootPane;

        //Items
    @FXML

    private ImageView soveværelseLampeTændt, soveværelseLampeSlukket, computerTændt, computerSlukket, radiator, vindueÅben, vindueLukket,
                badeværelseLysSlukket, badeværelseLysTændt, vandhaneTændt, vandhaneSlukket, bad, badShower, badTub,
                køkkenLampeTændt, køkkenLampeSlukket, tvTændt, tvSlukket, køleskabÅbnet, salat, burger, komfurTændt, komfurPande, transport,
                cykle, bil;

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
    public void initialize(URL url, ResourceBundle rb) {
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
    }

    public void loadBedroomStart(ActionEvent event) throws IOException {
        System.out.println("Start Bedroom");
        StackPane pane = FXMLLoader.load(getClass().getResource("bedroom.fxml"));
        rootPane.getChildren().setAll(pane);
        game.currentRoom = game.rooms.get(0);
    }

    //------------------------------------------------------------------------------------------------------------------
    ///MouseEvent Load Rooms
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
        game.currentRoom = game.rooms.get(1);
    }

    public void loadBathroom(MouseEvent event) throws IOException {
        System.out.println("Bathroom");
        StackPane pane = FXMLLoader.load(getClass().getResource("bathroom.fxml"));
        rootPane.getChildren().setAll(pane);
        game.currentRoom = game.rooms.get(2);
    }
    public void loadCity(MouseEvent event) throws IOException {
        System.out.println("City");
        StackPane pane = FXMLLoader.load(getClass().getResource("city.fxml"));
        rootPane.getChildren().setAll(pane);
        game.currentRoom = game.rooms.get(3);
    }
    public void loadBeach(MouseEvent event) throws IOException {
        System.out.println("Beach");
        StackPane pane = FXMLLoader.load(getClass().getResource("beach.fxml"));
        rootPane.getChildren().setAll(pane);
        game.currentRoom = game.rooms.get(4);
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
            showNewPoints(game.currentItem);
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
            showNewPoints(game.currentItem);
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
            showNewPoints(game.currentItem);
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
            showNewPoints(game.currentItem);
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
            showNewPoints(game.currentItem);
        }
    }

    // Badeværelse: Bad choice
    @FXML
    void choiceBad (MouseEvent event) {
        game.currentRoom = game.rooms.get(2);
        game.currentItem = game.currentRoom.getItem("bad");

        if(event.getButton() == MouseButton.SECONDARY){
            showDescription(game.currentItem);
        } else {
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
    }
    @FXML
    void choiceBruser (MouseEvent event) {
        game.plus_sum_score();
        game.switchItemState();
        game.currentItem = game.currentRoom.getItem("bad");
        game.currentItem.itemDescription = game.currentItem.choice1Text;
        badChoice.setVisible(false);
        badShower.setVisible(true);
        showNewPoints(game.currentItem);
    }
    @FXML
    void choiceBadekar (MouseEvent event) {
        game.switchItemState();
        game.currentItem = game.currentRoom.getItem("bad");
        game.currentItem.itemDescription = game.currentItem.choice2Text;
        badChoice.setVisible(false);
        badTub.setVisible(true);
    }


    //------------------------------------------------------------------------------------------------------------------
    // Køkken: Tænder/Slukker køkken lys
    @FXML
    void toggleKLamp (MouseEvent event) {
        game.currentRoom = game.rooms.get(1);
        game.currentItem = game.currentRoom.getItem("køkkenlampe");

        if(event.getButton() == MouseButton.SECONDARY){
            showDescription(game.currentItem);
        } else {
            game.switchItemState();

            if(game.currentItem.getItemState()==false) {
                køkkenLampeSlukket.setVisible(true);
                køkkenLampeTændt.setVisible(false);
            } else {
                køkkenLampeSlukket.setVisible(false);
                køkkenLampeTændt.setVisible(true);
            }

            System.out.println(game.currentItem.toggleState);
            showNewPoints(game.currentItem);
        }
    }

    // Køkken: Tænder/Slukker TV
    @FXML
    void toggleTv (MouseEvent event) {
        game.currentRoom = game.rooms.get(1);
        game.currentItem = game.currentRoom.getItem("tv");

        if(event.getButton() == MouseButton.SECONDARY){
            showDescription(game.currentItem);
        } else {
            game.switchItemState();

            if(game.currentItem.getItemState()==false) {
                tvSlukket.setVisible(true);
                tvTændt.setVisible(false);
            } else {
                tvSlukket.setVisible(false);
                tvTændt.setVisible(true);
            }

            System.out.println(game.currentItem.toggleState);
            showNewPoints(game.currentItem);
        }
    }

    // Køkken Choice: Køleskab
    @FXML
    void choiceKøleskab (MouseEvent event) {
        game.currentRoom = game.rooms.get(1);
        game.currentItem = game.currentRoom.getItem("køleskab");

        if(event.getButton() == MouseButton.SECONDARY){
            showDescription(game.currentItem);
        } else {
            if(game.currentItem.getItemUsed()==false) {
                if(køleskabChoice.isVisible()==true) {
                    køleskabChoice.setVisible(false);
                    køleskabÅbnet.setVisible(false);
                } else {
                    køleskabChoice.setVisible(true);
                    køleskabÅbnet.setVisible(true);
                }
            }else{
                køleskabChoice.setVisible(false);
                køleskabÅbnet.setVisible(false);
            }
        }
    }
    @FXML
    void choiceSalat (MouseEvent event) {
        game.switchItemState();
        game.plus_sum_score();
        game.currentItem = game.currentRoom.getItem("køleskab");
        game.currentItem.itemDescription = game.currentItem.choice1Text;
        køleskabChoice.setVisible(false);
        køleskabÅbnet.setVisible(false);
        salat.setVisible(true);
        showNewPoints(game.currentItem);
    }
    @FXML
    void choiceBurger (MouseEvent event) {
        game.switchItemState();
        game.currentItem = game.currentRoom.getItem("køleskab");
        game.currentItem.itemDescription = game.currentItem.choice2Text;
        køleskabChoice.setVisible(false);
        køleskabÅbnet.setVisible(false);
        burger.setVisible(true);
    }

    // Køkken Choice: Komfur
    @FXML
    void choiceKomfur (MouseEvent event) {
        game.currentRoom = game.rooms.get(1);
        game.currentItem = game.currentRoom.getItem("komfur");

        if(event.getButton() == MouseButton.SECONDARY){
            showDescription(game.currentItem);
        } else {
            if(game.currentItem.getItemUsed()==false) {
                if (komfurChoice.isVisible()==true) {
                    komfurChoice.setVisible(false);
                } else {
                    komfurChoice.setVisible(true);
                }
            }else{
                komfurChoice.setVisible(false);
            }
        }
    }
    @FXML
    void choiceOvenen (MouseEvent event) {
        game.switchItemState();
        game.currentItem = game.currentRoom.getItem("komfur");
        game.currentItem.itemDescription = game.currentItem.choice1Text;
        komfurChoice.setVisible(false);
        komfurTændt.setVisible(true);
    }
    @FXML
    void choiceStegepande (MouseEvent event) {
        game.plus_sum_score();
        game.switchItemState();
        game.currentItem = game.currentRoom.getItem("komfur");
        game.currentItem.itemDescription = game.currentItem.choice2Text;
        komfurChoice.setVisible(false);
        komfurPande.setVisible(true);
        showNewPoints(game.currentItem);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Byen Choice: Transport
    @FXML
    void choiceTransport (MouseEvent event) {
        game.currentRoom = game.rooms.get(3);
        game.currentItem = game.currentRoom.getItem("transport");

        if(event.getButton() == MouseButton.SECONDARY){
            showDescription(game.currentItem);
        } else {
            if(game.currentItem.getItemUsed()==false) {
                if (transportChoice.isVisible() == true) {
                    transportChoice.setVisible(false);
                } else {
                    transportChoice.setVisible(true);
                }
            }else{
                transportChoice.setVisible(false);
            }
        }
    }
    @FXML
    void choiceCykle (MouseEvent event) {
        game.switchItemState();
        game.plus_sum_score();
        game.currentItem = game.currentRoom.getItem("transport");
        game.currentItem.itemDescription = game.currentItem.choice1Text;
        transport.setVisible(false);
        cykle.setVisible(true);
        showNewPoints(game.currentItem);
    }
    @FXML
    void choiceBil (MouseEvent event) {
        game.switchItemState();
        game.currentItem = game.currentRoom.getItem("transport");
        game.currentItem.itemDescription = game.currentItem.choice2Text;
        transport.setVisible(false);
        bil.setVisible(true);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Strand:


    //------------------------------------------------------------------------------------------------------------------
    // Trash Items
    public void collectSilkepapir(MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("silkepapir");
        game.switchItemState();
        silkepapir.setImage(null);
        showNewPoints(game.currentItem);
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
            display.setText(GameText.textHelp());
        }
    }

    // Does not work fully yet
//    public void showRoomDescription(Room currentRoom) {
//        display.setText(currentRoom.getLongDescription());
//        display.setVisible(true);
//    }

    public void showDescription(Item currentItem) {

        if (display.isVisible()==false) {
            display.setVisible(true);
            display.setText(currentItem.getItemDescription());
        } else if(display.isVisible() == true && currentItem.getItemDescription().equals(display.getText())){
            display.setVisible(false);
        } else {
            display.setText(currentItem.getItemDescription());
        }
    }
    public void showNewPoints(Item currentItem) {
        if (display.isVisible()==false) {
            display.setVisible(true);
            display.setText(game.getAddedPoints());
        } else if(display.isVisible() == true && game.getAddedPoints().equals(display.getText())){
            display.setVisible(false);
        } else {
            display.setText(game.getAddedPoints());
        }
    }

    @FXML
    void showHighscore(){
        String data = null;
        try {
            File myObj = new File("score.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println("Din highscore var" + data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


