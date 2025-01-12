package controller;

public class MainController {
    private StudentController studentController;
    private TeacherController teacherController;
    private ClassSchoolController classSchoolController;

    public MainController() {
        this.studentController = new StudentController();
        this.teacherController = new TeacherController();
        this.classSchoolController = new ClassSchoolController();
    }

    public void start() {
        this.studentController.start();
        this.teacherController.start();
        classSchoolController.start();
    }
}
