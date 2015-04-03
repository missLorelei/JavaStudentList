/*
 * Karin Wittal 
 * Group 243
 */

package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Student;
/**
 *
 * @author Карина
 */
public class StaticData 
{
    public static int selectedRow = 0;
     //list
    public static ObservableList<Student> data = FXCollections.observableArrayList();
}
