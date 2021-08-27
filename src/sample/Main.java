package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("BugTracker");
        primaryStage.setScene(new Scene(root, 1200, 800));
        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());// CSS file TODO remove <--
//        String css = this.getClass().getResource("style.css").toExternalForm();
        primaryStage.setResizable(false);
        primaryStage.show();


        DdHelper db = new DdHelper();
        if (db.open()) {
            db.createTables();
            db.close();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

//https://docs.oracle.com/javafx/2/fxml_get_started/fxml_tutorial_intermediate.htm
