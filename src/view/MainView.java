package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class MainView {

    // Palette de couleurs
    public static final Color primaryColor = Color.decode("#0078D7");
    public static final Color secondaryColor = Color.decode("#FFFFFF");
    public static final Color backgroundColor = Color.decode("#F3F3F3");
    public static final Color tableRowColor = Color.decode("#E8F4FF");
    public static final Color orange = Color.decode("#F39C12"); // orange
    public static final Color red = Color.decode("#E74C3C"); // orange

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

    public void displayStartMenu() {
        System.out.println("-------- Management de l'ecole --------");
        System.out.println("1. Pour gestion des Enseignants");
        System.out.println("2. Pour gestion des etudiants");
        System.out.println("3. Pour gestion des classes");
        System.out.println("4. Informations de l'école");
        System.out.println("5. Quitter");
        System.out.print("Votre choix: ");
    }

    public void displayInfoSchool(int nbTeacher, int nbStudent, int nbClass) {
        System.out.println("------- Informations de l'école -------");
        System.out.println("Nombre des Enseignants: " + nbTeacher);
        System.out.println("Nombre des etudiants: " + nbStudent);
        System.out.println("Nombre des classes: " + nbClass);
        System.out.println("------- Fin Informations de l'école -------");
    }
}
