package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainView extends JFrame {

    // Palette de couleurs
    public static final Color primaryColor = Color.decode("#0078D7");
    public static final Color secondaryColor = Color.decode("#FFFFFF");
    public static final Color backgroundColor = Color.decode("#F3F3F3");
    public static final Color tableRowColor = Color.decode("#E8F4FF");
    public static final Color orange = Color.decode("#F39C12"); // orange
    public static final Color red = Color.decode("#E74C3C"); // red

    private JMenuItem studentManagement;
    private JMenuItem teacherManagement;
    private JMenuItem classesManagement;
    private JMenuItem exitItem;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainView() {

        // add splach screen before start the app
        new SplashScreen().splashScreen();
        this.setTitle("Gestion d'école");
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(800, 600));
        this.getContentPane().setBackground(MainView.backgroundColor);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add icon

        ImageIcon icon = new ImageIcon("src/res/ecole.png");

        this.setIconImage(icon.getImage());

        cardLayout = new CardLayout();

        mainPanel = new JPanel();

        mainPanel.setLayout(cardLayout);

        this.add(mainPanel, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        JMenu ecoleMenu = new JMenu("Gestion d'école");
        JMenu exitMenu = new JMenu("Quitter");

        studentManagement = new JMenuItem("Gestion des Étudiants");
        teacherManagement = new JMenuItem("Gestion des Enseignants");
        classesManagement = new JMenuItem("Gestion des classes");

        exitItem = new JMenuItem("Quitter");

        ecoleMenu.add(studentManagement);
        ecoleMenu.addSeparator();
        ecoleMenu.add(teacherManagement);
        ecoleMenu.addSeparator();
        ecoleMenu.add(classesManagement);

        exitMenu.add(exitItem);

        menuBar.add(ecoleMenu);
        menuBar.add(exitMenu);

        this.setJMenuBar(menuBar);

    }

    public static JButton createModerButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        return button;
    }

    public JMenuItem getStudentManagement() {
        return studentManagement;
    }

    public JMenuItem getTeacherManagement() {
        return teacherManagement;
    }

    public JMenuItem getClassesManagement() {
        return classesManagement;
    }

    public JMenuItem getExitItem() {
        return exitItem;
    }

    public CardLayout getCardLayout() {
        return this.cardLayout;
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }
}
