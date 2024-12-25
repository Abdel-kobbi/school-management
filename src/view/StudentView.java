package view;

import java.util.List;

import model.Student;

public class StudentView {

    public void displayStudentMenu() {
        System.out.println("-------- Gestion des etudiant --------");
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
