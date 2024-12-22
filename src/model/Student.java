package model;

public class Student {
    private static int idU = 0;
    private int id;
    private String nom;
    private int age;

    public Student(String nom, int age) {
        this.id = ++idU;
        this.nom = nom;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return this.nom;
    }

    public int getAge() {
        return this.age;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Id: " + this.getId() + ", Nom: " + this.getNom() + " Age: " + this.getAge() + " ans.";
    }

}
