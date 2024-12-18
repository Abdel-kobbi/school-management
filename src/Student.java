public class Student {
    private int id = 0;
    private String nom;
    private int age;

    public Student(int id, String nom, int age){
        this.id = id;
        this.nom = nom;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getNom(){
        return this.nom;
    }

    public int getAge(){
        return this.age;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return "Id: " + this.id +", Nom: "+ this.nom + " Age: " + this.age +" ans.";
    }

}
