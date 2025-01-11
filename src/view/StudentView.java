package view;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import model.ClassSchool;
import model.Student;

public class StudentView extends JFrame {

    private JPanel panelForm;
    private JLabel title;
    private JLabel nomLabel;
    private JTextField txtNom;
    private JLabel ageLabel;
    private JTextField txtAge;
    private JLabel classeLabel;
    private JComboBox<ClassSchool> listClasses;

    private JButton btnAdd;
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;
    private DefaultTableModel tableModel;
    private JTable tableStudent;

    public StudentView(List<ClassSchool> classSchools) {
        this.setTitle("Gestion des Étudiants");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout(20, 20));
        this.getContentPane().setBackground(MainView.backgroundColor);

        // Entête
        this.title = new JLabel("Gestion des Étudiant", JLabel.CENTER);
        this.title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        this.title.setForeground(MainView.primaryColor);
        this.add(this.title, BorderLayout.NORTH);

        // Formulaire
        this.panelForm = new JPanel();
        this.panelForm.setLayout(new GridBagLayout());
        this.panelForm.setPreferredSize(new Dimension(300, 100));
        this.panelForm.setBackground(MainView.secondaryColor);
        this.panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.nomLabel = new JLabel("Nom :");
        this.nomLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(this.nomLabel, gbc);

        this.txtNom = new JTextField();
        this.txtNom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        panelForm.add(this.txtNom, gbc);

        this.ageLabel = new JLabel("Âge :");
        this.ageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelForm.add(this.ageLabel, gbc);

        this.txtAge = new JTextField();
        this.txtAge.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        panelForm.add(this.txtAge, gbc);

        this.classeLabel = new JLabel("Classe :");
        this.classeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelForm.add(this.classeLabel, gbc);

        this.listClasses = new JComboBox<>(classSchools.toArray(new ClassSchool[0]));
        this.listClasses.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        panelForm.add(this.listClasses, gbc);

        this.btnAdd = MainView.createModerButton("Ajouter", MainView.primaryColor, MainView.secondaryColor);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.CENTER;
        panelForm.add(this.btnAdd, gbc);

        this.add(panelForm, BorderLayout.WEST);

        this.tableModel = new DefaultTableModel(new String[] { "ID", "Nom", "Âge", "Classe" }, 0);

        this.tableStudent = new JTable(tableModel);
        this.tableStudent.setDefaultEditor(Object.class, null); // Désactiver l'édition
        this.tableStudent.setRowHeight(30);
        this.tableStudent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.tableStudent.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        this.tableStudent.getTableHeader().setBackground(MainView.primaryColor);
        this.tableStudent.getTableHeader().setForeground(MainView.secondaryColor);
        this.tableStudent.setSelectionBackground(MainView.tableRowColor);
        JScrollPane scrollPane = new JScrollPane(tableStudent);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel panelActions = new JPanel();
        panelActions.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelActions.setBackground(MainView.backgroundColor);

        btnNew = MainView.createModerButton("Nouveau", MainView.primaryColor, MainView.secondaryColor);
        btnUpdate = MainView.createModerButton("Modifier", MainView.orange, MainView.secondaryColor);
        btnDelete = MainView.createModerButton("Supprimer", MainView.red, MainView.secondaryColor);

        panelActions.add(btnNew);
        panelActions.add(btnUpdate);
        panelActions.add(btnDelete);

        // button disabled
        btnNew.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);

        this.add(panelActions, BorderLayout.SOUTH);
    }

    public JTextField getTxtNom() {
        return txtNom;
    }

    public JTextField getTxtAge() {
        return txtAge;
    }

    public JComboBox<ClassSchool> getListClasses() {
        return listClasses;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnNew() {
        return btnNew;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTableStudent() {
        return tableStudent;
    }

    public void displayStudentMenu() {
        System.out.println("-------- Gestion des étudiants --------");
        System.out.println("1. Pour ajouter etudiant");
        System.out.println("2. Pour Afficher les etudiants");
        System.out.println("3. Pour modifier etudiant");
        System.out.println("4. Pour supprimer etudiant");
        System.out.println("5. Retour au menu principale ");
        System.out.print("Votre choix: ");
    }

    public void displayStudent(List<Student> students) {
        System.out.println("---------- Liste des etudiants ---------");
        if (students.size() > 0) {
            for (Student s : students) {
                System.out.println(s);
            }
        } else {
            System.out.println("Pas des étudiant.");
        }
        System.out.println("---------- Fin Liste des etudiants ---------");
    }
}
