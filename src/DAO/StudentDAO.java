package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.ConenxionDb;
import model.Student;

public class StudentDAO implements GenericDAO<Student, Integer> {
    private static final Connection db = ConenxionDb.getDb();

    @Override
    public boolean save(Student student) {
        String sql = "INSERT INTO students(nom, age, classe_id) values(?,?,?);";
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, student.getNom());
            stm.setInt(2, student.getAge());
            stm.setInt(3, student.getClasse().getId());
            int rowAffected = stm.executeUpdate();
            if (rowAffected > 0) {
                return true;
            }
            stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public Student findById(Integer id) {
        String sql = "SELECT * FROM students WHERE id = ?;";
        Student student = null;
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                student = new Student(result.getInt("id"),
                        result.getString("nom"),
                        result.getInt("age"),
                        new ClasseDAO().findById(result.getInt("classe_id")));
            }
            stm.close();
            result.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM students;";
        List<Student> students = new ArrayList<>();
        try {
            Statement stm = db.createStatement();
            ResultSet result = stm.executeQuery(sql);
            while (result.next()) {
                students.add(new Student(result.getInt("id"),
                        result.getString("nom"),
                        result.getInt("age"),
                        new ClasseDAO().findById(result.getInt("classe_id"))));
            }
            stm.close();
            result.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return students;
    }

    @Override
    public boolean update(Student student) {
        String sql = "UPDATE students SET nom = ?, age = ?, classe_id = ? WHERE id = ?;";
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, student.getNom());
            stm.setInt(2, student.getAge());
            stm.setInt(3, student.getClasse().getId());
            stm.setInt(4, student.getId());
            int rowAffected = stm.executeUpdate();
            if (rowAffected > 0) {
                return true;
            }
            stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM students WHERE id = ?;";
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setInt(1, id);
            int rowAffected = stm.executeUpdate();
            if (rowAffected > 0) {
                return true;
            }
            stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

}
