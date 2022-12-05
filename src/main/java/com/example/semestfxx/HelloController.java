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

    public StackPane bedroomPane;
    public StackPane bathroomPane;
    public StackPane kitchenPane;
    public StackPane cityPane;
    public StackPane beachPane;
    private Game game;
    private Room currentRoom;
    public Item currentItem;

    @FXML
    private StackPane rootPane;

        //Items
    @FXML

    private ImageView soveværelseLampeTændt, soveværelseLampeSlukket, computerTændt, computerSlukket, radiator, vindueÅben, vindueLukket,
                badeværelseLysSlukket, badeværelseLysTændt, vandhaneTændt, vandhaneSlukket, bad, badShower, badTub,
                køkkenLampeTændt, køkkenLampeSlukket, tvTændt, tvSlukket, køleskabÅbnet, salat, burger, komfurTændt, komfurPande,
                cykle, bil;

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
    private Text display, highscoreLoader, guiScore2;



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

//    public void loadBedroomStart(ActionEvent event) throws IOException {
//        System.out.println("Start Bedroom");
//        StackPane pane = FXMLLoader.load(getClass().getResource("worldStart.fxml"));
//        rootPane.getChildren().setAll(pane);
//        game.currentRoom = game.rooms.get(0);
//    }
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
    }
    public void loadKitchen(MouseEvent event) throws IOException {
        bedroomPane.setVisible(false);
        cityPane.setVisible(false);
        kitchenPane.setVisible(true);
    }
    public void loadBathroom(MouseEvent event) throws IOException {
        bedroomPane.setVisible(false);
        bathroomPane.setVisible(true);
    }
    public void loadCity(MouseEvent event) throws IOException {
        cityPane.setVisible(true);
        beachPane.setVisible(false);
        kitchenPane.setVisible(false);
    }
    public void loadBeach(MouseEvent event) throws IOException {
        beachPane.setVisible(true);
        cityPane.setVisible(false);
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
        System.out.println(game.currentItem.toggleState);
    }

    // Soveværelse: Tænder/Slukker computer
    @FXML
    void showComputer (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("computer");
        game.switchItemState();

        if(game.currentItem.getItemState()==true) {
            computerSlukket.setVisible(false);
            computerTændt.setVisible(true);
        } else {
            computerSlukket.setVisible(true);
            computerTændt.setVisible(false);
        }
        System.out.println(game.currentItem.toggleState);
    }

    //Soveværelse: Åbner/Lukker vindue
    @FXML
    void showWindow (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("vindue");
        game.switchItemState();

        if(game.currentItem.getItemState()==true) {
            vindueLukket.setVisible(false);
            vindueÅben.setVisible(true);
        } else {
            vindueLukket.setVisible(true);
            vindueÅben.setVisible(false);
        }
        System.out.println(game.currentItem.toggleState);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Badeværelse: Tænder/Slukker badeværelse lys
    @FXML
    void toggleBadeværelseLys (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("badeværelselys");
        game.switchItemState();

        if(game.currentItem.getItemState()==true) {
            badeværelseLysSlukket.setVisible(false);
            badeværelseLysTændt.setVisible(true);
        } else {
            badeværelseLysSlukket.setVisible(true);
            badeværelseLysTændt.setVisible(false);
        }
        System.out.println(game.currentItem.toggleState);
    }

    // Badeværelse: Tænder/Slukker vandhane
    @FXML
    void toggleVandhane (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("vandhane");
        game.switchItemState();

        if(game.currentItem.getItemState()==true) {
            vandhaneSlukket.setVisible(false);
            vandhaneTændt.setVisible(true);
        } else {
            vandhaneSlukket.setVisible(true);
            vandhaneTændt.setVisible(false);
        }
        System.out.println(game.currentItem.toggleState);
    }

    // Badeværelse: Bad choice
    @FXML
    void choiceBad (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
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
    void choiceBruser (MouseEvent event) {
        game.plus_sum_score();
        game.switchItemState();
        badChoice.setVisible(false);
        badShower.setVisible(true);
    }
    @FXML
    void choiceBadekar (MouseEvent event) {
        game.switchItemState();
        badChoice.setVisible(false);
        badTub.setVisible(true);
    }


    //------------------------------------------------------------------------------------------------------------------
    // Køkken: Tænder/Slukker køkken lys
    @FXML
    void toggleKLamp (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("køkkenlampe");
        game.switchItemState();

        if(køkkenLampeSlukket.isVisible()==true) {
            køkkenLampeSlukket.setVisible(false);
            køkkenLampeTændt.setVisible(true);
        } else {
            køkkenLampeSlukket.setVisible(true);
            køkkenLampeTændt.setVisible(false);
        }
        System.out.println(game.currentItem.toggleState);
    }

    // Køkken: Tænder/Slukker TV
    @FXML
    void toggleTv (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("tv");
        game.switchItemState();

        if(tvSlukket.isVisible()==true) {
            tvSlukket.setVisible(false);
            tvTændt.setVisible(true);
        } else {
            tvSlukket.setVisible(true);
            tvTændt.setVisible(false);
        }
            System.out.println(game.currentItem.toggleState);
    }

    // Køkken Choice: Køleskab
    @FXML
    void choiceKøleskab (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("køleskab");
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
    @FXML
    void choiceSalat (MouseEvent event) {
        game.switchItemState();
        game.plus_sum_score();
        køleskabChoice.setVisible(false);
        køleskabÅbnet.setVisible(false);
        salat.setVisible(true);
    }
    @FXML
    void choiceBurger (MouseEvent event) {
        game.switchItemState();
        køleskabChoice.setVisible(false);
        køleskabÅbnet.setVisible(false);
        burger.setVisible(true);
    }

    // Køkken Choice: Komfur
    @FXML
    void choiceKomfur (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("komfur");
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
    @FXML
    void choiceOvenen (MouseEvent event) {
        game.switchItemState();
        komfurChoice.setVisible(false);
        komfurTændt.setVisible(true);
    }
    @FXML
    void choiceStegepande (MouseEvent event) {
        game.plus_sum_score();
        game.switchItemState();
        komfurChoice.setVisible(false);
        komfurPande.setVisible(true);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Byen Choice: Transport
    @FXML
    void choiceTransport (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
        game.currentItem = game.currentRoom.getItem("transport");
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
    @FXML
    void choiceCykle (MouseEvent event) {
        game.switchItemState();
        game.plus_sum_score();
        transportChoice.setVisible(false);
        cykle.setVisible(true);
    }
    @FXML
    void choiceBil (MouseEvent event) {
        game.switchItemState();
        transportChoice.setVisible(false);
        bil.setVisible(true);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Strand:
    @FXML
    void npcQuiz (MouseEvent event) {
        game.currentRoom = game.rooms.get(0);
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
    }
    @FXML
    void npcQuizT (MouseEvent event) {
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


