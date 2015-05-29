package controllers;


import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "STUDENTS")
public class STUDENT implements Serializable{
    @Id
    private long ID;
    @Column
    private String Name;
    @Column
    private String Surname;
    @Column
    private String Groupz;
    @Column
    private String Birthday;
    @Column
    private String DEPARTMENT;
    @Column
    private String Speciality;
    @Column
    private String Course;
    @Column
    private String Delayz;

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public void setGroupz(String Groupz) {
        this.Groupz = Groupz;
    }

    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT = DEPARTMENT;
    }

    public void setINSTITUTE(String INSTITUTE) {
        this.Speciality = INSTITUTE;
    }

    public void setDelayz(String Delayz) {
        this.Delayz = Delayz;
    }
    public void setBirthday(String Birthday) {
        this.Birthday = Birthday;
    }
    public void setCourse(String Course) {
        this.Course = Course;
    }
    
    public long getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getGroupz() {
        return Groupz;
    }

    public String getDEPARTMENT() {
        return DEPARTMENT;
    }

    public String getINSTITUTE() {
        return Speciality;
    }

    public String getDelayz() {
        return Delayz;
    }
    
    public String getBirthday() {
        return Birthday;
    }

    public String getCourse() {
        return Course;
    }
    
  
    public STUDENT(){
        
    }

    public STUDENT(long ID, String FIRST_NAME, String LAST_NAME,String BIRTHDAY, String GROUP_NAME, String DEPARTMENT, String INSTITUTE,String COURSE, String LATE_DATE) {
        this.ID = ID;
        this.Name = FIRST_NAME;
        this.Surname = LAST_NAME;
        this.Birthday = BIRTHDAY;
        this.Groupz = GROUP_NAME;
        this.DEPARTMENT = DEPARTMENT;
        this.Speciality = INSTITUTE;
        this.Course = COURSE;
        this.Delayz = LATE_DATE;
    }
}
