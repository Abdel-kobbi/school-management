package controller;

import java.util.Scanner;

import model.School;
import model.Student;
import view.View;

public class Controller {

    private static Scanner input = new Scanner(System.in);

    private School school;
    private View view;

    public Controller(School school, View view) {
        this.school = school;
        this.view = view;
    }

    public void start() {
        int entry, id, age;
        String nom;
        do {
            view.displayStudentMenu();
            entry = input.nextInt();
            switch (entry) {
                case 1:
                    System.out.println("Entre votre id");
                    id = input.nextInt();
                    input.nextLine();
                    System.out.println("Entre votre nom");
                    nom = input.nextLine();
                    System.out.println("Entre votre age");
                    age = input.nextInt();
                    this.school.addStudent(new Student(id, nom, age));
                    break;
                case 2:
                    view.displayStudent(school.getSchool());
                    break;
                case 3:
                    view.displayStudent(school.getSchool());
                    System.out.println("choisi id de l'etudiant supprimer: ");
                    id = input.nextInt();
                    for (Student etu : school.getSchool()) {
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
