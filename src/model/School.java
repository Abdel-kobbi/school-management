package model;

import java.util.ArrayList;
import java.util.List;

public class School {
    private static School school;
    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private List<ClassSchool> listClassSchool = new ArrayList<>();

    // Utilisation de Design pattern : Singleton
    private School() {
    };

    public static School getSchool() {
        if (school == null) {
            school = new School();
        }
        return school;
    }

    // management students
    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void updateStudent(Student student, String name, int age) {
        student.setNom(name);
        student.setAge(age);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public Student searchStudent(int id) {
        return this.students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    // management teacher
    public List<Teacher> getTeachers() {
        return this.teachers;
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public void updateTeacher(Teacher teacher, String name, int age, String module) {
        teacher.setNom(name);
        teacher.setAge(age);
        teacher.setModule(module);
    }

    public void removeTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
    }

    public Teacher searchTeacher(int id) {
        return this.teachers.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    // management the classrooms
    public List<ClassSchool> getListClassRoom() {
        return this.listClassSchool;
    }

    public void addClass(ClassSchool classSchool) {
        this.listClassSchool.add(classSchool);
    }

    public void removeClass(ClassSchool classSchool) {
        this.listClassSchool.remove(classSchool);
    }

    public ClassSchool searchClassRomm(int id){
        return this.getListClassRoom().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

}
