package view;

import model.ClassSchool;
import model.Teacher;

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

public class ClassSchoolView extends JFrame {

    private JLabel title;
    private JPanel panelForm;
    private JLabel nomLabel;
    private JTextField txtNom;
    private JLabel teacherLabel;
    private JComboBox<Teacher> listTeaches;
    private JButton btnAdd;
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;
    private DefaultTableModel tableModel;
    private JTable tableClasses;

    public ClassSchoolView(List<Teacher> teachers) {
        this.setTitle("Gestion des classes");
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(800, 600));
        this.setLayout(new BorderLayout(20, 20));
        this.getContentPane().setBackground(MainView.backgroundColor);

        // Entête
        this.title = new JLabel("Gestion des classes", JLabel.CENTER);
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

        this.teacherLabel = new JLabel("Enseignant :");
        this.teacherLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelForm.add(this.teacherLabel, gbc);

        this.listTeaches = new JComboBox<>(teachers.toArray(new Teacher[0]));
        this.listTeaches.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        panelForm.add(this.listTeaches, gbc);

        this.btnAdd = MainView.createModerButton("Ajouter", MainView.primaryColor, MainView.secondaryColor);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.CENTER;
        panelForm.add(this.btnAdd, gbc);

        this.add(panelForm, BorderLayout.WEST);

        this.tableModel = new DefaultTableModel(new String[] { "ID", "Nom", "Enseignant" }, 0);

        this.tableClasses = new JTable(tableModel);
        this.tableClasses.setDefaultEditor(Object.class, null); // Désactiver l'édition
        this.tableClasses.setRowHeight(30);
        this.tableClasses.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.tableClasses.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        this.tableClasses.getTableHeader().setBackground(MainView.primaryColor);
        this.tableClasses.getTableHeader().setForeground(MainView.secondaryColor);
        this.tableClasses.setSelectionBackground(MainView.tableRowColor);
        JScrollPane scrollPane = new JScrollPane(this.tableClasses);
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

    public ClassSchoolView() {

    }

    public void displayClassSchoolMenu() {
        System.out.println("------ Gestion des classes ------");
        System.out.println("1. Ajouter une classe");
        System.out.println("2. Modifier une classe");
        System.out.println("3. Supprimer une classe");
        System.out.println("4. Afficher liste des classes");
        System.out.println("5. Afficher les etudiants d'une classes");
        System.out.println("6. Retour au menu principale");
        System.out.print("Votre choix: ");
    }

    public void displayClassRoom(List<ClassSchool> classSchool) {
        System.out.println("------ Liste des classes ------");
        if (classSchool.size() > 0) {
            for (ClassSchool classRoom : classSchool) {
                System.out.println(classRoom);
            }
        } else {
            System.out.println("Pas des classes.");
        }
        System.out.println("------ Fin Liste des classes ------");
    }

    public JTextField getTxtNom() {
        return txtNom;
    }

    public JLabel getTeacherLabel() {
        return teacherLabel;
    }

    public JComboBox<Teacher> getListTeaches() {
        return listTeaches;
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

    public JTable getTableClasses() {
        return tableClasses;
    }
}
