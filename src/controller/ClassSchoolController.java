package controller;

import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.ClasseDAO;
import DAO.TeacherDAO;
import model.ClassSchool;
import model.Teacher;
import view.ClassSchoolView;
import view.StudentView;
import view.TeacherView;

public class ClassSchoolController {
    private static Scanner input;
    private ClasseDAO classeDAO;
    private TeacherDAO teacherDAO;
    private ClassSchoolView classSchoolView;
    private TeacherView teacherView;
    private StudentView studentView;

    // Recuperation des TextFiled & buttons
    private JTextField txtNom;
    private JComboBox<Teacher> listTeachers;
    private JButton addButton;
    private DefaultTableModel tableModel;

    public ClassSchoolController() {
        this.classeDAO = new ClasseDAO();
        this.teacherDAO = new TeacherDAO();
        this.classSchoolView = new ClassSchoolView(this.teacherDAO.findAll());
        this.teacherView = new TeacherView();
        this.studentView = new StudentView(this.classeDAO.findAll());
        input = new Scanner(System.in);
        this.txtNom = this.classSchoolView.getTxtNom();
        this.listTeachers = this.classSchoolView.getListTeaches();
        this.addButton = this.classSchoolView.getBtnAdd();
        this.tableModel = this.classSchoolView.getTableModel();
    }

    public void start() {
        this.classSchoolView.setVisible(true);
        this.loadClasses();
        this.addButton.addActionListener((e) -> addClass());
        int entry;
        do {
            this.classSchoolView.displayClassSchoolMenu();
            entry = input.nextInt();
            switch (entry) {
                case 1 -> this.addClass("old");
                case 2 -> this.editClass();
                case 3 -> this.removeClass();
                case 4 -> this.listClassRoom();
                case 5 -> this.listStudentOfClass();
                case 6 -> System.out.println("");
                default -> System.out.println("Choix invalide!");
            }
        } while (entry != 6);
    }

    private void loadClasses() {
        try {
            List<ClassSchool> classes = this.classeDAO.findAll();
            this.tableModel.setRowCount(0); // vider le tableau
            for (ClassSchool classe : classes) {
                this.tableModel
                        .addRow(new Object[] { classe.getId(), classe.getClassName(), classe.getTeacher().getNom() });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(studentView, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addClass() {
        try {
            String nomValue = this.txtNom.getText();
            Teacher teacher = (Teacher) this.listTeachers.getSelectedItem();
            if (nomValue.isEmpty()) {
                throw new Exception("Le nom est nécessaire.");
            }

            boolean isSave = this.classeDAO.save(new ClassSchool(nomValue, teacher));

            if (isSave) {
                JOptionPane.showMessageDialog(this.classSchoolView, "La classe est ajouter avec succée.", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                this.txtNom.setText("");
                this.listTeachers.setSelectedIndex(0);
                loadClasses();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.classSchoolView, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addClass(String v) {
        int id;
        String nom;
        System.out.print("Entre le nom de la classe: ");
        input.nextLine();
        nom = input.nextLine();
        this.teacherView.displayTeachers(this.teacherDAO.findAll());
        System.out.print("choisi l'id d'Enseignant responsable: ");
        id = input.nextInt();
        Teacher teacher = this.teacherDAO.findById(id);
        if (teacher != null) {
            this.classeDAO.save(new ClassSchool(nom, teacher));
        } else {
            System.out.println("Enseignant n'existe pas!");
        }
    }

    private void editClass() {
        int id;
        String nom;
        this.classSchoolView.displayClassRoom(this.classeDAO.findAll());
        System.out.print("choisi l'id de la classe a modifier: ");
        id = input.nextInt();
        ClassSchool classe = this.classeDAO.findById(id);
        if (classe != null) {
            System.out.print("Entre le nouveau nom de la classe: ");
            input.nextLine();
            nom = input.nextLine();
            this.teacherView.displayTeachers(this.teacherDAO.findAll());
            System.out.print("choisi l'id d'Enseignant responsable: ");
            id = input.nextInt();
            Teacher teacher = this.teacherDAO.findById(id);
            if (teacher != null) {
                classe.setClassName(nom);
                classe.setTeacher(teacher);
                this.classeDAO.update(classe);
            } else {
                System.out.println("Enseignant n'existe pas!");
            }
        } else {
            System.out.println("La classe n'existe pas!");

        }
    }

    private void removeClass() {
        int id;
        this.classSchoolView.displayClassRoom(this.classeDAO.findAll());
        System.out.print("choisi l'id de la classe a supprimer: ");
        id = input.nextInt();
        this.classeDAO.delete(id);
    }

    private void listClassRoom() {
        this.classSchoolView.displayClassRoom(this.classeDAO.findAll());
    }

    private void listStudentOfClass() {
        int id;
        this.classSchoolView.displayClassRoom(this.classeDAO.findAll());
        System.out.print("Choisi id de la classe pour lister ces etudiants: ");
        id = input.nextInt();
        ClassSchool classRomm = this.classeDAO.findById(id);
        if (classRomm != null) {
            this.studentView.displayStudent(this.classeDAO.getStudents(classRomm));
        } else {
            System.out.println("La classe n'existe pas!");
        }
    }
}
