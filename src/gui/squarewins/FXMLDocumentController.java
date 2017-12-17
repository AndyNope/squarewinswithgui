/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.squarewins;

import ch.kbw.Model.SquareWins;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javafx.scene.shape.Line;

/**
 *
 * @author kbwschuler
 */
public class FXMLDocumentController implements Initializable {

    private ArrayList<Point> bluePoints = new ArrayList<>();
    private ArrayList<Point> redPoints = new ArrayList<>();
    private Point point, nextPoint;
    private SquareWins sw = new SquareWins();
   
    private boolean blue = true; //true=blue | false=red
    private RadioButton[] allPoints;

    private ArrayList<Line> lines = new ArrayList<Line>();
    @FXML
    private AnchorPane anchPane;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private RadioButton rb4;
    @FXML
    private RadioButton rb5;
    @FXML
    private RadioButton rb6;
    @FXML
    private RadioButton rb7;
    @FXML
    private RadioButton rb8;
    @FXML
    private RadioButton rb9;
    @FXML
    private RadioButton rb10;
    @FXML
    private RadioButton rb11;
    @FXML
    private RadioButton rb12;
    @FXML
    private RadioButton rb13;
    @FXML
    private RadioButton rb14;
    @FXML
    private RadioButton rb15;
    @FXML
    private RadioButton rb16;
    @FXML
    private RadioButton rb17;
    @FXML
    private RadioButton rb18;
    @FXML
    private RadioButton rb19;
    @FXML
    private RadioButton rb20;
    @FXML
    private RadioButton rb21;
    @FXML
    private RadioButton rb22;
    @FXML
    private RadioButton rb23;
    @FXML
    private RadioButton rb24;
    @FXML
    private RadioButton rb25;

