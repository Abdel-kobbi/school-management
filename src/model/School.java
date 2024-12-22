package model;

import java.util.ArrayList;

public class School {
    private static School school;
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Teacher> teachers = new ArrayList<>();

    // Utilisation de Design pattern : Singleton
    private School() {
    };

    public static School getSchool() {
        if (school == null) {
            school = new School();
        }
        return school;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void updateStudent(Student student, String name, int age) {
        for (Student s : this.getStudents()) {
            if (s.getId() == student.getId()) {
                s.setNom(name);
                s.setAge(age);
                return;
            }
        }
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public Student searchStudent(int id) {
        Student student = null;
        for (Student s : school.getStudents()) {
            if (s.getId() == id) {
                student = s;
                break;
            }
        }
        return student;
    }

    public ArrayList<Teacher> getTeachers() {
        return this.teachers;
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public void updateTeacher(Teacher teacher, String name, int age, String module) {
        for (Teacher t : this.getTeachers()) {
            if (t.getId() == teacher.getId()) {
                t.setNom(name);
                t.setAge(age);
                t.setModule(module);
                return;
            }
        }
    }

    public void removeTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
    }

    public Teacher searchTeacher(int id) {
        Teacher teacher = null;
        for (Teacher t : school.getTeachers()) {
            if (t.getId() == id) {
                teacher =  t;
                break;
            }
        }
        return teacher;
    }

}
