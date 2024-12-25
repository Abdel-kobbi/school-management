package view;

import model.ClassSchool;
import java.util.List;

public class ClassSchoolView {

    public void displayClassSchoolMenu() {
        System.out.println("------ Gestion des classes ------");
        System.out.println("1. Ajouter une classe");
        System.out.println("2. Supprimer une classe");
        System.out.println("3. Ajouter des etudiants au classe");
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
}
