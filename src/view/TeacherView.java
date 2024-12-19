package view;

import java.util.ArrayList;

import model.Teacher;

public class TeacherView {

    public void displayTeacherMenu(){
        System.out.println("-------- Gestion des Enseignant --------");
        System.out.println("1. Pour ajouter Enseignant");
        System.out.println("2. Pour Afficher les Enseignant");
        System.out.println("3. Pour supprimer Enseignant");
        System.out.println("4. Pour sortie");
    }


    public void displayTeachers(ArrayList<Teacher> teachers){
        System.out.println("----- Liste des Enseignant -----");
        for( Teacher t : teachers){
            System.out.println(t);
        }
        System.out.println("----- Liste des Enseignant -----");
    }

}
