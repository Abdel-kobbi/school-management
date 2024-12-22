package controller;

import java.util.Scanner;

import model.School;
import view.MainView;

public class MainController {
    private static Scanner input;
    private School school;
    private MainView mainView;
    private StudentController studentController;
    private TeacherController teacherController;

    public MainController(){
        input = new Scanner(System.in);
        this.school = School.getSchool();
        this.mainView = new MainView();
        this.studentController = new StudentController();
        this.teacherController = new TeacherController();
    }

    public void start() {
        int entry;
        do {
            this.mainView.displayStartMenu();
            entry = input.nextInt();
            switch (entry) {
                case 1:
                    this.teacherController.start();
                    break;
                case 2:
                    this.studentController.start();
                    break;
                case 3:
                    this.mainView.displayInfoSchool(school.getTeachers().size(), school.getStudents().size());
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Choix invalide!");
            }
        } while (entry != 4);

        System.out.println("Fin");
    }
}
