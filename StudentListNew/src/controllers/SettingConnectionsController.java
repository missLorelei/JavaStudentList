package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.StaticData;

public class SettingConnectionsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextField editPort;
    
    @FXML
    private TextField editIP;

    @FXML
    private Button btnOk;

    @FXML
    void clickOk(ActionEvent event) {
        
        Stage stage = (Stage) btnOk.getScene().getWindow();
        //close form Message
        stage.close();
    }

    @FXML
    void initialize() {
        assert btnOk != null : "fx:id=\"btnOk\" was not injected: check your FXML file 'SettingConnections.fxml'.";

    }
}
