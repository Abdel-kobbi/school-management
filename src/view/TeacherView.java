package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

public class TeacherView extends JFrame {

    private JLabel title;
    private JPanel panelForm;
    private JLabel nomLabel;
    private JTextField txtNom;
    private JLabel ageLabel;
    private JTextField txtAge;
    private JLabel moduleLabel;
    private JTextField txtModule;
    private JButton btnAdd;
    DefaultTableModel tableModel;
    private JTable tableTeacher;
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;

    public TeacherView() {
        this.setTitle("Gestion des Enseignants");
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(800, 600));
        this.setLayout(new BorderLayout(20, 20));
        this.getContentPane().setBackground(MainView.backgroundColor);

        // Entête
        this.title = new JLabel("Gestion des Enseignants", JLabel.CENTER);
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

        this.moduleLabel = new JLabel("Matière :");
        this.moduleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelForm.add(this.moduleLabel, gbc);

        this.txtModule = new JTextField();
        this.txtModule.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        panelForm.add(this.txtModule, gbc);

        this.btnAdd = MainView.createModerButton("Ajouter", MainView.primaryColor, MainView.secondaryColor);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.CENTER;
        panelForm.add(this.btnAdd, gbc);

        this.add(panelForm, BorderLayout.WEST);

        this.tableModel = new DefaultTableModel(new String[] { "ID", "Nom", "Âge", "Matière" }, 0);

        this.tableTeacher = new JTable(tableModel);
        this.tableTeacher.setDefaultEditor(Object.class, null); // Désactiver l'édition
        this.tableTeacher.setRowHeight(30);
        this.tableTeacher.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.tableTeacher.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        this.tableTeacher.getTableHeader().setBackground(MainView.primaryColor);
        this.tableTeacher.getTableHeader().setForeground(MainView.secondaryColor);
        this.tableTeacher.setSelectionBackground(MainView.tableRowColor);
        JScrollPane scrollPane = new JScrollPane(this.tableTeacher);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel panelActions = new JPanel();
        panelActions.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelActions.setBackground(MainView.backgroundColor);

        this.btnNew = MainView.createModerButton("Nouveau", MainView.primaryColor, MainView.secondaryColor);
        this.btnUpdate = MainView.createModerButton("Modifier", MainView.orange, MainView.secondaryColor);
        this.btnDelete = MainView.createModerButton("Supprimer", MainView.red, MainView.secondaryColor);

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

    public JTextField getTxtModule() {
        return txtModule;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTableTeacher() {
        return tableTeacher;
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

}
