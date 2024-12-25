package view;

import java.util.List;

import model.Teacher;

public class TeacherView {

    public void displayTeacherMenu() {
        System.out.println("-------- Gestion des Enseignant --------");
        System.out.println("1. Pour ajouter Enseignants");
        System.out.println("2. Pour Afficher les Enseignants");
        System.out.println("3. Pour modifier Enseignant");
        System.out.println("4. Pour supprimer Enseignant");
        System.out.println("5. Retour au menu principale ");
        System.out.print("Votre choix: ");
    }

    public void displayTeachers(List<Teacher> teachers) {
        System.out.println("----- Liste des Enseignant -----");
        if (teachers.size() > 0) {
            for (Teacher t : teachers) {
                System.out.println(t);
            }
        } else {
            System.out.println("Pas des Enseignants.");
        }
        System.out.println("----- Fin Liste des Enseignant -----");
    }

}
