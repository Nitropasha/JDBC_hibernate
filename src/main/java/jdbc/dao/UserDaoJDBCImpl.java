//package jdbc.dao;
//
//import jdbc.model.User;
//import jdbc.util.Util;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//    private static String INSERT_STUDENT = "INSERT INTO students6 (name, lastname, age) VALUES (?,?,?)";
//    private static String DELETE_STUDENT = "DELETE FROM students6 WHERE id =?";
//    private static String DROP_TABLE = "TRUNCATE TABLE students6";
//    private static String DELETE_TABLE = "DELETE FROM students6";
//    private static String UPDATE_TABLE = "INSERT INTO students (name, lastname, age) VALUES ('ee','eee', 33)";
//    private static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS students6 (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastname VARCHAR(50), age INTEGER)";
//    private static String query = "SELECT * FROM students6";
//    public UserDaoJDBCImpl() {
//    }
//
//    public void createUsersTable() {
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE)) {
//            preparedStatement.execute();
//            System.out.println("Таблица создана");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void dropUsersTable() {
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(DROP_TABLE)) {
//            preparedStatement.execute();
//            System.out.println("Таблица Удалена");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setInt(3, age);
//            preparedStatement.execute();
//
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void removeUserById(long id) {
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {
//            preparedStatement.setInt(1, (int) id);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public List<User> getAllUsers() {
//        List<User> students = new ArrayList<>();
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String lastname = rs.getString("lastname");
//                byte age = (byte) rs.getInt("age");
//                students.add(new User(name, lastname, age));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return students;
//    }
//
//    public void cleanUsersTable() {
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TABLE)) {
//            preparedStatement.execute();
//            System.out.println("Таблица Очищина");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
