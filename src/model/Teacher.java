package model;

public class Teacher {
    private String nom;
    private int age, id;
    private String module;

    public Teacher(int id, String nom, int age, String module) {
        this.id = id;
        this.nom = nom;
        this.age = age;
        this.module = module;
    }

    public Teacher(String nom, int age, String module) {
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String toString() {
        return "ID: " + this.getId() + ", Nom: " + this.getNom() + ", Age: " + this.getAge() + " ans, Module: "
                + this.getModule() + ".";
    }

}
