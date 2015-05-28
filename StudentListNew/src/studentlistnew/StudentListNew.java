/*
 * Karin Wittal 
 * Group 243
 */

package studentlistnew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Карина
 */
public class StudentListNew extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Artem Dzhuran form");
        Image icon = new Image(getClass().getResourceAsStream("/images/logo.png"));
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Class.forName("org.h2.Driver");
        //Connection conn = DriverManager.getConnection("jdbc:h2:~/DB");
        //Statement stat = conn.createStatement();
        //stat.execute("drop table if exists students");

       // stat.execute("create table students(id int primary key auto_increment, name varchar(255), surname varchar(255),"
              //  + "birthday varchar(50), department varchar(255), speciality varchar(255), course varchar(20), groupz varchar(255), delayz varchar(100) )");
        //stat.execute("insert into test1 values(1, 'Hello')");
       // ResultSet rs;
        //rs = stat.executeQuery("select * from students");
        //while (rs.next()) {
        //    System.out.println(rs.getString("name"));
        //}
        //stat.close();
        //conn.close();
        
        
        launch(args);
    }
    
}