    private static void createPoint(RadioButton rbutton) {
        rbutton.setUserData(new Point(rbutton.getLayoutX(), rbutton.getLayoutY()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        anchPane = new AnchorPane();

        System.out.println(this.anchPane);
        createPoint(rb1);
        createPoint(rb2);
        createPoint(rb3);
        createPoint(rb4);
        createPoint(rb5);
        createPoint(rb6);
        createPoint(rb7);
        createPoint(rb8);
        createPoint(rb9);
        createPoint(rb10);
        createPoint(rb11);
        createPoint(rb12);
        createPoint(rb13);
        createPoint(rb14);
        createPoint(rb15);
        createPoint(rb16);
        createPoint(rb17);
        createPoint(rb18);
        createPoint(rb19);
        createPoint(rb20);
        createPoint(rb21);
        createPoint(rb22);
        createPoint(rb23);
        createPoint(rb24);
        createPoint(rb25);

        allPoints = new RadioButton[]{
            rb1, rb2, rb3, rb4, rb5,
            rb6, rb7, rb8, rb9, rb10,
            rb11, rb12, rb13, rb14, rb15,
            rb16, rb17, rb18, rb19, rb20,
            rb21, rb22, rb23, rb24, rb25
        };
    }

    @FXML
    private void pointSelectionAction(ActionEvent event) throws InterruptedException {
        RadioButton source = (RadioButton) event.getSource();
        point = (Point) source.getUserData();

        if (source.isSelected()) {

            int x = (int) ((point.getX() - 86) / 20), y = (int) ((point.getY() - 87) / 20);
            System.out.println("Koordinate: " + x + "|" + y);

            if (blue) {
                source.setStyle("-fx-mark-color: blue;");
                System.out.println("blue");
                sw.addPoint(new ch.kbw.Model.Point(x, y, true));

                bluePoints.add(point);
                source.setDisable(true);
                sw.updateVectors();
                if (sw.checkwin().equals("blue")) {
                    drawLine();
                    msg("BLUE", "RED");
                    resetGame();
                }
                //algorithm(red);
                blue = false;
            } else {
                source.setStyle("-fx-mark-color: red;");
                System.out.println("red");
                sw.addPoint(new ch.kbw.Model.Point(x, y, false));

                redPoints.add(point);
                source.setDisable(true);
                sw.updateVectors();
                if (sw.checkwin().equals("red")) {
                    drawLine();
                    msg("RED", "BLUE");
                    resetGame();
                }
                //algorithm(blue); 
                blue = true;
            }

        }
    }
    //maybe place method into go() method --> rename go() to restart()
    public void resetGame() {
        for (RadioButton rb : allPoints) {
            rb.setDisable(false);
            rb.setSelected(false);
        }
        sw.reset();
    }

    public void msg(String color, String color2) throws InterruptedException {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Win!");
        alert.setHeaderText("Congratulation: " + "YOU WIN! " + color);
        alert.setContentText(color2 + ", now it's your turn! The field is being deleted.");

        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
        Thread.sleep(800);
    }

    @FXML
    private void go(ActionEvent event) {

    }

    private void drawLine() throws InterruptedException {
        //Andys Funktion auskommentiert
        /*lines.add(new Line(sw.getDrawPoints()[0].getX() + 9, sw.getDrawPoints()[0].getY() + 9, sw.getDrawPoints()[1].getX() + 9, sw.getDrawPoints()[1].getY() + 9));
        lines.add(new Line(sw.getDrawPoints()[1].getX() + 9, sw.getDrawPoints()[1].getY() + 9, sw.getDrawPoints()[2].getX() + 9, sw.getDrawPoints()[2].getY() + 9));
        lines.add(new Line(sw.getDrawPoints()[2].getX() + 9, sw.getDrawPoints()[2].getY() + 9, sw.getDrawPoints()[3].getX() + 9, sw.getDrawPoints()[3].getY() + 9));
        lines.add(new Line(sw.getDrawPoints()[3].getX() + 9, sw.getDrawPoints()[3].getY() + 9, sw.getDrawPoints()[0].getX() + 9, sw.getDrawPoints()[0].getY() + 9));
        //System.out.println(point.getX() + " " + point.getY() + " " + nextPoint.getX() + " " + nextPoint.getY());

        selectedPoints.clear();
        addLineToGridPane();*/
        if (sw.checkwin().equals("blue")) {
            for (int i = 0; i < bluePoints.size(); i++) {
                point = bluePoints.get(i);
                if (i != bluePoints.size() - 1) {
                    nextPoint = bluePoints.get(i + 1);
                } else {
                    nextPoint = bluePoints.get(0);
                }
                lines.add(new Line(point.getX() + 9, point.getY() + 9, nextPoint.getX() + 9, nextPoint.getY() + 9));
                System.out.println(point.getX() + " " + point.getY() + " " + nextPoint.getX() + " " + nextPoint.getY());
            }
        } else {
            for (int i = 0; i < redPoints.size(); i++) {
                point = redPoints.get(i);
                if (i != redPoints.size() - 1) {
                    nextPoint = redPoints.get(i + 1);
                } else {
                    nextPoint = redPoints.get(0);
                }
                lines.add(new Line(point.getX() + 9, point.getY() + 9, nextPoint.getX() + 9, nextPoint.getY() + 9));
                System.out.println(point.getX() + " " + point.getY() + " " + nextPoint.getX() + " " + nextPoint.getY());
            }
        }

        addLineToGridPane();
    }

    public void addLineToGridPane() {
        Line line = new Line();
        line.setStrokeWidth(30.0);
        line.setStroke(Color.BLACK);
        for (int i = lines.size() - 1; (i >= 0) && ((line = lines.get(i)).getParent() == null); i--) {
            Main.root.getChildren().add(line);
            printChildrenOfGridPane();
        }
    }

    public void printChildrenOfGridPane() {
        for (Node c : Main.root.getChildren()) {
            System.out.println("Type of Children: " + c.getTypeSelector());
        }
    }

    private void nextP(ActionEvent event) {
        rb1.setStyle("blue");
    }
}
