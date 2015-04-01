/*
 * Karin Wittal 
 * Group 243
 */

package student;

/**
 *
 * @author Карина
 */
public class Student            //class that include methods of student profile
{
    
    private String name;
    private String surname;
    private String birthday;
    private String group;
    private String department;
    private String course;
    private String speciality;
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
