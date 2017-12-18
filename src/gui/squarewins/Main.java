package gui.squarewins;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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
        root.addEventFilter(KeyEvent.ANY, e -> {
            // handle keyboard event...
            e.consume();
        });

        stage.setTitle("Square Wins");
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
