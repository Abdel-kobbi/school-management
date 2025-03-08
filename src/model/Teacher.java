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

    @Override
    public String toString() {
        return this.getNom().toUpperCase();
    }

    @Override // override this method for using in the JComboBox to setSelectedItem
    public boolean equals(Object obj) {
        return this.getId() == ((Teacher) obj).getId();
    }

}
