package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.ConenxionDb;
import model.ClassSchool;
import model.Student;

public class ClasseDAO implements GenericDAO<ClassSchool, Integer> {
    private static final Connection db = ConenxionDb.getDb();

    @Override
    public void save(ClassSchool classe) {
        String sql = "INSERT INTO classes (nom, teacher_id) VALUES (?,?);";
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, classe.getClassName());
            stm.setInt(2, classe.getTeacher().getId());
            int result = stm.executeUpdate();
            if (result > 0) {
                System.out.println("La classe a été ajouté avec succès.");
            }
            stm.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    };

    @Override
    public ClassSchool findById(Integer id) {
        String sql = "SELECT * FROM classes WHERE id = ?;";
        ClassSchool classeSchool = null;
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                classeSchool = new ClassSchool(result.getInt("id"),
                        result.getString("nom"),
                        new TeacherDAO().findById(result.getInt("teacher_id")));
            }
            stm.close();
            result.close();
        } catch (SQLException e) {
            System.out.println("Error on findBy id class room: " + e.getMessage());
        }
        return classeSchool;
    };

    @Override
    public List<ClassSchool> findAll() {
        String sql = "SELECT * FROM classes;";
        List<ClassSchool> classes = new ArrayList<>();
        try {
            Statement stm = db.createStatement();
            ResultSet result = stm.executeQuery(sql);
            while (result.next()) {
                classes.add(new ClassSchool(result.getInt("id"),
                        result.getString("nom"),
                        new TeacherDAO().findById(result.getInt("teacher_id"))));
            }
            stm.close();
            result.close();
        } catch (SQLException e) {
            System.out.println("Error on findAll class room: " + e.getMessage());
        }
        return classes;
    };

    @Override
    public void update(ClassSchool classe) {
        String sql = "UPDATE classes set nom = ?, teacher_id = ? WHERE id = ?;";
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, classe.getClassName());
            stm.setInt(2, classe.getTeacher().getId());
            stm.setInt(3, classe.getId());
            int result = stm.executeUpdate();
            if (result > 0) {
                System.out.println("La classe est modifier avec succée.");
            } else {
                System.out.println("Aucun classe trouver avec cet ID.");
            }
            stm.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    };

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM classes WHERE id = ?;";
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setInt(1, id);
            int result = stm.executeUpdate();
            if (result > 0) {
                System.out.println("La classe est supprimer avec succée.");
            } else {
                System.out.println("Aucun classe trouver avec cet ID.");
            }
            stm.close();
        } catch (SQLException e) {
            System.out.println("Error on delete classe: " + e.getMessage());
        }
    };

    public List<Student> getStudents(ClassSchool classe) {
        String sql = "SELECT * FROM students WHERE classe_id = ?;";
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setInt(1, classe.getId());
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                students.add(new Student(result.getInt("id"),
                        result.getString("nom"),
                        result.getInt("age"),
                        new ClasseDAO().findById(result.getInt("classe_id"))));
            }
            stm.close();
            result.close();
        } catch (SQLException e) {
            System.out.println("Error on get all student of classroom: " + e.getMessage());
        }
        return students;
    }

    public int getNumberOfStudent(ClassSchool classe) {
        return this.getStudents(classe).size();
    }
}
