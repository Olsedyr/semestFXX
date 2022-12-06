package com.example.semestfxx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.layout.StackPane;
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
   @Override
    public void start(Stage primaryStage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
       Scene scene = new Scene(root, 1280, 720);
       primaryStage.setScene(scene);
       primaryStage.show();
       primaryStage.setTitle("Klimaspillet");
   }

    public static void main(String[] args) {
        launch();
    }

}