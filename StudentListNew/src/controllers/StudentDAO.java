package controllers;

import java.sql.SQLException;
import java.util.List;

import controllers.STUDENT;

public interface StudentDAO {
    public void addStudent(STUDENT student) throws SQLException;   //добавить студента
    public void updateStudent(STUDENT student) throws SQLException;//обновить студента
    public STUDENT getStudentById(Long id) throws SQLException;    //получить стедента по id
    public List getAllStudents() throws SQLException;              //получить всех студентов
    public void deleteStudent(STUDENT student) throws SQLException;//удалить студента
}