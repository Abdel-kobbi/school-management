package controller;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.ClasseDAO;
import DAO.StudentDAO;
import model.ClassSchool;
import model.Student;
import view.StudentView;

public class StudentController {

    private StudentDAO studentDAO;
    private StudentView studentView;
    private ClasseDAO classeDAO;

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
}
