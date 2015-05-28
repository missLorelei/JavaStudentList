/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.io.*;
import java.net.*;
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
            
            
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            
            /*

            OutputStream out = socket.getOutputStream();       
            PrintWriter output = new PrintWriter(out);  
            */
            String line = "Helo!";
            int i = 0;
            while (i < 3) {
                System.out.println("Sending this line to the server...");
                out.writeUTF(line); // отсылаем введенную строку текста серверу.
                out.flush(); // заставляем поток закончить передачу данных.
                line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                System.out.println("The server was very polite. It sent me this : " + line);
                System.out.println();
                line = json.toString();
                i++;

                if (i == 2)
                line = "GoodBye!";
                }
                socket.close();

          /*     output.println(json);   
                output.flush();
                output.close();*/

                socket.close();  
        }
        catch(SocketException e2){
            
        }
                                                 
    }
}
