package controller;

import java.util.Scanner;

import model.School;
import model.Student;
import view.StudentView;

public class StudentController {

    private static Scanner input = new Scanner(System.in);

    private School school;
    private StudentView studentView = new StudentView();

    public StudentController(School school) {
        this.school = school;
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
            }
        } while (entry != 4);

    }

}
