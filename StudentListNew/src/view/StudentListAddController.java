/*
 * Karin Wittal 
 * Group 243
 */

package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.StaticData;
import model.Student;



/**
 * FXML Controller class
 *
 * @author Карина
 */
public class StudentListAddController  implements Initializable
{
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private DatePicker birthday;
    @FXML
    private ChoiceBox<String> departmentChoiceBox;
    @FXML
    private ChoiceBox<String> specialityChoiceBox;
    @FXML
    private ChoiceBox<String> courseChoiceBox;
    @FXML
    private TextField groupField;
    @FXML
    private Button bAdd;
    @FXML
    private DatePicker dateOfDelay;
    
    
    @FXML
     void onClickAdd(ActionEvent event)
    {
        if(!nameField.getText().matches("^\\D*$"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Name can not contain numbers.");
            alert.setContentText("Please check and try again!");

            alert.showAndWait();
            return;
        }
        else
        {
            //не містить
        }
        
        
        if(!surnameField.getText().matches("^\\D*$"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Name can not contain numbers.");
            alert.setContentText("Please check and try again!");

            alert.showAndWait();
            return;
        }
        else
        {
            //не містить
        }
        
        if (!isEmptyFields())
        {
            String department = (String) departmentChoiceBox.getSelectionModel().getSelectedItem().toString();
            String speciality = (String) specialityChoiceBox.getSelectionModel().getSelectedItem().toString();
            String course = (String) courseChoiceBox.getSelectionModel().getSelectedItem().toString();

            Student st = new Student(nameField.getText(),
                                 surnameField.getText(),
                                 birthday.getValue().toString(),
                                 department,
                                 speciality,
                                 course,
                                 groupField.getText(),
                                 dateOfDelay.getValue().toString());

            StaticData.data.add(st);
            Stage stage = (Stage) bAdd.getScene().getWindow();
            
            try {
                Class.forName("org.h2.Driver");
                Connection conn = DriverManager.getConnection("jdbc:h2:~/DB");
                Statement stat = conn.createStatement();        
                
                String sql = "INSERT INTO students (name,surname,birthday,department,speciality,course,groupz,delayz) "
                        + "VALUES(?,?,?,?,?,?,?,?)";
                
                PreparedStatement pstmt = conn.prepareStatement(sql); 
                pstmt.setString(1, st.getName());
                pstmt.setString(2, st.getSurname());
                pstmt.setString(3, st.getBirthday());
                pstmt.setString(4, st.getDepartment());
                pstmt.setString(5, st.getSpeciality());
                pstmt.setString(6, st.getCourse());
                pstmt.setString(7, st.getGroup());
                pstmt.setString(8, st.getDelayDate());

                pstmt.executeUpdate();
                stat.close();
                conn.close();
                
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StudentListAddController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(StudentListAddController.class.getName()).log(Level.SEVERE, null, ex);
            }

            stage.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill in all the fields");
            alert.setContentText("Please check and try again!");
        }
    }
       
        private boolean isEmptyFields()
        {
        if (nameField.getText().isEmpty() ||
            surnameField.getText().isEmpty() || groupField.getText().isEmpty()
                || birthday.getValue().toString().equals("")||dateOfDelay.getValue().toString().equals(""))
                //fields is empty
                return true;
        else
            return false;

        }
        
        @Override
        public void initialize(URL url, ResourceBundle rb) 
        {
        ObservableList<String> listOfComputerScines = FXCollections.observableArrayList();
        listOfComputerScines.add("Software engineering");
        listOfComputerScines.add("Computer engineering");
        listOfComputerScines.add("Computer science");

        ObservableList<String> listOfMath = FXCollections.observableArrayList();
        listOfMath.add("Department of Applied Mathematics");
        listOfMath.add("Department of Algebra and Computer Science");
        listOfMath.add("Department of Mathematical Analysis");

        ObservableList<String> listOfPhysics = FXCollections.observableArrayList();
        listOfPhysics.add("Department of Physics");
        listOfPhysics.add("Department of Theoretical Physics");
        listOfPhysics.add("Department of Solid State Physics");
        listOfPhysics.add("Department of Physics of Semiconductors and Nanostructures");



        departmentChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
             String tmp = (String) departmentChoiceBox.getItems().get((Integer) number2);
             System.out.println(tmp);
             if (tmp.equals("Computer science"))
             {
                specialityChoiceBox.setItems(listOfComputerScines);
                specialityChoiceBox.getSelectionModel().select("Software engineering");
             }
             if (tmp.equals("Mathematics"))
             {
                specialityChoiceBox.setItems(listOfMath);
                specialityChoiceBox.getSelectionModel().select("Department of Applied Mathematics");
             }

             if (tmp.equals("Physics"))
             {
                specialityChoiceBox.setItems(listOfPhysics);
                specialityChoiceBox.getSelectionModel().select("Department of Physics");
             }

      }
    });    
    } 
}       
        

    
    
  
    

