package jdbc.util;

import java.sql.Connection;

import jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;

import java.sql.DriverManager;
import java.sql.SQLException;

//public class Util {
//    // реализуйте настройку соеденения с БД
//    public static Connection getConnection() {
//        String dbURL = "jdbc:mysql://localhost:3306/mydbtest";
//        String dbUser = "root";
//        String dbPass = "goldrain";
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return connection;
//
//    }
//}
public class Util {
    // реализуйте настройку соеденения с БД
    private static SessionFactory sessionFactory;
    public static SessionFactory getConnection() {
        Configuration configuration = new Configuration();
        // Настройка параметров подключения к базе данных
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydbtest");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "goldrain");
        // Настройка диалекта Hibernate
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        // Дополнительные параметры Hibernate
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        // Добавление аннотированных классов (Entity)
        configuration.addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;

    }
}
