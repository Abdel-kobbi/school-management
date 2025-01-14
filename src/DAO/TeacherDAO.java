package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.ConenxionDb;
import model.Teacher;

public class TeacherDAO implements GenericDAO<Teacher, Integer> {
    private static final Connection db = ConenxionDb.getDb();

    @Override
    public boolean save(Teacher teacher) {
        String sql = "INSERT INTO teachers(nom, age, module) values (?,?,?);";
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, teacher.getNom());
            stm.setInt(2, teacher.getAge());
            stm.setString(3, teacher.getModule());
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
    public Teacher findById(Integer id) {
        String sql = "SELECT * FROM teachers WHERE id = ?;";
        Teacher teacher = null;
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                teacher = new Teacher(result.getInt("id"),
                        result.getString("nom"),
                        result.getInt("age"),
                        result.getString("module"));
            }
            stm.close();
            result.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return teacher;
    }

    @Override
    public List<Teacher> findAll() {
        String sql = "SELECT * FROM teachers;";
        List<Teacher> teachers = new ArrayList<>();
        try {
            Statement stm = db.createStatement();
            ResultSet result = stm.executeQuery(sql);
            while (result.next()) {
                teachers.add(new Teacher(result.getInt("id"),
                        result.getString("nom"),
                        result.getInt("age"),
                        result.getString("module")));
            }
            stm.close();
            result.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return teachers;
    }

    @Override
    public boolean update(Teacher teacher) {
        String sql = "UPDATE teachers set nom = ?, age = ?, module = ? WHERE id = ?;";
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, teacher.getNom());
            stm.setInt(2, teacher.getAge());
            stm.setString(3, teacher.getModule());
            stm.setInt(4, teacher.getId());
            int result = stm.executeUpdate();
            if (result > 0) {
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
        String sql = "DELETE FROM teachers WHERE id = ?;";
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setInt(1, id);
            int result = stm.executeUpdate();
            if (result > 0) {
                return true;
            }
            stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

}
