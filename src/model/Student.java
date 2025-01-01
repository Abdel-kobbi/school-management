package model;

public class Student {
    private int id;
    private String nom;
    private int age;
    private ClassSchool classe;

    public Student(int id, String nom, int age, ClassSchool classe) {
        this.id = id;
        this.nom = nom;
        this.age = age;
        this.classe = classe;
    }

    public Student(String nom, int age, ClassSchool classe) {
        this.nom = nom;
        this.age = age;
        this.classe = classe;
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

    public ClassSchool getClasse() {
        return classe;
    }

    public void setClasse(ClassSchool classe) {
        this.classe = classe;
    }

    public String toString() {
        return "Id: " + this.getId() + ", Nom: " + this.getNom() + ", Age: " + this.getAge() +
                " ans, La classe : " + this.getClasse().getClassName();
    }

}
