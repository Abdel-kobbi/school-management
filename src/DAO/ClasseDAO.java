package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.ConenxionDb;
import model.ClassSchool;

public class ClasseDAO implements GenericDAO<ClassSchool, Integer> {
    private static final Connection db = ConenxionDb.getDb();

    @Override
    public boolean save(ClassSchool classe) {
        String sql = "INSERT INTO classes (nom, teacher_id) VALUES (?,?);";
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, classe.getClassName());
            stm.setInt(2, classe.getTeacher().getId());
            int result = stm.executeUpdate();
            if (result > 0) {
                return true;
            }
            stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return false;
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return classes;
    };

    @Override
    public boolean update(ClassSchool classe) {
        String sql = "UPDATE classes set nom = ?, teacher_id = ? WHERE id = ?;";
        try {
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, classe.getClassName());
            stm.setInt(2, classe.getTeacher().getId());
            stm.setInt(3, classe.getId());
            int result = stm.executeUpdate();
            if (result > 0) {
                return true;
            }
            stm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    };

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM classes WHERE id = ?;";
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
        return false;
    };
}
