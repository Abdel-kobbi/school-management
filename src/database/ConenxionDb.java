package database;

import java.sql.*;

public class ConenxionDb {
    private static final String URL = "jdbc:sqlite:src/database/school.db";
    private static Connection db;

    private ConenxionDb() {
    };

    // Methode pour connecter a la base de donner
    public static Connection getDb() {
        try {
            if (db == null) {
                db = DriverManager.getConnection(URL);
                initialDataBase();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return db;
    }

    // creation des table de la base de donner
    public static void initialDataBase() {

        try {
            Connection connection = getDb();
            Statement stm = connection.createStatement();
            stm.execute("PRAGMA foreign_keys = ON;");

            // creation de table teacher
            String teacherTable = """
                    CREATE TABLE IF NOT EXISTS teachers(
                        id INTEGER primary key AUTOINCREMENT,
                        nom text not null,
                        age INTEGER,
                        module text
                    );
                    """;
            stm.execute(teacherTable);

            // creation de table classes
            String classesTable = """
                    CREATE TABLE IF NOT EXISTS classes(
                        id INTEGER primary key AUTOINCREMENT,
                        nom text not null,
                        teacher_id INTEGER,
                        FOREIGN KEY (teacher_id) REFERENCES teachers(id) ON DELETE RESTRICT
                    );
                    """;
            stm.execute(classesTable);

            // creation de table student
            String studentTable = """
                    CREATE TABLE IF NOT EXISTS students(
                        id INTEGER primary key AUTOINCREMENT,
                        nom text not null,
                        age INTEGER,
                        classe_id INTEGER,
                        FOREIGN KEY (classe_id) REFERENCES classes(id) ON DELETE RESTRICT
                    );
                    """;
            stm.execute(studentTable);
            System.out.println("La base de donner est crée avec succée.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
