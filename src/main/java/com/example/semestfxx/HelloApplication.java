package com.example.semestfxx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


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

 /*   public static void main(String args[]){
        launch(args);
}

    @Override
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
   @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
        Game klimaspillet = new Game();
    }

    public static void main(String[] args) {
        launch();
    }


}