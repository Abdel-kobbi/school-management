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
            this.studentView.displayStudentMenu();
            entry = input.nextInt();
            switch (entry) {
                case 1:
                    System.out.println("Entre votre nom");
                    input.nextLine();
                    nom = input.nextLine();
                    System.out.println("Entre votre age");
                    age = input.nextInt();
                    this.school.addStudent(new Student(nom, age));
                    break;
                case 2:
                    this.studentView.displayStudent(school.getStudents());
                    break;
                case 3:
                    this.studentView.displayStudent(school.getStudents());
                    if(school.getStudents().size() == 0){
                        System.err.println("Pas des étudiants");
                        break;
                    }
                    System.out.println("choisi id de l'etudiant a modifier: ");
                    id = input.nextInt();
                    System.out.println("Entre le nouveau nom");
                    input.nextLine();
                    nom = input.nextLine();
                    System.out.println("Entre le nouveau age");
                    age = input.nextInt();
                    this.school.updateStudent(this.school.searchStudent(id), nom, age);
                    break;
                case 4:
                    this.studentView.displayStudent(school.getStudents());
                    if(school.getStudents().size() == 0){
                        System.err.println("Pas des étudiants");
                        break;
                    }
                    System.out.println("choisi id de l'etudiant supprimer: ");
                    id = input.nextInt();
                    this.school.removeStudent(this.school.searchStudent(id));
                case 5:
                    break;
                default : 
                    System.out.println("Choix invalide!");

            }
        } while (entry != 5);

    }

}
