package com.example.semestfxx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController implements Initializable {



    @FXML
    private VBox rootVbox;
    @FXML
    private Button ButtonStanden_1, ButtonKøkken_1;

    private Stage stage;
    private Scene scene;
    private Parent root;




    @Override
    public void initialize(URL url, ResourceBundle rb){

    }





    public void loadSecond(ActionEvent event) throws IOException {

        System.out.println("Scene 2");
        VBox vbox = FXMLLoader.load(getClass().getResource("bedroom.fxml"));
        rootVbox.getChildren().setAll(vbox);

    }


    public void loadKitchen() throws IOException {
        System.out.println("Scene 3");
        VBox vbox = FXMLLoader.load(getClass().getResource("bedroom.fxml"));
        rootVbox.getChildren().setAll(vbox);
    }

    public void switchToStranden(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stranden.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToKøkken(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("køkken.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}


