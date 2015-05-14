/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class SendStudentViewController implements Initializable {

    @FXML
    private TextField hostEdit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Scene stageTheLabelBelongs = hostEdit.getScene();
       
        
    }    
    
}
