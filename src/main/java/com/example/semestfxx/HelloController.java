package com.example.semestfxx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    private Game game;
    private Room currentRoom;
    public Item currentItem;
    public StackPane bedroomPane, bathroomPane, kitchenPane, cityPane, beachPane;
    public Inventory inventory;

    @FXML
    private StackPane rootPane;

    //Items
    @FXML

    private ImageView soveværelseLampeTændt, soveværelseLampeSlukket, computerTændt, computerSlukket, vindueÅben, vindueLukket,
                badeværelseLysSlukket, badeværelseLysTændt, vandhaneTændt, vandhaneSlukket, bad, badShower, badTub,

                køkkenLampeTændt, køkkenLampeSlukket, tvTændt, tvSlukket, køleskabÅbnet, salat, burger, komfurTændt, komfurPande,
                cykle, bil, sodavandsdåser, transport;

    @FXML
    private AnchorPane badChoice, køleskabChoice, komfurChoice, transportChoice, npcQuiz, npcQuiz1, npcQuiz2, npcQuiz3, npcQuizF1, npcQuizF2, npcQuizF3;

        //Doors
    @FXML
    private ImageView bedroomToBathroom, bathroomToBedroom,
                kitchenToBedroom, bedroomToKitchen,
                kitchenToCity, cityToKitchen,
                cityToBeach, beachToCity;

    //TrashItems
    @FXML
    private ImageView silkepapir, pizzabakke, mælkekarton;

    @FXML
    private Button quitGame, helpGame;

    @FXML
    private ListView<String> inventoryList;

    @FXML
    private Text display, highscoreLoader;

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("INIT");
        game = GameSingleton.getInstance().getGame();
        currentRoom = GameSingleton.getInstance().getGame().currentRoom;
        currentItem = GameSingleton.getInstance().getGame().currentItem;
        inventory = GameSingleton.getInstance().getGame().inventory;
    }

    //------------------------------------------------------------------------------------------------------------------
    ///ActionEvent
    public void loadHighscore(ActionEvent event) throws IOException {
        String fileName = "score.txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            //process the line
            System.out.println(line);
            if (highscoreLoader.isVisible()==false) {
                highscoreLoader.setVisible(true);
                highscoreLoader.setText(String.valueOf("Din highscore er: "+line));
            } else {
                highscoreLoader.setVisible(false);
            }
        }
    }
    public void startGame(ActionEvent event) throws IOException {
        worldStart(event);
        System.out.println("Game Started!");
    }
    public void worldStart(ActionEvent event) throws IOException {
        System.out.println("Start Bedroom");
        StackPane pane = FXMLLoader.load(getClass().getResource("worldStart.fxml"));
        rootPane.getChildren().setAll(pane);
        game.currentRoom = game.rooms.get(0);
    }

    //------------------------------------------------------------------------------------------------------------------
    ///MouseEvent Load Rooms
    public void loadBedroom(MouseEvent event) throws IOException {
        bedroomPane.setVisible(true);
        kitchenPane.setVisible(false);
        bathroomPane.setVisible(false);
        game.currentRoom = game.rooms.get(0);
        showRoomDescription(game.currentRoom);
    }
    public void loadKitchen(MouseEvent event) throws IOException {
        kitchenPane.setVisible(true);
        bedroomPane.setVisible(false);
        cityPane.setVisible(false);
        game.currentRoom = game.rooms.get(1);
        showRoomDescription(game.currentRoom);
    }
    public void loadBathroom(MouseEvent event) throws IOException {
        bedroomPane.setVisible(false);
        bathroomPane.setVisible(true);
        game.currentRoom = game.rooms.get(2);
        showRoomDescription(game.currentRoom);
    }
    public void loadCity(MouseEvent event) throws IOException {
        cityPane.setVisible(true);
        beachPane.setVisible(false);
        kitchenPane.setVisible(false);
        game.currentRoom = game.rooms.get(3);
        showRoomDescription(game.currentRoom);
    }
    public void loadBeach(MouseEvent event) throws IOException {
        beachPane.setVisible(true);
        cityPane.setVisible(false);
        game.currentRoom = game.rooms.get(4);
        showRoomDescription(game.currentRoom);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Soveværelse: Tænder/Slukker lys
    @FXML
    void showCeilingLight (MouseEvent event) {
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
        game.currentItem.itemDescription = game.currentItem.choice1Text;
        transportChoice.setVisible(false);
        transport.setVisible(false);
        cykle.setVisible(true);
        showNewPoints(game.currentItem);
    }
    @FXML
    void choiceBil (MouseEvent event) {
        game.switchItemState();
        game.currentItem.itemDescription = game.currentItem.choice2Text;
        transportChoice.setVisible(false);
        transport.setVisible(false);
        bil.setVisible(true);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Strand:
    @FXML
    void npcQuiz (MouseEvent event) {
        game.currentItem = game.currentRoom.getItem("NPC");
        if(game.currentItem.getItemUsed()==false) {
            if (npcQuiz.isVisible() == true) {
                npcQuiz.setVisible(false);
                npcQuiz1.setVisible(false);
                npcQuiz2.setVisible(false);
                npcQuiz3.setVisible(false);
            } else {
                npcQuiz.setVisible(true);
                npcQuiz1.setVisible(true);
                npcQuizF1.setVisible(true);
                npcQuizF2.setVisible(true);
                npcQuizF3.setVisible(true);
            }
        }else{
            npcQuiz.setVisible(false);
        }
    }
    @FXML
    void npcQuizT (MouseEvent event) {
        game.plus_sum_score();
        showNewPoints(game.currentItem);
        if(npcQuiz1.isVisible()==true){
            npcQuiz1.setVisible(false);
            npcQuiz2.setVisible(true);
            npcQuizF2.setVisible(true);
        } else if (npcQuiz2.isVisible()==true) {
            npcQuiz2.setVisible(false);
            npcQuiz3.setVisible(true);
            npcQuizF3.setVisible(true);
        } else if (npcQuiz3.isVisible()==true) {
            npcQuiz3.setVisible(false);
            npcQuiz.setVisible(false);
            npcQuizF3.setVisible(true);
            game.switchItemState();
        }
    }
    @FXML
    void npcQuizF (MouseEvent event) {
        if(npcQuiz1.isVisible()==true){
            npcQuizF1.setVisible(false);
        } else if (npcQuiz2.isVisible()==true) {
            npcQuizF2.setVisible(false);
        } else if (npcQuiz3.isVisible()==true) {
            npcQuizF3.setVisible(false);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // Trash Items
    public void collectSilkepapir(MouseEvent event) {
        game.currentItem = game.currentRoom.getItem("silkepapir");
        game.switchItemState();
        showNewPoints(game.currentItem);
        silkepapir.setImage(null);
        itemToInventory();
    }
    public void collectPizzabakke(MouseEvent event) {
        game.currentItem = game.currentRoom.getItem("pizzabakke");
        game.switchItemState();
        showNewPoints(game.currentItem);
        pizzabakke.setImage(null);
        itemToInventory();
    }
    public void collectMælkekarton(MouseEvent event) {
        game.currentItem = game.currentRoom.getItem("mælkekarton");
        game.switchItemState();
        showNewPoints(game.currentItem);
        mælkekarton.setImage(null);
        itemToInventory();
    }
    public void collectSodavandsdåser (MouseEvent event) {
        game.currentItem = game.currentRoom.getItem("sodavandsdåser");
        game.switchItemState();
        showNewPoints(game.currentItem);
        sodavandsdåser.setImage(null);
        itemToInventory();
    }

    //------------------------------------------------------------------------------------------------------------------
    // Inventory
    public void itemToInventory() {
        if (inventoryList==null) {
            inventoryList = new ListView<String>();
        }
        inventoryList.getItems().add(game.currentItem.itemName);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Buttons
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

    public void showRoomDescription(Room currentRoom) {
        display.setText(currentRoom.getLongDescription());
        display.setVisible(true);
    }

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
        } else {
            display.setText(game.getAddedPoints());
        }
    }

    @FXML
    void showInventory (ActionEvent event) {
        if (display.isVisible()==false) {
            display.setVisible(true);
            display.setText(game.getInventoryDescription());
        } else {
            display.setVisible(false);
        }
    }
}


