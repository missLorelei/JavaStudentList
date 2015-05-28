package model;

import javax.persistence.*;

@Entity
@Table(name = "STUDENTS")
public class Student            //class that include methods of student profile
{
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "group")
    private String group;
    @Column(name = "department")
    private String department;
    @Column(name = "course")
    private String course;
    @Column(name = "speciality")
    private String speciality;
    @Column(name = "dateOfDelay")
    private String dateOfDelay;
        
    public Student()
    {
                
    }
    
    public Student(String name, String surname, String birthday,  
                   String department,  String speciality,String course,String group, String dateOfDelay )
    {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.department = department;
        this.speciality = speciality;
        this.course = course;
        this.group = group;
        this.dateOfDelay = dateOfDelay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    
    public String getDelayDate() {
        return dateOfDelay;
    }

    public void setDelayDate(String dateOfDelay) {
        this.dateOfDelay = dateOfDelay;
    }
   
}
