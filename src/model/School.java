package model;

import java.util.ArrayList;

public class School {
    private static School school;
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Teacher> teachers = new ArrayList<>();

    // Utilisation de Design pattern : Singleton
    private School(){};

    public static School getSchool(){
        if(school == null ){
            school = new School();
            System.out.println("school instance.");
        }
        return school;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    public void removeStudent(Student student){
        this.students.remove(student);
    }

    public ArrayList<Teacher> getTeachers(){
        return this.teachers;
    }

    public void addTeacher(Teacher teacher){
        this.teachers.add(teacher);
    }

    public void removeTeacher(Teacher teacher){
        this.teachers.remove(teacher);
    }

}
