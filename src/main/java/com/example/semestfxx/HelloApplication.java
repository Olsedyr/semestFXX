package com.example.semestfxx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class HelloApplication extends Application {

    Game klimaspillet;


/*    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox layout = new VBox();
        VBox layout2 = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 1280, 720);
        Scene scene2 = new Scene(layout2, 1280, 720);

        Label label1 = new Label("This is the First Scene");
        Label label2 = new Label("This is the Second Scene");

        Button button = new Button("PLAY");
        button.setOnAction(e -> primaryStage.setScene(scene2));

        Button button2 = new Button("MainMenu");
        button2.setOnAction(e -> primaryStage.setScene(scene));

        layout.getChildren().addAll(label1, button);
        layout2.getChildren().addAll(label2, button2);

        primaryStage.setTitle("KLIMASPILLET");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
*/

//    public void play() {
//        printWelcome();
//
//        boolean finished = false;
//        while (!finished) {
//            Command command = parser.getCommand();
//            finished = processCommand(command);
//        }
//        System.out.println("Tak fordi du spillede med. Vi ses!.");
//    }
//
//    private void printWelcome() {
//        System.out.println();
//        System.out.println("###### Velkommen til Klimaspillet! ######");
//        System.out.println("Her i Klimaspillet, påvirker dine valg klimaet, så prøv dit bedste for at hjælpe klimaet!");
//        System.out.println("Du starter med 0 point, og dit mål er at få så mange point som muligt, ud fra dine klimabevidste handlinger.");
//        System.out.println();
//        System.out.println("Du kan skrive '" + Commands.GO + " + [udgange]' at gå til forskellige steder, '"
//                + Commands.LOOK + "' eller '" + Commands.LOOK + " + [objekt]' at kigge rundt eller på et objekt, \n'"
//                + Commands.USE + " + [objekt]' at at interagere med et objekt og '" + Commands.Inventory + "' for at se hvad du har samlet op.");
//        System.out.println("Skrive '" + Commands.HELP + "' hvis du har brug for hjælp.");
//        System.out.println("Når du ønkser at lukke programmet skal du skrive 'afslut'.");
//
//
//        //Læser score.txt filen
//        try {
//            Scanner reader = new Scanner(klimaspillet.scoreFile);
//            System.out.println("Din sidste score var: "+reader.nextLine());
//        } catch (FileNotFoundException e) {
//            System.out.println("Kan ikke finde scorefil");
//            throw new RuntimeException(e);
//        }
//
//
//        System.out.println("Held og lykke! :P");
//        System.out.println();
//        System.out.println("Du vågner op fra din middagslur, det er eftermiddag.");
//        System.out.println(klimaspillet.getRoomDescription());
//    }



   @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
        klimaspillet = new Game();
        System.out.println("Game was made!");
    }

    public static void main(String[] args) {
        launch();
    }

}