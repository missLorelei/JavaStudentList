/*
 * Karin Wittal 
 * Group 243
 */

package view;

import controllers.Factory;
import controllers.STUDENT;

import java.io.IOException;
import java.lang.Object.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;


import javafx.stage.Stage;
import model.Student;
import model.StaticData;

/**
 *
 * @author Карина
 */
public class MainFormController implements Initializable 
{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    @FXML
    private Label labelName;
    @FXML
    private Label labelSurname;
    @FXML
    private Label labelBirthday;
    @FXML
    private Label labelDepartment;
    @FXML
    private Label labelSpeciality;
    @FXML
    private Label labelCourse;
    @FXML
    private Label labelGroup;
    @FXML
    private Label labelDelay;
    @FXML
    private Button bNew;
    @FXML
    private Button bEdit;
    @FXML
    private Button bDelete;
    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student, String> tableSurname;
    @FXML
    private TableColumn<Student, String> tableGroup;
    @FXML
    private TableColumn<Student, String> tableDate;
    
    @FXML
     void onClickButtonNew(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        
        FXMLDocumentController(stage, "/view/StudentListAdd.fxml");
    }
  
    @FXML
     void onClickButtonEdit(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        
        FXMLDocumentController(stage, "/view/StudentListEdit.fxml");
    }
    protected void FXMLDocumentController(Stage stage, String arg) throws IOException 
    {
        //Загрузили ресурс файла
        Parent root = FXMLLoader.load(getClass().getResource(arg));
        Scene scene = new Scene(root);
        Image icon = new Image(getClass().getResourceAsStream("/images/logo.png"));
        stage.setTitle("Edit/Add");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
     void onClickButtonDelete(ActionEvent event)
    {
         try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirm the deletion");
            alert.setContentText("Are you sure you want to delete?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                Student st = new Student();
                    st = StaticData.data.get(StaticData.selectedRow);
              
                    STUDENT p = new STUDENT();
                    p.setID(StaticData.selectedRow);
                    p.setName(st.getName());
                    p.setSurname(st.getSurname());
                    p.setDEPARTMENT(st.getDepartment());
                    p.setINSTITUTE(st.getSpeciality());
                    p.setGroupz(st.getGroup());
                    p.setDelayz(st.getDelayDate());
                    Factory.getInstance().getStudentDAO().deleteStudent(p);
                    StaticData.data.remove(StaticData.selectedRow);
                   
            } else {
                // ... user chose CANCEL or closed the dialog
            }    
            
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try {
            List<STUDENT> studs = Factory.getInstance().getStudentDAO().getAllStudents();
            for(int i = 0; i < studs.size(); ++i) {
                Student st  = new Student(studs.get(i).getName(), studs.get(i).getSurname(),studs.get(i).getBirthday(),studs.get(i).getGroupz(), studs.get(i).getDEPARTMENT(), studs.get(i).getINSTITUTE(),studs.get(i).getCourse(), studs.get(i).getDelayz());
                StaticData.data.add(st);
            }

            Properties props = new Properties();
            
            
            
            tableSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("Surname"));
            tableGroup.setCellValueFactory(new PropertyValueFactory<Student, String>("Group"));
            tableDate.setCellValueFactory(new PropertyValueFactory<Student, String>("DelayDate"));
            table.setItems(StaticData.data);
            
            
            
            
            
//======Event change selected row
            table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); // just in case you didnt already set the selection model to multiple selection.
            table.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends Integer> change) {
                    Student st = new Student();
                    int row = 0;
                    try {
                        String tmp = change.getList().toString();
                        char myChar = tmp.charAt(1);
                        tmp = String.valueOf(myChar);
                        row = Integer.parseInt(tmp);
                        StaticData.selectedRow = row;
                        System.out.println(row);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                    
                    st = StaticData.data.get(row);
                    labelName.setText(st.getName());
                    labelSurname.setText(st.getSurname());
                    labelBirthday.setText(st.getBirthday());
                    labelDepartment.setText(st.getDepartment());
                    labelSpeciality.setText(st.getSpeciality());
                    
                    labelGroup.setText(st.getGroup());
                    
                    
                    labelCourse.setText(st.getCourse());
                    labelDelay.setText(st.getDelayDate());
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
