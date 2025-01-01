package model;

import java.util.List;

import DAO.ClasseDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;

public class School {
    private static School school;
    private StudentDAO studentDAO = new StudentDAO();
    private TeacherDAO teacherDAO = new TeacherDAO();
    private ClasseDAO classeDAO = new ClasseDAO();

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
        return this.studentDAO.findAll();
    }

    public void addStudent(Student student) {
        this.studentDAO.save(student);
    }

    public void updateStudent(Student student, String name, int age) {
        student.setNom(name);
        student.setAge(age);
        this.studentDAO.update(student);
    }

    public void removeStudent(Student student) {
        this.studentDAO.delete(student.getId());
    }

    public Student searchStudent(int id) {
        return this.studentDAO.findById(id);
    }

    // management teacher
    public List<Teacher> getTeachers() {
        return this.teacherDAO.findAll();
    }

    public void addTeacher(Teacher teacher) {
        this.teacherDAO.save(teacher);
    }

    public void updateTeacher(Teacher teacher, String name, int age, String module) {
        teacher.setNom(name);
        teacher.setAge(age);
        teacher.setModule(module);
        this.teacherDAO.update(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        this.teacherDAO.delete(teacher.getId());
        ;
    }

    public Teacher searchTeacher(int id) {
        return this.teacherDAO.findById(id);
    }

    // management the classrooms
    public List<ClassSchool> getListClassRoom() {
        return this.classeDAO.findAll();
    }

    public void addClass(ClassSchool classSchool) {
        this.classeDAO.save(classSchool);
    }

    public void removeClass(int id) {
        this.classeDAO.delete(id);
    }

    public ClassSchool searchClassRomm(int id) {
        return this.classeDAO.findById(id);
    }

}
