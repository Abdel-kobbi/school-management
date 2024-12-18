import java.util.ArrayList;
import java.util.Scanner;
public class App {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        int entry, age, id;
        String nom;
        do {
            System.out.println("1. Pour ajouter etudiant");
            System.out.println("2. Pour Afficher les etudiants");
            System.out.println("3. Pour supprimer etudiant");
            System.out.println("4. Pour sortie");
            entry = input.nextInt();
            switch (entry) {
                case 1:
                    System.out.println("Entre votre id");
                    id = input.nextInt();
                    input.nextLine();
                    System.out.println("Entre votre nom");
                    nom = input.nextLine();
                    System.out.println("Entre votre age");
                    age = input.nextInt();
                    addStudent(id, nom, age);
                    break;
                case 2:
                    afficherStudents();
                    break;
                case 3:
                    removeStudents();
                    break;
            }
        }while(entry != 4);

        System.out.println("Fin");

        input.close();
    }

    public static void addStudent(int id, String name, int age){
        students.add(new Student(id, name, age));
    }

    public static void afficherStudents(){
        System.out.println("---------- Liste des etudiants ---------");
        for(Student s : students){
            System.out.println(s);
        }
        System.out.println("---------- Liste des etudiants ---------");
    }

    public static void removeStudents(){
        System.out.println("---------- Liste des etudiants ---------");
        for(Student s : students){
            System.out.println(s);
        }
        System.out.println("choisi id de l'etudiant supprimer: ");
        int id = input.nextInt();
        for(Student s : students){
            if(s.getId() == id){
                students.remove(s);
                break;
            }
        }

    }
}
