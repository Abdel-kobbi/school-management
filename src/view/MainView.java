package view;

public class MainView {

    public void displayStartMenu(){
        System.out.println("-------- Management de l'ecole --------");
        System.out.println("1. Pour gestion des Enseignants");
        System.out.println("2. Pour gestion des etudiants");
        System.out.println("3. Informations de l'école");
        System.out.println("4. Quitter");
        System.out.print("Votre choix: ");
    }

    public void displayInfoSchool(int nbTeacher, int nbStudent){
        System.out.println("------- Informations de l'école -------");
        System.out.println("Nombre des Enseignants: " + nbTeacher);
        System.out.println("Nombre des etudiants: " + nbStudent);
        System.out.println("------- Fin Informations de l'école -------");
    }
}
