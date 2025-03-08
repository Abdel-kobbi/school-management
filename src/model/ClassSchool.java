package model;

public class ClassSchool {
    private int id;
    private String className;
    private Teacher teacher;

    public ClassSchool(int id, String className, Teacher teacher) {
        this.id = id;
        this.className = className;
        this.teacher = teacher;
    }

    public ClassSchool(String className, Teacher teacher) {
        this.className = className;
        this.teacher = teacher;
    }

    // getters
    public int getId() {
        return this.id;
    }

    public String getClassName() {
        return this.className;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    // setters
    public void setClassName(String className) {
        this.className = className;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return this.getClassName().toUpperCase();
    }

    @Override // override this method for using in the JComboBox to setSelectedItem
    public boolean equals(Object obj) {
        return this.getId() == ((ClassSchool) obj).getId();
    }
}
