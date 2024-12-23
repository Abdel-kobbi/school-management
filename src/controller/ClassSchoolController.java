package controller;

import java.util.Scanner;

import model.ClassSchool;
import model.School;
import model.Student;
import model.Teacher;
import view.ClassSchoolView;
import view.StudentView;
import view.TeacherView;

public class ClassSchoolController {
    private static Scanner input;
    private School school;
    private ClassSchoolView classSchoolView;
    private TeacherView teacherView;
    private StudentView studentView;

    public ClassSchoolController() {
        this.school = School.getSchool();
        this.classSchoolView = new ClassSchoolView();
        this.teacherView = new TeacherView();
        this.studentView = new StudentView();
        input = new Scanner(System.in);
    }

    public void start() {
        int entry, id;
        String nom;
        do {
            this.classSchoolView.displayClassSchoolMenu();
            entry = input.nextInt();
            switch (entry) {
                case 1:
                    System.out.print("Entre le nom de la classe: ");
                    input.nextLine();
                    nom = input.nextLine();
                    this.teacherView.displayTeachers(school.getTeachers());
                    System.out.print("choisi l'id d'Enseignant: ");
                    id = input.nextInt();
                    Teacher teacher = school.searchTeacher(id);
                    if (teacher != null) {
                        this.school.addClass(new ClassSchool(nom, teacher));
                    } else {
                        System.out.println("Enseignant n'existe pas!");
                    }
                    break;
                case 2:
                    this.classSchoolView.displayClassRoom(school.getListClassRoom());
                    System.out.print("choisi l'id de la classe a supprimer: ");
                    id = input.nextInt();
                    ClassSchool classRoom = school.searchClassRomm(id);
                    if (classRoom != null) {
                        school.removeClass(classRoom);
                    } else {
                        System.out.println("La classe n'existe pas!");
                    }
                    break;
                case 3:
                    this.classSchoolView.displayClassRoom(this.school.getListClassRoom());
                    System.out.print("choisi l'id de la classe pour ajouter des étudiants: ");
                    int classId = input.nextInt();
                    ClassSchool cSchool = this.school.searchClassRomm(classId);
                    if (cSchool != null) {
                        this.studentView.displayStudent(this.school.getStudents());
                        System.out
                                .print("choisi l'id de l'etudiant pour ajouter a la classe " + cSchool.getClassName()
                                        + ": ");
                        int studentId = input.nextInt();
                        Student student = this.school.searchStudent(studentId);
                        if (student != null) {
                            cSchool.addStudentToClass(student);
                        } else {
                            System.out.println("L'etudiant n'existe pas!");
                        }
                    } else {
                        System.out.println("La classe n'existe pas!");
                    }
                    break;
                case 4:
                    this.classSchoolView.displayClassRoom(this.school.getListClassRoom());
                    break;
                case 5:
                    this.classSchoolView.displayClassRoom(this.school.getListClassRoom());
                    System.out.print("Choisi le classe pour lister ces etudiants: ");
                    id = input.nextInt();
                    ClassSchool classRomm = this.school.searchClassRomm(id);
                    if (classRomm != null) {
                        this.studentView.displayStudent(classRomm.getStudents());
                    } else {
                        System.out.println("La classe n'existe pas!");
                    }
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Choix invalide!");
                    break;
            }
        } while (entry != 6);
    }

}
