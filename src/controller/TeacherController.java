package controller;

import java.util.Scanner;

import model.School;
import model.Teacher;
import view.TeacherView;

public class TeacherController {
    private static Scanner input;

    private TeacherView teacherView = new TeacherView();
    private School school;

    public TeacherController(){
        this.school = School.getSchool();
        input = new Scanner(System.in);
    }
    public void start(){
        int entry, age, id;
        String nom, module;
        do{
            this.teacherView.displayTeacherMenu();
            entry = input.nextInt();
            switch (entry) {
                case 1:
                    System.out.print("Entre votre nom: ");
                    input.nextLine();
                    nom = input.nextLine();
                    System.out.print("Entre votre age: ");
                    age = input.nextInt();
                    input.nextLine();
                    System.out.print("Entre votre module: ");
                    module = input.nextLine();
                    this.school.addTeacher(new Teacher(nom, age, module));
                    break;
                case 2:
                    this.teacherView.displayTeachers(school.getTeachers());
                    break;
                case 3:
                    this.teacherView.displayTeachers(school.getTeachers());
                    if(school.getTeachers().size() == 0){
                        System.err.println("Pas des Enseignants");
                        break;
                    }
                    System.out.print("Entre l'id de l'Enseignant a modifier: ");
                    id = input.nextInt();
                    System.out.print("Entre le nouveau nom: ");
                    input.nextLine();
                    nom = input.nextLine();
                    System.out.print("Entre le nouveau age: ");
                    age = input.nextInt();
                    input.nextLine();
                    System.out.print("Entre le nouveau module: ");
                    module = input.nextLine();
                    this.school.updateTeacher(school.searchTeacher(id), nom, age, module);;
                    break;
                case 4:
                    this.teacherView.displayTeachers(school.getTeachers());
                    if(school.getTeachers().size() == 0){
                        System.err.println("Pas des Enseignants");
                        break;
                    }
                    System.out.print("Entre l'id de l'Enseignant a supprimer: ");
                    id = input.nextInt();
                    this.school.removeTeacher(school.searchTeacher(id));
                case 5:
                    break;
                default : 
                    System.out.println("Choix invalide!");
            }

        }while(entry != 5);
    }


}
