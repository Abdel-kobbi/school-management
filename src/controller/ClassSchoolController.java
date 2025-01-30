package controller;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import DAO.ClasseDAO;
import DAO.TeacherDAO;
import model.ClassSchool;
import model.Teacher;
import view.ClassSchoolView;

public class ClassSchoolController {
    private ClasseDAO classeDAO;
    private TeacherDAO teacherDAO;
    private ClassSchoolView classSchoolView;

    // Recuperation des TextFiled & buttons
    private JTextField txtNom;
    private JComboBox<Teacher> listTeachers;
    private JButton addButton;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton newButton;
    private JTable table;

    public ClassSchoolController() {
        this.classeDAO = new ClasseDAO();
        this.teacherDAO = new TeacherDAO();
        this.classSchoolView = new ClassSchoolView(this.teacherDAO.findAll());
        this.txtNom = this.classSchoolView.getTxtNom();
        this.listTeachers = this.classSchoolView.getListTeachers();
        this.addButton = this.classSchoolView.getBtnAdd();
        this.tableModel = this.classSchoolView.getTableModel();
        this.deleteButton = this.classSchoolView.getBtnDelete();
        this.updateButton = this.classSchoolView.getBtnUpdate();
        this.newButton = this.classSchoolView.getBtnNew();
        this.table = this.classSchoolView.getTableClasses();
        this.loadClasses();
        this.addButton.addActionListener((e) -> addClass());
        this.table.getSelectionModel().addListSelectionListener(e -> this.enableDeleteAndUpdateBtn(e));
    }

    private void loadClasses() {
        try {
            List<ClassSchool> classes = this.classeDAO.findAll();
            this.tableModel.setRowCount(0); // vider le tableau
            for (ClassSchool classe : classes) {
                this.tableModel
                        .addRow(new Object[] { classe.getId(), classe.getClassName(), classe.getTeacher() });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.classSchoolView, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(this.classSchoolView, "La classe a été ajouter avec succée.", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                this.txtNom.setText("");
                this.listTeachers.setSelectedIndex(0);
                loadClasses();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.classSchoolView, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
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
                int id = (int) this.tableModel.getValueAt(selectedRows[0], 0);
                String nom = (String) this.tableModel.getValueAt(selectedRows[0], 1);
                Teacher teacher = (Teacher) this.table.getValueAt(selectedRows[0], 2);

                // Remplir les champs avec les données sélectionnées
                this.txtNom.setText(nom);
                this.listTeachers.setSelectedItem(teacher);

                // Ajouter le nouveau listener pour la suppression
                this.deleteButton.addActionListener(event -> this.deleteClasse(id));
                // Ajouter le nouveau listener pour la modification
                this.updateButton.addActionListener(event -> this.updateClasse(id));
                // Ajouter le nouveau listener pour la nouveau étudiant
                this.newButton.addActionListener(event -> this.enableAddNewClasse());
            } else { // Aucune ou plusieurs lignes sélectionnées
                this.enableAddNewClasse();
            }
        }
    }

    private void deleteClasse(int id) {
        int confirme = JOptionPane.showConfirmDialog(this.classSchoolView,
                "Etes-vous sûr de vouloir supprimer cette classe?", "Confirmation de suppression",
                JOptionPane.YES_NO_OPTION);

        if (confirme == JOptionPane.YES_OPTION) {
            boolean isDeleted = this.classeDAO.delete(id);
            if (isDeleted) {
                JOptionPane.showMessageDialog(this.classSchoolView, "La classe a été supprimé avec succès.", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                this.loadClasses();
            } else {
                JOptionPane.showMessageDialog(this.classSchoolView,
                        "Une erreur est survenue, merci de réessayer plus tard.", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateClasse(int id) {
        try {
            String valueNom = txtNom.getText();
            Teacher teacher = (Teacher) listTeachers.getSelectedItem();
            if (valueNom.isEmpty()) {
                throw new Exception("Le nom est nécessaire.");
            }
            boolean isUpdated = this.classeDAO.update(new ClassSchool(id, valueNom, teacher));
            if (isUpdated) {
                JOptionPane.showMessageDialog(this.classSchoolView, "La classe a été modifier avec succès.", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                this.emptyForm();
                this.loadClasses();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.classSchoolView, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void enableAddNewClasse() {
        this.updateButton.setEnabled(false);
        this.deleteButton.setEnabled(false);
        this.newButton.setEnabled(false);
        this.addButton.setEnabled(true);
        // Réinitialiser les champs du formulaire
        this.emptyForm();
    }

    private void emptyForm() {
        this.txtNom.setText("");
        this.listTeachers.setSelectedIndex(0);
    }

    public ClassSchoolView getClassSchoolView() {
        return this.classSchoolView;
    }
}
