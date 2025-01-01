package controller;

import java.util.Scanner;

import DAO.TeacherDAO;
import model.Teacher;
import view.TeacherView;

public class TeacherController {
    private static Scanner input;

    private TeacherView teacherView;
    private TeacherDAO teacherDAO;

    public TeacherController() {
        this.teacherDAO = new TeacherDAO();
        teacherView = new TeacherView();
        input = new Scanner(System.in);
    }

    public void start() {
        int entry;
        do {
            this.teacherView.displayTeacherMenu();
            entry = input.nextInt();
            switch (entry) {
                case 1 -> this.addTeacher();
                case 2 -> this.listOfTeachers();
                case 3 -> this.editTeacher();
                case 4 -> this.removeTeacher();
                case 5 -> System.out.println("");
                default -> System.out.println("Choix invalide!");
            }

        } while (entry != 5);
    }

    private void addTeacher() {
        String nom, module;
        int age;
        System.out.print("Entre votre nom: ");
        input.nextLine();
        nom = input.nextLine();
        System.out.print("Entre votre age: ");
        age = input.nextInt();
        input.nextLine();
        System.out.print("Entre votre module: ");
        module = input.nextLine();
        this.teacherDAO.save(new Teacher(nom, age, module));
    }

    private void listOfTeachers() {
        this.teacherView.displayTeachers(this.teacherDAO.findAll());
    }

    private void editTeacher() {
        int id, age;
        String nom, module;
        this.listOfTeachers();
        if (this.teacherDAO.findAll().size() == 0) {
            System.err.println("Pas des Enseignants");
            return;
        }
        System.out.print("Entre l'id de l'Enseignant a modifier: ");
        id = input.nextInt();
        Teacher teacher = this.teacherDAO.findById(id);
        if (teacher != null) {
            System.out.print("Entre le nouveau nom: ");
            input.nextLine();
            nom = input.nextLine();
            System.out.print("Entre le nouveau age: ");
            age = input.nextInt();
            input.nextLine();
            System.out.print("Entre le nouveau module: ");
            module = input.nextLine();
            teacher.setNom(nom);
            teacher.setAge(age);
            teacher.setModule(module);
            this.teacherDAO.update(teacher);
        }else{
            System.err.println("Pas d'Enseignant pour cet ID.");
        }
    }

    private void removeTeacher() {
        int id;
        this.listOfTeachers();
        if (this.teacherDAO.findAll().size() == 0) {
            return;
        }
        System.out.print("Entre l'id de l'Enseignant a supprimer: ");
        id = input.nextInt();
        this.teacherDAO.delete(id);
    }
}
