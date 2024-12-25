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
    private ClassSchoolController classSchoolController;

    public MainController() {
        input = new Scanner(System.in);
        this.school = School.getSchool();
        this.mainView = new MainView();
        this.studentController = new StudentController();
        this.teacherController = new TeacherController();
        this.classSchoolController = new ClassSchoolController();
    }

    public void start() {
        int entry;
        do {
            this.mainView.displayStartMenu();
            entry = input.nextInt();
            switch (entry) {
                case 1 -> this.manageTeachers();
                case 2 -> this.manageStudents();
                case 3 -> this.manageClassRoom();
                case 4 -> this.detailsSchool();
                case 5 -> System.out.println();
                default -> System.out.println("Choix invalide!");
            }
        } while (entry != 5);

        System.out.println("Fin");
    }

    private void manageTeachers() {
        this.teacherController.start();
    }

    private void manageStudents() {
        this.studentController.start();
    }

    private void manageClassRoom() {
        this.classSchoolController.start();
    }

    private void detailsSchool() {
        this.mainView.displayInfoSchool(
                school.getTeachers().size(),
                school.getStudents().size(),
                school.getListClassRoom().size());
    }
}
