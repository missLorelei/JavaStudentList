/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.StaticData;


public class SendStudentViewController implements Initializable {

    @FXML
    private TextField hostEdit;
    
    @FXML
    private TextField portEdit;
    
    @FXML
    private Button sendButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       String json = StaticData.jsonedStudent;
       
    }    
    
    @FXML
     void onClick(ActionEvent event) throws IOException
    {
        String json = StaticData.jsonedStudent;
        String host = hostEdit.getText();
        int port = Integer.valueOf(portEdit.getText());
        
        try {
            Socket socket = new Socket(host, port);   

            OutputStream out = socket.getOutputStream();       
            PrintWriter output = new PrintWriter(out);         

            output.println(json);   
            output.flush();
            output.close();

            socket.close();  
        }
        catch(SocketException e2){
            
        }
                                                 
    }
}
