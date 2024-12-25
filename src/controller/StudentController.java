package controller;

import java.util.Scanner;

import model.School;
import model.Student;
import view.StudentView;

public class StudentController {

    private static Scanner input;

    private School school;
    private StudentView studentView;

    public StudentController() {
        this.school = School.getSchool();
        this.studentView = new StudentView();
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
        int age;
        System.out.print("Entre votre nom: ");
        input.nextLine();
        nom = input.nextLine();
        System.out.print("Entre votre age: ");
        age = input.nextInt();
        this.school.addStudent(new Student(nom, age));
    }

    private void listOfStudent() {
        this.studentView.displayStudent(school.getStudents());
    }

    private void editStudent() {
        int id, age;
        String nom;
        this.studentView.displayStudent(school.getStudents());
        if (school.getStudents().size() == 0) {
            System.err.println("Pas des étudiants");
            return;
        }
        System.out.print("choisi id de l'etudiant a modifier: ");
        id = input.nextInt();
        System.out.print("Entre le nouveau nom: ");
        input.nextLine();
        nom = input.nextLine();
        System.out.print("Entre le nouveau age: ");
        age = input.nextInt();
        this.school.updateStudent(this.school.searchStudent(id), nom, age);
    }

    private void removeStudent() {
        int id;
        this.studentView.displayStudent(school.getStudents());
        if (school.getStudents().size() == 0) {
            System.err.println("Pas des étudiants.");
            return;
        }
        System.out.print("choisi id de l'etudiant supprimer: ");
        id = input.nextInt();
        this.school.removeStudent(this.school.searchStudent(id));
    }

}
