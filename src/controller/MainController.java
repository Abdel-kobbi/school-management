package controller;

import java.util.Scanner;

import model.School;
import view.MainView;

public class MainController {
    private static Scanner input = new Scanner(System.in);
    private MainView mainView = new MainView();
    private School school = new School();
    private StudentController studentController = new StudentController(this.school);
    private TeacherController teacherController = new TeacherController(this.school);


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

            }
        } while (entry != 3);

        System.out.println("Fin");
    }
}
