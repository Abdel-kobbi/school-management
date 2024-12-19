package model;


public class Teacher {
    private static int idU = 0;
    private String nom;
    private int age, id;
    private String module;

    public Teacher(String nom, int age, String module){
        this.id = ++idU;
        this.nom = nom;
        this.age = age;
        this.module = module;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getModule() {
        return module;
    }

    public String toString(){
        return "ID: " + this.getId() + ", Nom: " + this.getNom() + ", Age: " + this.getAge() + ", Module: " + this.getModule();
    }

}
