/*
 * Karin Wittal 
 * Group 243
 */

package view;

import java.io.IOException;
import java.lang.Object.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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


import javafx.stage.Stage;
import model.Student;
import model.StaticData;

/**
 *
 * @author Карина
 */
public class MainFormController implements Initializable 
{
    
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
        
        tableSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("Surname"));
        tableGroup.setCellValueFactory(new PropertyValueFactory<Student, String>("Group"));
        tableDate.setCellValueFactory(new PropertyValueFactory<Student, String>("DelayDate"));
        table.setItems(StaticData.data);
        Student st1 = new Student("Karin", "Wittal", "1996-08-03", "Computer science", "PZKS", "Second","243", "2015-02-01");
        Student st2 = new Student("Konstantin", "Vikyrchak", "1995-09-09", "Computer science", "PZKS", "Second","243", "2015-03-02");
        Student st3 = new Student("Artem", "Dzhuran", "1996-04-07", "Computer science", "PZKS", "Second","243", "2015-03-29");
        Student st4 = new Student("Vanya", "Balan", "1996-10-09", "Computer science", "PZKS", "Second","243", "2015-03-18");
        StaticData.data.add(st1);
        StaticData.data.add(st2);
        StaticData.data.add(st3);
        StaticData.data.add(st4);


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
    }   
}
