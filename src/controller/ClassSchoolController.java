package controller;

import java.util.Scanner;

import DAO.ClasseDAO;
import DAO.TeacherDAO;
import model.ClassSchool;
import model.Teacher;
import view.ClassSchoolView;
import view.StudentView;
import view.TeacherView;

public class ClassSchoolController {
    private static Scanner input;
    private ClasseDAO classeDAO;
    private TeacherDAO teacherDAO;
    private ClassSchoolView classSchoolView;
    private TeacherView teacherView;
    private StudentView studentView;

    public ClassSchoolController() {
        this.classeDAO = new ClasseDAO();
        this.teacherDAO = new TeacherDAO();
        this.classSchoolView = new ClassSchoolView();
        this.teacherView = new TeacherView();
        this.studentView = new StudentView();
        input = new Scanner(System.in);
    }

    public void start() {
        int entry;
        do {
            this.classSchoolView.displayClassSchoolMenu();
            entry = input.nextInt();
            switch (entry) {
                case 1 -> this.addClass();
                case 2 -> this.editClass();
                case 3 -> this.removeClass();
                case 4 -> this.listClassRoom();
                case 5 -> this.listStudentOfClass();
                case 6 -> System.out.println("");
                default -> System.out.println("Choix invalide!");
            }
        } while (entry != 6);
    }

    private void addClass() {
        int id;
        String nom;
        System.out.print("Entre le nom de la classe: ");
        input.nextLine();
        nom = input.nextLine();
        this.teacherView.displayTeachers(this.teacherDAO.findAll());
        System.out.print("choisi l'id d'Enseignant responsable: ");
        id = input.nextInt();
        Teacher teacher = this.teacherDAO.findById(id);
        if (teacher != null) {
            this.classeDAO.save(new ClassSchool(nom, teacher));
        } else {
            System.out.println("Enseignant n'existe pas!");
        }
    }

    private void editClass() {
        int id;
        String nom;
        this.classSchoolView.displayClassRoom(this.classeDAO.findAll());
        System.out.print("choisi l'id de la classe a modifier: ");
        id = input.nextInt();
        ClassSchool classe = this.classeDAO.findById(id);
        if (classe != null) {
            System.out.print("Entre le nouveau nom de la classe: ");
            input.nextLine();
            nom = input.nextLine();
            this.teacherView.displayTeachers(this.teacherDAO.findAll());
            System.out.print("choisi l'id d'Enseignant responsable: ");
            id = input.nextInt();
            Teacher teacher = this.teacherDAO.findById(id);
            if (teacher != null) {
                classe.setClassName(nom);
                classe.setTeacher(teacher);
                this.classeDAO.update(classe);
            } else {
                System.out.println("Enseignant n'existe pas!");
            }
        } else {
            System.out.println("La classe n'existe pas!");

        }
    }

    private void removeClass() {
        int id;
        this.classSchoolView.displayClassRoom(this.classeDAO.findAll());
        System.out.print("choisi l'id de la classe a supprimer: ");
        id = input.nextInt();
        this.classeDAO.delete(id);
    }

    private void listClassRoom() {
        this.classSchoolView.displayClassRoom(this.classeDAO.findAll());
    }

    private void listStudentOfClass() {
        int id;
        this.classSchoolView.displayClassRoom(this.classeDAO.findAll());
        System.out.print("Choisi id de la classe pour lister ces etudiants: ");
        id = input.nextInt();
        ClassSchool classRomm = this.classeDAO.findById(id);
        if (classRomm != null) {
            this.studentView.displayStudent(this.classeDAO.getStudents(classRomm));
        } else {
            System.out.println("La classe n'existe pas!");
        }
    }
}
