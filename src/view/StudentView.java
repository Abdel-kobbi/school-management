package view;

import java.util.ArrayList;

import model.Student;

public class StudentView {

    public void displayStudentMenu() {
        System.out.println("-------- Gestion des etudiant --------");
        System.out.println("1. Pour ajouter etudiant");
        System.out.println("2. Pour Afficher les etudiants");
        System.out.println("3. Pour supprimer etudiant");
        System.out.println("4. Pour sortie");
    }

    public void displayStudent(ArrayList<Student> students ){
        System.out.println("---------- Liste des etudiants ---------");
        for(Student s : students){
            System.out.println(s);
        }
        System.out.println("---------- Liste des etudiants ---------");
    }
}
