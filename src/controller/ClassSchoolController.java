package controller;

import java.util.List;

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

public class ClassSchoolController {
    private ClasseDAO classeDAO;
    private TeacherDAO teacherDAO;
    private ClassSchoolView classSchoolView;
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
        this.studentView = new StudentView(this.classeDAO.findAll());
        this.txtNom = this.classSchoolView.getTxtNom();
        this.listTeachers = this.classSchoolView.getListTeaches();
        this.addButton = this.classSchoolView.getBtnAdd();
        this.tableModel = this.classSchoolView.getTableModel();
    }

    public void start() {
        this.classSchoolView.setVisible(true);
        this.loadClasses();
        this.addButton.addActionListener((e) -> addClass());
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
}
