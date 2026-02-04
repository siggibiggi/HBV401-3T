package com.example.hbv401g;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;
import java.util.List;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField input;

    @FXML
    private ListView<String> output;

    List<String> data = Arrays.asList("Apple", "Banana", "Cherry", "Date");

    @FXML
    public void initialize() {
        for (String item : data) {
            output.getItems().add(item);
        }
        input.textProperty().addListener((obs, oldValue, newValue) -> {
            output.getItems().clear();

            if (newValue == null || newValue.isBlank()) {
                for (String item : data) {
                    output.getItems().add(item);
                }
                return;
            }

            for (String item : data) {
                if (item.toLowerCase().contains(newValue.toLowerCase())) {
                    output.getItems().add(item);
                }
            }
        });
    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
