package controller;

import java.util.Scanner;

import view.MainView;

public class MainController {
    private static Scanner input;
    private MainView mainView;
    private StudentController studentController;
    private TeacherController teacherController;

    public MainController(){
        input = new Scanner(System.in);
        mainView = new MainView();
        studentController = new StudentController();
        teacherController = new TeacherController();
    }

    public void start() {
        int entry;
        do {
            mainView.displayStartMenu();
            entry = input.nextInt();
            switch (entry) {
                case 1:
                    teacherController.start();
                    break;
                case 2:
                    studentController.start();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Choix invalide!");

            }
        } while (entry != 3);

        System.out.println("Fin");
    }
}
