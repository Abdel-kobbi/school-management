package model;

import java.util.ArrayList;

public class School {

    private ArrayList<Student> school = new ArrayList<>();

    public ArrayList<Student> getSchool() {
        return school;
    }

    public void addStudent(Student student){
        this.school.add(student);
    }

    public void removeStudent(Student student){
        this.school.remove(student);
    }
}
