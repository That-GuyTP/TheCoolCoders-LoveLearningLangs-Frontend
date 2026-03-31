package com.application;

import java.io.IOException;

import com.model.DataConstants;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        DataConstants.getDataDirectory();
        scene = new Scene(centeredContent(loadFXML("startup")), 840, 680);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(centeredContent(loadFXML(fxml)));
    }

    public static void setRoot(Parent root) {
        scene.setRoot(centeredContent(root));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static Parent centeredContent(Parent root) {
        StackPane wrapper = new StackPane();
        wrapper.setAlignment(Pos.CENTER);
        if (root instanceof Region region) {
            region.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        }
        wrapper.getChildren().setAll(root);
        return wrapper;
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
}
