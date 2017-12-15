/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.squarewins;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author kbwschuler
 */
public class Main extends Application {

    public static AnchorPane root;

    @Override
    public void start(Stage stage) throws Exception {

        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        Group root = new Group();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        /*Line line = new Line(200, 20, 13, 50);
        root.getChildren().add(line);*/
        System.out.println("this is root: "+this.root);
        test();
    }

    public void test() {
        System.out.println("hello");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
