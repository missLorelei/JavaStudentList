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
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.Student;
import model.StaticData;

/**
 * FXML Controller class
 *
 * @author Карина
 */
public class StudentListEditController implements Initializable  //controller with form edit
{
    private Student st = new Student();
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
    private Button bEdit;
    @FXML
    private DatePicker dateOfDelay;
    @FXML
    void onClickEdit(ActionEvent event)    // method that run when we click on edit button
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
       // Stage stage = (Stage) bEdit.getScene().getWindow();
        if (!isEmptyFields())
        {
            st.setName(nameField.getText());
            st.setSurname(surnameField.getText());
            st.setBirthday(birthday.getValue().toString());
            st.setDepartment(departmentChoiceBox.getSelectionModel().getSelectedItem().toString());
            st.setSpeciality(specialityChoiceBox.getSelectionModel().getSelectedItem().toString());
            st.setCourse(courseChoiceBox.getSelectionModel().getSelectedItem().toString());
            st.setGroup(groupField.getText());
            st.setDelayDate(dateOfDelay.getValue().toString());
            
            StaticData.data.set(StaticData.selectedRow, st);
            //StaticData.data.set(st);
            Stage stage = (Stage) bEdit.getScene().getWindow();
            //close form Message
            //stage.close();
            try {
                Class.forName("org.h2.Driver");
                Connection conn = DriverManager.getConnection("jdbc:h2:~/DB");
                Statement stat = conn.createStatement();          
                
                String sql = "UPDATE students SET name=?,surname=?,birthday=?,department=?,speciality=?,course=?,groupz=?,delayz=? WHERE id = ?";
                
                PreparedStatement pstmt = conn.prepareStatement(sql); 
               
                pstmt.setString(1, st.getName());
                pstmt.setString(2, st.getSurname());
                pstmt.setString(3, st.getBirthday());
                pstmt.setString(4, st.getDepartment());
                pstmt.setString(5, st.getSpeciality());
                pstmt.setString(6, st.getCourse());
                pstmt.setString(7, st.getGroup());
                pstmt.setString(8, st.getDelayDate());
                pstmt.setInt(9, st.getId());
                
                pstmt.executeUpdate();
                stat.close();
                conn.close();
  
            } catch (ClassNotFoundException ex) 
            {
                Logger.getLogger(StudentListAddController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) 
            {
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
       st = StaticData.data.get(StaticData.selectedRow);
        nameField.setText(st.getName());
        surnameField.setText(st.getSurname());
        groupField.setText(st.getGroup());
        birthday.setValue(LocalDate.now());
        dateOfDelay.setValue(LocalDate.now());
        
        ObservableList<String> listOfCourse = FXCollections.observableArrayList();
        listOfCourse.add("First");
        listOfCourse.add("Second");
        listOfCourse.add("Third");
        listOfCourse.add("Fourth");
        listOfCourse.add("Fifth");
        courseChoiceBox.setItems(listOfCourse);
        courseChoiceBox.setValue(st.getCourse());
        
        ObservableList<String> listOfDepartment = FXCollections.observableArrayList();
        listOfDepartment.add("Computer science");
        listOfDepartment.add("Physics");
        listOfDepartment.add("Mathematics");
        departmentChoiceBox.setItems(listOfDepartment);
        departmentChoiceBox.setValue(st.getDepartment());
        
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
        
       if (st.getDepartment().equals("Computer science"))
             {
                departmentChoiceBox.getSelectionModel().select("Computer science");
                specialityChoiceBox.setItems(listOfComputerScines);
                specialityChoiceBox.setValue(st.getSpeciality());
             } 
       
       if (st.getDepartment().equals("Mathematics"))
             {
                departmentChoiceBox.getSelectionModel().select("Mathematics");
                specialityChoiceBox.setItems(listOfMath);
                specialityChoiceBox.setValue(st.getSpeciality());
             } 
     
       if (st.getDepartment().equals("Physics"))
             {
                departmentChoiceBox.getSelectionModel().select("Physics");
                specialityChoiceBox.setItems(listOfPhysics);
                specialityChoiceBox.setValue(st.getSpeciality());
             } 
       
        departmentChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() 
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) 
            {
                 String tmp = (String) departmentChoiceBox.getItems().get((Integer) number2);
                 System.out.println(tmp);
                 if (tmp.equals("Department of Computer Science"))
                 {
                    specialityChoiceBox.setItems(listOfComputerScines);
                    specialityChoiceBox.getSelectionModel().select("PZKS");
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
    

