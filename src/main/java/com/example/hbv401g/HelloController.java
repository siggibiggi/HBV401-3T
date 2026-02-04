package com.example.hbv401g;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField searchinput;

    @FXML
    private TextField inputinput;

    @FXML
    private ListView<String> output;

    List<String> data = new ArrayList<>();

    @FXML
    public void initialize() {
        createNewTable();
        loadDataFromDatabase();

        for (String item : data) {
            output.getItems().add(item);
        }
        searchinput.textProperty().addListener((obs, oldValue, newValue) -> {
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

    private void createNewTable() {
        String url = "jdbc:sqlite:test.db";
        String sql = """
                CREATE TABLE IF NOT EXISTS test_table (
                 id INTEGER PRIMARY KEY,
                 message TEXT NOT NULL
                );""";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadDataFromDatabase() {
        String url = "jdbc:sqlite:test.db";
        String query = "SELECT message FROM test_table";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                data.add(rs.getString("message"));
            }
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    private void insertData(String newMessage) {
        String url = "jdbc:sqlite:test.db";
        String sql = "INSERT INTO test_table(message) VALUES(?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newMessage);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        String newText = inputinput.getText();

        if (newText != null && !newText.isBlank()) {
            insertData(newText);

            data.add(newText);
            output.getItems().add(newText);

            inputinput.clear();
            welcomeText.setText("added");
        } else {
            welcomeText.setText("type something");
        }
    }
}
