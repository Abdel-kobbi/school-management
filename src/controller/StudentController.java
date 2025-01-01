package controller;

import java.util.Scanner;

import DAO.ClasseDAO;
import DAO.StudentDAO;
import model.ClassSchool;
import model.Student;
import view.ClassSchoolView;
import view.StudentView;

public class StudentController {

    private static Scanner input;

    private StudentDAO studentDAO;
    private StudentView studentView;
    private ClasseDAO classeDAO;
    private ClassSchoolView classSchoolView;

    public StudentController() {
        this.studentDAO = new StudentDAO();
        this.studentView = new StudentView();
        this.classeDAO = new ClasseDAO();
        this.classSchoolView = new ClassSchoolView();
        input = new Scanner(System.in);
    }

    public void start() {
        int entry;
        do {
            this.studentView.displayStudentMenu();
            entry = input.nextInt();
            switch (entry) {
                case 1 -> this.addStudent();
                case 2 -> this.listOfStudent();
                case 3 -> this.editStudent();
                case 4 -> this.removeStudent();
                case 5 -> System.out.println("");
                default -> System.out.println("Choix invalide!");

            }
        } while (entry != 5);

    }

    private void addStudent() {
        String nom;
        int age, classeId;
        System.out.print("Entre votre nom: ");
        input.nextLine();
        nom = input.nextLine();
        System.out.print("Entre votre age: ");
        age = input.nextInt();
        this.classSchoolView.displayClassRoom(this.classeDAO.findAll());
        System.out.print("Choisi id de la classe: ");
        classeId = input.nextInt();
        ClassSchool classe = this.classeDAO.findById(classeId);
        if (classe != null) {
            this.studentDAO.save(new Student(nom, age, classe));
        } else {
            System.out.println("Aucun classe pour cet ID.");
        }
    }

    private void listOfStudent() {
        this.studentView.displayStudent(this.studentDAO.findAll());
    }

    private void editStudent() {
        int id, age, classeId;
        String nom;
        this.listOfStudent();
        if (this.studentDAO.findAll().size() == 0) {
            System.err.println("Pas des étudiants");
            return;
        }
        System.out.print("choisi id de l'etudiant a modifier: ");
        id = input.nextInt();
        Student student = this.studentDAO.findById(id);
        if (student != null) {
            System.out.print("Entre le nouveau nom: ");
            input.nextLine();
            nom = input.nextLine();
            System.out.print("Entre le nouveau age: ");
            age = input.nextInt();
            this.classSchoolView.displayClassRoom(this.classeDAO.findAll());
            System.out.print("Choisi id de la classe: ");
            classeId = input.nextInt();
            ClassSchool classe = this.classeDAO.findById(classeId);
            if (classe != null) {
                student.setNom(nom);
                student.setAge(age);
                student.setClasse(classe);
                this.studentDAO.update(student);
            } else {
                System.out.println("Aucun classe pour cet ID.");
            }
        } else {
            System.out.println("Aucun étudiant(e) avec cet ID.");
        }

    }

    private void removeStudent() {
        int id;
        this.listOfStudent();
        if (this.studentDAO.findAll().size() == 0) {
            System.err.println("Pas des étudiants.");
            return;
        }
        System.out.print("choisi id de l'etudiant supprimer: ");
        id = input.nextInt();
        this.studentDAO.delete(id);
    }

}
