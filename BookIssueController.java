package com.example.employeemanagementsystem1;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class BookIssueController implements Initializable {

    @FXML
    private Button issueButton;

    @FXML
    private TextField bookIdField;

    @FXML
    private TextField daysField;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField mobileNumberField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField authorField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void handleIssueButtonAction(ActionEvent event) {
        String query = "INSERT INTO book_issues (book_id, no_of_days, customer_name, mobile_number, email, author) VALUES ('" +
            bookIdField.getText() + "'," +
            daysField.getText() + ",'" +
            customerNameField.getText() + "','" +
            mobileNumberField.getText() + "','" +
            emailField.getText() + "','" +
            authorField.getText() + "')";
        System.out.println(query);
        executeQuery(query);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CONFIRMATION");
        alert.setContentText("Book Issued Successfully!");
        alert.showAndWait().ifPresent(rs - > {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/daproject", "root", "");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    @FXML
    void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }

}
