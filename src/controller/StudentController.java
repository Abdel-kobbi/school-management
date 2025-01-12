package controller;

import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.ClasseDAO;
import DAO.StudentDAO;
import model.ClassSchool;
import model.Student;
import view.ClassSchoolView;
import view.StudentView;

public class StudentController {

    private static Scanner input;

    private StudentDAO studentDAO;
    private StudentView studentView;
    private ClasseDAO classeDAO;
    private ClassSchoolView classSchoolView;

    // Recuperation des TextFiled & buttons
    private JTextField txtNom;
    private JTextField txtAge;
    private JComboBox<ClassSchool> listClasses;
    private JButton addButton;
    private DefaultTableModel tableModel;

    public StudentController() {
        this.studentDAO = new StudentDAO();
        this.classeDAO = new ClasseDAO();
        this.studentView = new StudentView(this.classeDAO.findAll());
        this.classSchoolView = new ClassSchoolView();
        input = new Scanner(System.in);
        this.txtNom = this.studentView.getTxtNom();
        this.txtAge = this.studentView.getTxtAge();
        this.listClasses = this.studentView.getListClasses();
        this.tableModel = studentView.getTableModel();
        this.addButton = studentView.getBtnAdd();
    }

    private void loadStudent() {
        try {
            List<Student> students = studentDAO.findAll();
            tableModel.setRowCount(0); // vider le tableau
            for (Student student : students) {
                tableModel.addRow(
                        new Object[] { student.getId(), student.getNom(), student.getAge(), student.getClasse() });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(studentView, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void start() {
        this.studentView.setVisible(true);
        this.loadStudent();
        this.addButton.addActionListener(e -> addStudent());
        int entry;
        do {
            this.studentView.displayStudentMenu();
            entry = input.nextInt();
            switch (entry) {
                case 1 -> this.addStudent("V1");
                case 2 -> this.listOfStudent();
                case 3 -> this.editStudent();
                case 4 -> this.removeStudent();
                case 5 -> System.out.println("");
                default -> System.out.println("Choix invalide!");

            }
        } while (entry != 5);

    }

    private void addStudent() {
        try {
            String valueNom = txtNom.getText();
            String valueAge = txtAge.getText();
            ClassSchool classe = (ClassSchool) listClasses.getSelectedItem();
            if (valueNom.isEmpty()) {
                throw new Exception("Le nom est nécessaire.");
            }
            if (valueAge.isEmpty()) {
                throw new Exception("L'âge est nécessaire.");
            } else if (!isInteger(valueAge) || parseInt(valueAge) <= 0) {
                throw new Exception("L'âge doit être un nombre entier positive.");
            }

            boolean isSave = this.studentDAO.save(new Student(valueNom, parseInt(valueAge), classe));
            if (isSave) {
                JOptionPane.showMessageDialog(studentView, "L'étudiant a été ajouté avec succès.", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                this.txtNom.setText("");
                this.txtAge.setText("");
                this.listClasses.setSelectedIndex(0);
                loadStudent();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(studentView, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private int parseInt(String value) {
        return Integer.parseInt(value);
    }

    private void addStudent(String version) {
        String nom;
        int age, classeId;
        System.out.print("Entre votre nom: ");
        input.nextLine();
        nom = input.nextLine();
        System.out.print("Entre votre age: ");
        age = input.nextInt();
        this.classSchoolView.displayClassRoom(this.classeDAO.findAll());
        System.out.print("Choisi id de la classe: ");
        classeId = input.nextInt();
        ClassSchool classe = this.classeDAO.findById(classeId);
        if (classe != null) {
            this.studentDAO.save(new Student(nom, age, classe));
        } else {
            System.out.println("Aucun classe pour cet ID.");
        }
    }

    private void listOfStudent() {
        this.studentView.displayStudent(this.studentDAO.findAll());
    }

    private void editStudent() {
        int id, age, classeId;
        String nom;
        this.listOfStudent();
        if (this.studentDAO.findAll().size() == 0) {
            System.err.println("Pas des étudiants");
            return;
        }
        System.out.print("choisi id de l'etudiant a modifier: ");
        id = input.nextInt();
        Student student = this.studentDAO.findById(id);
        if (student != null) {
            System.out.print("Entre le nouveau nom: ");
            input.nextLine();
            nom = input.nextLine();
            System.out.print("Entre le nouveau age: ");
            age = input.nextInt();
            this.classSchoolView.displayClassRoom(this.classeDAO.findAll());
            System.out.print("Choisi id de la classe: ");
            classeId = input.nextInt();
            ClassSchool classe = this.classeDAO.findById(classeId);
            if (classe != null) {
                student.setNom(nom);
                student.setAge(age);
                student.setClasse(classe);
                this.studentDAO.update(student);
            } else {
                System.out.println("Aucun classe pour cet ID.");
            }
        } else {
            System.out.println("Aucun étudiant(e) avec cet ID.");
        }

    }

    private void removeStudent() {
        int id;
        this.listOfStudent();
        if (this.studentDAO.findAll().size() == 0) {
            System.err.println("Pas des étudiants.");
            return;
        }
        System.out.print("choisi id de l'etudiant supprimer: ");
        id = input.nextInt();
        this.studentDAO.delete(id);
    }

}
