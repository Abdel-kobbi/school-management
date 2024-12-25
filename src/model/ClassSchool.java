package model;

import java.util.ArrayList;
import java.util.List;

public class ClassSchool {
    private static int idU = 0;
    private int id;
    private String className;
    private Teacher teacher;
    private List<Student> students;

    public ClassSchool(String className, Teacher teacher) {
        this.id = ++idU;
        this.className = className;
        this.teacher = teacher;
        this.students = new ArrayList<>();
    }

    // getters
    public int getId() {
        return this.id;
    }

    public String getClassName() {
        return this.className;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getNumberOfStudent(){
        return this.students.size();
    }

    //setters
    public void setClassName(String className) {
        this.className = className;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void addStudentToClass(Student student){
        this.students.add(student);
    }

    public void removeStudentFromClass(Student student){
        this.students.remove(student);
    }

    public String toString() {
        return "ID: " + this.getId() + ", Nom de la classe: " + this.className + 
        ", L'Enseignant de la classe: " + this.getTeacher() + 
        ", Le nombre des etudiants de la classe est:  " + this.getNumberOfStudent();
    }

}
