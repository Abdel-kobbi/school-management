package controller;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.TeacherDAO;
import model.Teacher;
import view.TeacherView;

public class TeacherController {

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
        this.txtNom = this.teacherView.getTxtNom();
        this.txtAge = this.teacherView.getTxtAge();
        this.txtModule = this.teacherView.getTxtModule();
        this.addButton = this.teacherView.getBtnAdd();
        this.tableModel = this.teacherView.getTableModel();
    }

    public void start() {
        this.teacherView.setVisible(true);
        this.loadTeachers();
        this.addButton.addActionListener(e -> addTeacher());
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
}
