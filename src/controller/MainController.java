package controller;

import java.awt.CardLayout;

import view.MainView;

public class MainController {
    private StudentController studentController;
    private TeacherController teacherController;
    private ClassSchoolController classSchoolController;

    private MainView mainView;
    private CardLayout cardLayout;

    public MainController() {
        this.mainView = new MainView();
        this.cardLayout = this.mainView.getCardLayout();
        this.mainView.getStudentManagement().addActionListener(e -> showContent("studentManagement"));
        this.mainView.getTeacherManagement().addActionListener(e -> showContent("teacherManagement"));
        this.mainView.getClassesManagement().addActionListener(e -> showContent("classesManagement"));
        this.mainView.getExitItem().addActionListener(e -> System.exit(0));
        this.studentController = new StudentController();
        this.teacherController = new TeacherController();
        this.classSchoolController = new ClassSchoolController();
    }

    private void showContent(String name) {
        this.cardLayout.show(mainView.getMainPanel(), name);
    }

    public void start() {
        this.mainView.getMainPanel().add(this.studentController.getStudentView(), "studentManagement");
        this.mainView.getMainPanel().add(this.teacherController.getTeacherView(), "teacherManagement");
        this.mainView.getMainPanel().add(this.classSchoolController.getClassSchoolView(), "classesManagement");
        this.mainView.setVisible(true);
    }
}
