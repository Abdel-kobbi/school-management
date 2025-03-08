package controller;

import java.util.List;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
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
    private JButton newButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTable table;

    public TeacherController() {
        this.teacherDAO = new TeacherDAO();
        this.teacherView = new TeacherView();
        this.txtNom = this.teacherView.getTxtNom();
        this.txtAge = this.teacherView.getTxtAge();
        this.txtModule = this.teacherView.getTxtModule();
        this.addButton = this.teacherView.getBtnAdd();
        this.tableModel = this.teacherView.getTableModel();
        this.table = this.teacherView.getTableTeacher();
        this.updateButton = this.teacherView.getBtnUpdate();
        this.deleteButton = this.teacherView.getBtnDelete();
        this.newButton = this.teacherView.getBtnNew();
        this.loadTeachers();
        this.addButton.addActionListener(e -> addTeacher());
        this.table.getSelectionModel().addListSelectionListener(e -> this.enableDeleteAndUpdateBtn(e));
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
                JOptionPane.showMessageDialog(this.teacherView, "L'Enseignant a été ajouter avec succée.", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                this.emptyForm();
                loadTeachers();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(teacherView, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void enableDeleteAndUpdateBtn(ListSelectionEvent e) {

        // Supprimer les listeners du bouton de suppression
        for (ActionListener al : this.deleteButton.getActionListeners()) {
            this.deleteButton.removeActionListener(al);
        }

        // Supprimer les listeners du bouton de modification
        for (ActionListener al : this.updateButton.getActionListeners()) {
            this.updateButton.removeActionListener(al);
        }

        if (!e.getValueIsAdjusting()) { // Éviter les événements multiples
            int[] selectedRows = table.getSelectedRows(); // Obtenir les lignes sélectionnées

            if (selectedRows.length == 1) { // Une seule ligne sélectionnée
                this.deleteButton.setEnabled(true);
                this.updateButton.setEnabled(true);
                this.newButton.setEnabled(true);
                this.addButton.setEnabled(false);

                // Récupérer les données de la ligne sélectionnée
                int id = (int) this.table.getValueAt(selectedRows[0], 0);
                String nom = (String) this.tableModel.getValueAt(selectedRows[0], 1);
                int age = (int) this.tableModel.getValueAt(selectedRows[0], 2);
                String module = (String) this.tableModel.getValueAt(selectedRows[0], 3);

                // Remplir les champs avec les données sélectionnées
                this.txtNom.setText(nom);
                this.txtAge.setText(String.valueOf(age));
                this.txtModule.setText(module);

                // Ajouter le nouveau listener pour la suppression
                this.deleteButton.addActionListener(event -> this.deleteTeacher(id));
                // Ajouter le nouveau listener pour la modification
                this.updateButton.addActionListener(event -> this.updateTeacher(id));
                // Ajouter le nouveau listener pour la nouveau étudiant
                this.newButton.addActionListener(event -> this.enableAddNewTeacher());
            } else { // Aucune ou plusieurs lignes sélectionnées
                this.enableAddNewTeacher();
            }
        }
    }

    private void deleteTeacher(int id) {
        int confirme = JOptionPane.showConfirmDialog(this.teacherView,
                "Etes-vous sûr de vouloir supprimer cet Enseignant?", "Confirmation de suppression",
                JOptionPane.YES_NO_OPTION);

        if (confirme == JOptionPane.YES_OPTION) {
            boolean isDeleted = this.teacherDAO.delete(id);
            if (isDeleted) {
                JOptionPane.showMessageDialog(this.teacherView, "L'Enseignant a été supprimé avec succès.", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                this.loadTeachers();
            } else {
                JOptionPane.showMessageDialog(this.teacherView,
                        "Une erreur est survenue, merci de réessayer plus tard.", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTeacher(int id) {
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
            boolean isSave = this.teacherDAO.update(new Teacher(id, nameValue, parseInt(ageValue), moduleValue));
            if (isSave) {
                JOptionPane.showMessageDialog(this.teacherView, "L'Enseignant a été modifier avec succée.", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                this.emptyForm();
                loadTeachers();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(teacherView, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void enableAddNewTeacher() {
        this.updateButton.setEnabled(false);
        this.deleteButton.setEnabled(false);
        this.newButton.setEnabled(false);
        this.addButton.setEnabled(true);
        this.emptyForm();
        // Désélectionné la liste
        this.table.clearSelection();
    }

    private void emptyForm() {
        this.txtNom.setText("");
        this.txtAge.setText("");
        this.txtModule.setText("");
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

    public TeacherView getTeacherView() {
        return this.teacherView;
    }
}
