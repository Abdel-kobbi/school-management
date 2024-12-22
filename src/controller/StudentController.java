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
        int entry, id, age;
        String nom;
        do {
            studentView.displayStudentMenu();
            entry = input.nextInt();
            switch (entry) {
                case 1:
                    System.out.println("Entre votre nom");
                    nom = input.nextLine();
                    input.nextLine();
                    System.out.println("Entre votre age");
                    age = input.nextInt();
                    this.school.addStudent(new Student(nom, age));
                    break;
                case 2:
                    studentView.displayStudent(school.getStudents());
                    break;
                case 3:
                    studentView.displayStudent(school.getStudents());
                    System.out.println("choisi id de l'etudiant supprimer: ");
                    id = input.nextInt();
                    for (Student etu : school.getStudents()) {
                        if (etu.getId() == id) {
                            school.removeStudent(etu);
                            break;
                        }
                    }
                    break;
                case 4:
                    break;
                default : 
                    System.out.println("Choix invalide!");

            }
        } while (entry != 4);

    }

}
