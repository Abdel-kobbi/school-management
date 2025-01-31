package controller;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
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
    private JButton deleteButton;
    private JButton updateButton;
    private JButton newButton;
    private DefaultTableModel tableModel;
    private JTable table;

    public StudentController() {
        this.studentDAO = new StudentDAO();
        this.classeDAO = new ClasseDAO();
        this.studentView = new StudentView();
        this.txtNom = this.studentView.getTxtNom();
        this.txtAge = this.studentView.getTxtAge();
        this.listClasses = this.studentView.getListClasses();
        this.tableModel = this.studentView.getTableModel();
        this.addButton = this.studentView.getBtnAdd();
        this.table = this.studentView.getTableStudent();
        this.deleteButton = this.studentView.getBtnDelete();
        this.updateButton = this.studentView.getBtnUpdate();
        this.newButton = this.studentView.getBtnNew();
        this.addClassesToComboBox();
        this.loadStudent();
        this.addButton.addActionListener(e -> addStudent());
        this.table.getSelectionModel().addListSelectionListener(e -> this.enableDeleteAndUpdateBtn(e));
    }

    public void addClassesToComboBox() {
        // for update the compoBox
        this.listClasses.setModel(new DefaultComboBoxModel<>());
        for (ClassSchool classe :this.classeDAO.findAll()) {
            this.listClasses.addItem(classe);
        }
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
                this.emptyForm();
                loadStudent();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(studentView, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
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
                ClassSchool classe = (ClassSchool) this.tableModel.getValueAt(selectedRows[0], 3);

                // Remplir les champs avec les données sélectionnées
                this.txtNom.setText(nom);
                this.txtAge.setText(String.valueOf(age));
                this.listClasses.setSelectedItem(classe);

                // Ajouter le nouveau listener pour la suppression
                this.deleteButton.addActionListener(event -> this.deleteStudent(id));
                // Ajouter le nouveau listener pour la modification
                this.updateButton.addActionListener(event -> this.updateStudent(id));
                // Ajouter le nouveau listener pour la nouveau étudiant
                this.newButton.addActionListener(event -> this.enableAddNewStudent());
            } else { // Aucune ou plusieurs lignes sélectionnées
                this.enableAddNewStudent();
            }
        }
    }

    private void deleteStudent(int id) {
        int confirme = JOptionPane.showConfirmDialog(this.studentView,
                "Etes-vous sûr de vouloir supprimer cet étudiant?", "Confirmation de suppression",
                JOptionPane.YES_NO_OPTION);

        if (confirme == JOptionPane.YES_OPTION) {
            boolean isDeleted = this.studentDAO.delete(id);
            if (isDeleted) {
                JOptionPane.showMessageDialog(this.studentView, "L'étudiant a été supprimé avec succès.", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                this.loadStudent();
            } else {
                JOptionPane.showMessageDialog(this.studentView,
                        "Une erreur est survenue, merci de réessayer plus tard.", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateStudent(int id) {
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

            boolean isUpdated = this.studentDAO.update(new Student(id, valueNom, parseInt(valueAge), classe));
            if (isUpdated) {
                JOptionPane.showMessageDialog(studentView, "L'étudiant a été modifier avec succès.", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                this.emptyForm();
                loadStudent();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(studentView, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void enableAddNewStudent() {
        this.updateButton.setEnabled(false);
        this.deleteButton.setEnabled(false);
        this.newButton.setEnabled(false);
        this.addButton.setEnabled(true);
        // Réinitialiser les champs du formulaire
        this.emptyForm();
        // Désélectionné la liste
        this.table.clearSelection();
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

    private void emptyForm() {
        this.txtNom.setText("");
        this.txtAge.setText("");
        this.listClasses.setSelectedIndex(0);
    }

    public StudentView getStudentView() {
        return studentView;
    }
}
