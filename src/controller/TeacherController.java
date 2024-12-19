package controller;

import java.util.Scanner;

import model.School;
import model.Teacher;
import view.TeacherView;

public class TeacherController {
    private static Scanner input = new Scanner(System.in);

    private TeacherView teacherView = new TeacherView();
    private School school;

    public TeacherController(School school){
        this.school = school;
    }
    public void start(){
        int entry, age, id;
        String nom, module;
        do{
            teacherView.displayTeacherMenu();
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
                    school.addTeacher(new Teacher(nom, age, module));
                    break;
                case 2:
                    teacherView.displayTeachers(school.getTeachers());
                    break;
                case 3:
                    teacherView.displayTeachers(school.getTeachers());
                    System.out.print("Entre l'id de l'Enseignant a supprimer: ");
                    id = input.nextInt();
                    for(Teacher t: school.getTeachers()){
                        if(t.getId() == id){
                            school.removeTeacher(t);
                            break;
                        }
                    }
                    break;
            }

        }while(entry != 4);
    }


}
