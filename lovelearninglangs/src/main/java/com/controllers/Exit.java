package com.controllers;

import java.io.IOException;
import com.application.App;
import javafx.fxml.FXML;

public class Exit {
    
    /* Exits the app when clicked.
     * 
     */
    @FXML
    private void exitApp() throws IOException {
        App.close();
    }
}
