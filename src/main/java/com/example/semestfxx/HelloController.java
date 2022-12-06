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
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.lang.System.out;

public class HelloController implements Initializable {

    private Game game;
    private Room currentRoom;
    public Item currentItem;
    public StackPane bedroomPane;
    public StackPane bathroomPane;
    public StackPane kitchenPane;
    public StackPane cityPane;
    public StackPane beachPane;

    @FXML
    private StackPane rootPane;

        //Items
    @FXML

    private ImageView soveværelseLampeTændt, soveværelseLampeSlukket, computerTændt, computerSlukket, radiator, vindueÅben, vindueLukket,
                badeværelseLysSlukket, badeværelseLysTændt, vandhaneTændt, vandhaneSlukket, bad, badShower, badTub,
                køkkenLampeTændt, køkkenLampeSlukket, tvTændt, tvSlukket, køleskabÅbnet, salat, burger, komfurTændt, komfurPande,
                cykle, bil, sodavandsdåser, transport;;

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
    private ImageView silkepapir,
                pizzabakke, mælkekarton;
    @FXML
    private Button quitGame, helpGame;

    @FXML
    private Text display0, display1, display2, display3, display4, highscoreLoader, guiScore2;



    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("INIT");
        game = GameSingleton.getInstance().getGame();
        currentRoom = GameSingleton.getInstance().getGame().currentRoom;
        currentItem = GameSingleton.getInstance().getGame().currentItem;

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
    }
    public void loadKitchen(MouseEvent event) throws IOException {
        kitchenPane.setVisible(true);
        bedroomPane.setVisible(false);
        cityPane.setVisible(false);
        game.currentRoom = game.rooms.get(1);
    }
    public void loadBathroom(MouseEvent event) throws IOException {
        bedroomPane.setVisible(false);
        bathroomPane.setVisible(true);
        game.currentRoom = game.rooms.get(2);
    }
    public void loadCity(MouseEvent event) throws IOException {
        cityPane.setVisible(true);
        beachPane.setVisible(false);
        kitchenPane.setVisible(false);
        game.currentRoom = game.rooms.get(3);
    }
    public void loadBeach(MouseEvent event) throws IOException {
        beachPane.setVisible(true);
        cityPane.setVisible(false);
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
            showDescription0(game.currentItem);
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
            showNewPoints0(game.currentItem);
        }
    }

    // Soveværelse: Tænder/Slukker computer
    @FXML
    void showComputer (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("computer");

        if(event.getButton() == MouseButton.SECONDARY){
            showDescription0(game.currentItem);
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
            showNewPoints0(game.currentItem);
        }
    }

    //Soveværelse: Åbner/Lukker vindue
    @FXML
    void showWindow (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("vindue");

        if(event.getButton() == MouseButton.SECONDARY){
            showDescription0(game.currentItem);
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
            showNewPoints0(game.currentItem);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // Badeværelse: Tænder/Slukker badeværelse lys
    @FXML
    void toggleBadeværelseLys (MouseEvent event) {
        game.currentRoom = game.rooms.get(2);

        game.currentItem = game.currentRoom.getItem("badeværelseLys");
        if(event.getButton() == MouseButton.SECONDARY){
            showDescription2(game.currentItem);
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
            showNewPoints2(game.currentItem);
        }
    }

    // Badeværelse: Tænder/Slukker vandhane
    @FXML
    void toggleVandhane (MouseEvent event) {
        game.currentRoom = game.rooms.get(2);
        game.currentItem = game.currentRoom.getItem("vandhane");

        if(event.getButton() == MouseButton.SECONDARY){
            showDescription2(game.currentItem);
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
            showNewPoints2(game.currentItem);
        }
    }

    // Badeværelse: Bad choice
    @FXML
    void choiceBad (MouseEvent event) {
        game.currentRoom = game.rooms.get(2);
        game.currentItem = game.currentRoom.getItem("bad");

        if(event.getButton() == MouseButton.SECONDARY){
            showDescription2(game.currentItem);
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
        showNewPoints2(game.currentItem);
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
            showDescription1(game.currentItem);
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
            showNewPoints1(game.currentItem);
        }
    }

    // Køkken: Tænder/Slukker TV
    @FXML
    void toggleTv (MouseEvent event) {
        game.currentRoom = game.rooms.get(1);
        game.currentItem = game.currentRoom.getItem("tv");

        if(event.getButton() == MouseButton.SECONDARY){
            showDescription1(game.currentItem);
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
            showNewPoints1(game.currentItem);
        }
    }

    // Køkken Choice: Køleskab
    @FXML
    void choiceKøleskab (MouseEvent event) {
        game.currentRoom = game.rooms.get(1);
        game.currentItem = game.currentRoom.getItem("køleskab");

        if(event.getButton() == MouseButton.SECONDARY){
            showDescription1(game.currentItem);
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
        showNewPoints1(game.currentItem);
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
            showDescription1(game.currentItem);
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
        showNewPoints1(game.currentItem);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Byen Choice: Transport
    @FXML
    void choiceTransport (MouseEvent event) {
        game.currentRoom = game.rooms.get(3);
        game.currentItem = game.currentRoom.getItem("transport");

        if(event.getButton() == MouseButton.SECONDARY){
            showDescription3(game.currentItem);
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
        showNewPoints3(game.currentItem);
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
        game.currentRoom = game.rooms.get(4);
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
        showNewPoints4(game.currentItem);
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
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("silkepapir");
        game.switchItemState();
        silkepapir.setImage(null);
        showNewPoints0(game.currentItem);
    }
    public void collectPizzabakke(MouseEvent event) {
        game.currentRoom = game.rooms.get(1);
        game.currentItem = game.currentRoom.getItem("pizzabakke");
        game.switchItemState();
        pizzabakke.setImage(null);
        showNewPoints1(game.currentItem);
    }

    public void collectMælkekarton(MouseEvent event) {
        game.currentRoom = game.rooms.get(1);
        game.currentItem = game.currentRoom.getItem("mælkekarton");
        game.switchItemState();
        mælkekarton.setImage(null);
        showNewPoints1(game.currentItem);
    }

    public void collectSodavandsdåser (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("sodavandsdåser");
        game.switchItemState();
        sodavandsdåser.setImage(null);
        showNewPoints0(game.currentItem);
    }

    //------------------------------------------------------------------------------------------------------------------




    @FXML
    void quit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void help0(ActionEvent event) {
        if (display0.isVisible()==false) {
            display0.setVisible(true);
            display0.setText(GameText.textHelp());
        } else {
            display0.setText(GameText.textHelp());
        }
    }
    void help1(ActionEvent event) {
        if (display1.isVisible()==false) {
            display1.setVisible(true);
            display1.setText(GameText.textHelp());
        } else {
            display1.setText(GameText.textHelp());
        }
    }
    void help2(ActionEvent event) {
        if (display2.isVisible()==false) {
            display2.setVisible(true);
            display2.setText(GameText.textHelp());
        } else {
            display2.setText(GameText.textHelp());
        }
    }
    void help3(ActionEvent event) {
        if (display3.isVisible()==false) {
            display3.setVisible(true);
            display3.setText(GameText.textHelp());
        } else {
            display3.setText(GameText.textHelp());
        }
    }
    void help4(ActionEvent event) {
        if (display4.isVisible()==false) {
            display4.setVisible(true);
            display4.setText(GameText.textHelp());
        } else {
            display4.setText(GameText.textHelp());
        }
    }

    // Does not work fully yet
//    public void showRoomDescription(Room currentRoom) {
//        display.setText(currentRoom.getLongDescription());
//        display.setVisible(true);
//    }

    public void showDescription0(Item currentItem) {

        if (display0.isVisible()==false) {
            display0.setVisible(true);
            display0.setText(currentItem.getItemDescription());
        } else if(display0.isVisible() == true && currentItem.getItemDescription().equals(display0.getText())){
            display0.setVisible(false);
        } else {
            display0.setText(currentItem.getItemDescription());
        }
    }
    public void showDescription1(Item currentItem) {

        if (display1.isVisible()==false) {
            display1.setVisible(true);
            display1.setText(currentItem.getItemDescription());
        } else if(display1.isVisible() == true && currentItem.getItemDescription().equals(display1.getText())){
            display1.setVisible(false);
        } else {
            display1.setText(currentItem.getItemDescription());
        }
    }
    public void showDescription2(Item currentItem) {

        if (display2.isVisible()==false) {
            display2.setVisible(true);
            display2.setText(currentItem.getItemDescription());
        } else if(display2.isVisible() == true && currentItem.getItemDescription().equals(display2.getText())){
            display2.setVisible(false);
        } else {
            display2.setText(currentItem.getItemDescription());
        }
    }
    public void showDescription3(Item currentItem) {

        if (display3.isVisible()==false) {
            display3.setVisible(true);
            display3.setText(currentItem.getItemDescription());
        } else if(display3.isVisible() == true && currentItem.getItemDescription().equals(display3.getText())){
            display3.setVisible(false);
        } else {
            display3.setText(currentItem.getItemDescription());
        }
    }
    public void showDescription4(Item currentItem) {

        if (display4.isVisible()==false) {
            display4.setVisible(true);
            display4.setText(currentItem.getItemDescription());
        } else if(display4.isVisible() == true && currentItem.getItemDescription().equals(display4.getText())){
            display4.setVisible(false);
        } else {
            display4.setText(currentItem.getItemDescription());
        }
    }
    public void showNewPoints0(Item currentItem) {
        if (display0.isVisible()==false) {
            display0.setVisible(true);
            display0.setText(game.getAddedPoints());
        } else if(display0.isVisible() == true && game.getAddedPoints().equals(display0.getText())){
            display0.setVisible(false);
        } else {
            display0.setText(game.getAddedPoints());
        }
    }
    public void showNewPoints1(Item currentItem) {
        if (display1.isVisible()==false) {
            display1.setVisible(true);
            display1.setText(game.getAddedPoints());
        } else if(display1.isVisible() == true && game.getAddedPoints().equals(display1.getText())){
            display1.setVisible(false);
        } else {
            display1.setText(game.getAddedPoints());
        }
    }
    public void showNewPoints2(Item currentItem) {
        if (display2.isVisible()==false) {
            display2.setVisible(true);
            display2.setText(game.getAddedPoints());
        } else if(display2.isVisible() == true && game.getAddedPoints().equals(display2.getText())){
            display2.setVisible(false);
        } else {
            display2.setText(game.getAddedPoints());
        }
    }
    public void showNewPoints3(Item currentItem) {
        if (display3.isVisible()==false) {
            display3.setVisible(true);
            display3.setText(game.getAddedPoints());
        } else if(display3.isVisible() == true && game.getAddedPoints().equals(display3.getText())){
            display3.setVisible(false);
        } else {
            display3.setText(game.getAddedPoints());
        }
    }
    public void showNewPoints4(Item currentItem) {
        if (display4.isVisible()==false) {
            display4.setVisible(true);
            display4.setText(game.getAddedPoints());
        } else if(display4.isVisible() == true && game.getAddedPoints().equals(display4.getText())){
            display4.setVisible(false);
        } else {
            display4.setText(game.getAddedPoints());
        }
    }

    @FXML
    void showInventory0 (ActionEvent event) {
        if (display0.isVisible()==false) {
            display0.setVisible(true);
            display0.setText(GameText.textInventory());
        } else {
            display0.setVisible(false);
        }
    }
    void showInventory1 (ActionEvent event) {
        if (display1.isVisible()==false) {
            display1.setVisible(true);
            display1.setText(GameText.textInventory());
        } else {
            display1.setVisible(false);
        }
    }
    void showInventory2 (ActionEvent event) {
        if (display2.isVisible()==false) {
            display2.setVisible(true);
            display2.setText(GameText.textInventory());
        } else {
            display2.setVisible(false);
        }
    }
    void showInventory3 (ActionEvent event) {
        if (display3.isVisible()==false) {
            display3.setVisible(true);
            display3.setText(GameText.textInventory());
        } else {
            display3.setVisible(false);
        }
    }
    void showInventory4 (ActionEvent event) {
        if (display4.isVisible()==false) {
            display4.setVisible(true);
            display4.setText(GameText.textInventory());
        } else {
            display4.setVisible(false);
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


