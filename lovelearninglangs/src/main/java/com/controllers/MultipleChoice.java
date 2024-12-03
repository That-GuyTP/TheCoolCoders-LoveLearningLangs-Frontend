package com.controllers;

import java.io.IOException;

import javafx.fxml.FXML;

public class MultipleChoice {


    @FXML
    private void SwitchToNexQuestion() throws IOException{
        test();
    }

    private void test(){
        System.out.println("You have clicked an answer choice!");
    }
}
