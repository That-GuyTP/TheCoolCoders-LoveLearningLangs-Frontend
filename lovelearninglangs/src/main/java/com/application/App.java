package com.application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    //private static Scene previousScene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("startup"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        //previousScene = new Scene(scene.getRoot(), scene.getWidth(), scene.getHeight() );
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    /* @Author Thomas Peterson
     * A method to close the app when needed.
     */
    public static void close() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    /*
    public static void back() {
        if (previousScene != null) {
            scene.setRoot(previousScene.getRoot());
            previousScene = null;
        } else {
            System.out.println("Previous scene could not be set");
        }
        
    }
         */
} 