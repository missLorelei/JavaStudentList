/*
 * Karin Wittal 
 * Group 243
 */

package view;

import flexjson.JSONSerializer;
import java.io.IOException;
import java.lang.Object.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.StaticData;
import model.Student;

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
    
    public int selectedId;
    
    public Student st;
    
    @FXML
    private MenuItem sendStudent;
    
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
                
                Class.forName("org.h2.Driver");
                Connection conn = DriverManager.getConnection("jdbc:h2:~/DB");
                String sql = "DELETE FROM students WHERE id = ?";
                
                PreparedStatement pstmt = conn.prepareStatement(sql); 
                pstmt.setInt(1, selectedId);
                pstmt.executeUpdate();
                
                conn.close();
                
                StaticData.data.remove(StaticData.selectedRow);
            } else {
                // ... user chose CANCEL or closed the dialog
            }    
            
        } catch (Exception e) {
            
        }
    }
    
     @FXML
    void onCickSendStudent(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();       
        
        JSONSerializer serializer = new JSONSerializer();
        String json = serializer.serialize(st);
        stage.setUserData(json);
        
        FXMLDocumentController(stage, "/view/SendStudentView.fxml");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
  
        tableSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("Surname"));
        tableGroup.setCellValueFactory(new PropertyValueFactory<Student, String>("Group"));
        tableDate.setCellValueFactory(new PropertyValueFactory<Student, String>("DelayDate"));
        table.setItems(StaticData.data);
      
        st = new Student();
        
        ArrayList<Student> list = new ArrayList<Student>();
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/DB");
            Statement stat = conn.createStatement();
            
            ResultSet rs;
            rs = stat.executeQuery("select * from students");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                
                
                Student a = new Student();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setSurname(rs.getString("surname"));
                a.setBirthday(rs.getString("birthday"));
                a.setDepartment(rs.getString("department"));
                a.setGroup(rs.getString("groupz"));
                a.setSpeciality(rs.getString("speciality"));
                a.setSpeciality(rs.getString("course"));
                a.setDelayDate(rs.getString("delayz"));
                
                list.add(a);   
            }
            
            StaticData.data.addAll(list);
            stat.close();
            conn.close();
 
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

//======Event change selected row        
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); // just in case you didnt already set the selection model to multiple selection.
        table.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Integer> change) {
               // st = new Student();
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
                selectedId = st.getId();
                
                labelCourse.setText(st.getCourse());
                labelDelay.setText(st.getDelayDate());
                
            }
        });
    }   
}
