package controller;

import java.util.Scanner;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.TeacherDAO;
import model.Teacher;
import view.TeacherView;

public class TeacherController {
    private static Scanner input;

    private TeacherView teacherView;
    private TeacherDAO teacherDAO;

    // Recuperation des TextFiled & buttons
    private JTextField txtNom;
    private JTextField txtAge;
    private JTextField txtModule;
    private JButton addButton;
    private DefaultTableModel tableModel;

    public TeacherController() {
        this.teacherDAO = new TeacherDAO();
        this.teacherView = new TeacherView();
        input = new Scanner(System.in);
        this.txtNom = this.teacherView.getTxtNom();
        this.txtAge = this.teacherView.getTxtAge();
        this.txtModule = this.teacherView.getTxtModule();
        this.addButton = this.teacherView.getBtnAdd();
        this.tableModel = this.teacherView.getTableModel();
    }

    public void start() {
        int entry;
        this.teacherView.setVisible(true);
        this.loadTeachers();
        this.addButton.addActionListener(e -> addTeacher());
        do {
            this.teacherView.displayTeacherMenu();
            entry = input.nextInt();
            switch (entry) {
                case 1 -> this.addTeacher("str");
                case 2 -> this.listOfTeachers();
                case 3 -> this.editTeacher();
                case 4 -> this.removeTeacher();
                case 5 -> System.out.println("");
                default -> System.out.println("Choix invalide!");
            }

        } while (entry != 5);
    }

    private void loadTeachers() {
        try {
            List<Teacher> teachers = this.teacherDAO.findAll();
            this.tableModel.setRowCount(0); // vider le tableau
            for (Teacher teacher : teachers) {
                this.tableModel.addRow(
                        new Object[] { teacher.getId(), teacher.getNom(), teacher.getAge(), teacher.getModule() });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.teacherView, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addTeacher() {
        try {
            String nameValue = txtNom.getText();
            String ageValue = txtAge.getText();
            String moduleValue = txtModule.getText();
            if (nameValue.isEmpty()) {
                throw new Exception("Le nom est nécessaire.");
            }
            if (ageValue.isEmpty()) {
                throw new Exception("L'âge est nécessaire.");
            } else if (!isInteger(ageValue) || parseInt(ageValue) <= 0) {
                throw new Exception("L'âge doit être un nombre entier positive.");
            }
            if (moduleValue.isEmpty()) {
                throw new Exception("La Matière est nécessaire.");
            }
            boolean isSave = this.teacherDAO.save(new Teacher(nameValue, parseInt(ageValue), moduleValue));
            if (isSave) {
                JOptionPane.showMessageDialog(this.teacherView, "L'Enseignants est ajouter avec succée.", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                this.txtNom.setText("");
                this.txtAge.setText("");
                this.txtModule.setText("");
                loadTeachers();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(teacherView, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isInteger(String ageValue) {
        try {
            Integer.parseInt(ageValue);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private int parseInt(String ageValue) {
        return Integer.parseInt(ageValue);
    }

    private void addTeacher(String str) {
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
        } else {
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
