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
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javafx.scene.shape.Line;

/**
 *
 * @author kbwschuler
 */
public class FXMLDocumentController implements Initializable {

    private ArrayList<Point> winPoints = new ArrayList<>();
    private Point point, nextPoint;
    private SquareWins sw = new SquareWins();

    private boolean blue = true; //true=blue | false=red
    private RadioButton[] allPoints;
    private int score1, score2;

    private ArrayList<Line> lines = new ArrayList<Line>();
    @FXML
    private Label p1,p2;
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
    @FXML
    private RadioButton color;

    private void createPoint(RadioButton rbutton) {
        rbutton.setUserData(new Point(rbutton.getLayoutX(), rbutton.getLayoutY()));
    }

    public void createField() {
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
        createPoint(color);
        color.setDisable(true);
        allPoints = new RadioButton[]{
            rb1, rb2, rb3, rb4, rb5,
            rb6, rb7, rb8, rb9, rb10,
            rb11, rb12, rb13, rb14, rb15,
            rb16, rb17, rb18, rb19, rb20,
            rb21, rb22, rb23, rb24, rb25
        };
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        color.setStyle("-fx-mark-color: blue;");
        color.setSelected(true);
        createField();
        p1.setText("Player 1:");
        p2.setText("Player 2:");
        p1.setTextFill(Color.BLUE);
        p2.setTextFill(Color.RED);
        for(RadioButton rad : allPoints) {
            rad.setDisable(false);
            rad.setSelected(false);
        }
    }

    @FXML
    private void pointSelectionAction(ActionEvent event) throws InterruptedException {
        RadioButton source = (RadioButton) event.getSource();
        point = (Point) source.getUserData();

        if (source.isSelected()) {

            int x = (int) ((point.getX() - 60) / 30), y = (int) ((point.getY() - 60) / 30);
            System.out.println("Koordinate: " + x + "|" + y);

            if (blue) {
                source.setStyle("-fx-mark-color: blue;");
                color.setStyle("-fx-mark-color: red;");
                System.out.println("blue");
                sw.addPoint(new ch.kbw.Model.Point(x, y, true));

                source.setDisable(true);
                sw.updateVectors();
                if (sw.checkwin().equals("blue")) {
                    score1++;
                    p1.setText("Player1: " + score1);
                    drawLine();
                    msg("BLUE", "RED");
                }
                //algorithm(red);
                blue = false;
            } else {
                source.setStyle("-fx-mark-color: red;");
                color.setStyle("-fx-mark-color: blue;");
                System.out.println("red");
                sw.addPoint(new ch.kbw.Model.Point(x, y, false));

                source.setDisable(true);
                sw.updateVectors();
                if (sw.checkwin().equals("red")) {
                    drawLine();
                    score2++;
                    p2.setText("Player2: " + score2);
                    msg("RED", "BLUE");
                }
                //algorithm(blue); 
                blue = true;
            }

        }

    }
    
    public void removeLines(){
        //delete all lines from anchorpane
        ArrayList<Node> deleteList = new ArrayList<>();
        for (Node c : Main.root.getChildren()) {
            if (c.getTypeSelector().equals("Line")) {
                deleteList.add(c);
            }
        }
        Main.root.getChildren().removeAll(deleteList);
    }
    public void diableAutoFocus(){
        
        ArrayList<Node> rb = new ArrayList<>();
        for (Node c : Main.root.getChildren()) {
            if (c.getTypeSelector().equals("RadioButton")) {
                rb.add(c);
            }
        }
    }

    //maybe place method into go() method --> rename go() to restart()
    public void resetGame() {
        for (RadioButton rb : allPoints) {
            rb.setDisable(false);
            rb.setSelected(false);
            rb.setMnemonicParsing(false);
        }
        removeLines();
        //Main.root.getChildren().remove(lines);
        lines.clear();
        winPoints.clear();
        sw.reset();
        createField();
        
    }

    public void msg(String color, String color2) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Win!");
        alert.setHeaderText("Congratulation: " + "YOU WIN! " + color);
        alert.setContentText(color2 + ", now it's your turn!");

        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");

                Task<Void> sleeper = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                        }
                        return null;
                    }
                };
                sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {
                        resetGame();
                    }
                });
                new Thread(sleeper).start();
            }
        });
    }

    @FXML
    private void replay(ActionEvent event) {
        resetGame();
    }

    @FXML
    private void help(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Instruction!");
        alert.setHeaderText("Thank you for playing \"SQUARE WINS\" Insired by \"https://wild.maths.org/creating-squares\"" );
        alert.setContentText("Goal: Draw a square and you win. \n"
                + "A quare has 4 equals edges and 4 right angles.\n\n\n"
                + "Made by Andy Bui, Ramin Farsinejad and Adel Patkovic");
        alert.setResizable(false);
        
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("-Left Help-");
            }
        });
    }

    @FXML
    private void reset(ActionEvent event) {
        score1 = 0;
        score2 = 0;
        p1.setText("Player 1:");
        p2.setText("Player 2:");
        resetGame();
    }

    public void updateWinPoints() {
        for (int i = 0; i < sw.getDrawPoints().length; i++) {
            winPoints.add(new Point(1.0 * (sw.getDrawPoints()[i].getX() * 30 + 60), 1.0 * (sw.getDrawPoints()[i].getY() * 30 + 60)));
        }
    }

    private void drawLine() throws InterruptedException {
        updateWinPoints();
        for (int i = 0; i < winPoints.size(); i++) {
            point = winPoints.get(i);
            if (i != winPoints.size() - 1) {
                nextPoint = winPoints.get(i + 1);
            } else {
                nextPoint = winPoints.get(0);
            }
            lines.add(new Line(point.getX() + 6, point.getY() + 9, nextPoint.getX() + 6, nextPoint.getY() + 9));
            System.out.println(point.getX() + " " + point.getY() + " " + nextPoint.getX() + " " + nextPoint.getY());
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
