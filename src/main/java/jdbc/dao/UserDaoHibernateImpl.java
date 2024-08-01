package jdbc.dao;

import jdbc.model.User;

import java.util.ArrayList;
import java.util.List;

import jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static jdbc.util.Util.*;

public class UserDaoHibernateImpl implements UserDao {
    private static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS new_student (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastname VARCHAR(50), age INTEGER)";
    private static String DROP_TABLE = "DROP TABLE IF EXISTS new_student";

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = getConnection().openSession();

        try {
            session.beginTransaction();
            session.createNativeQuery(CREATE_TABLE).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table created successfully!");
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            session.close();
        }

    }


    @Override
    public void dropUsersTable() {
        Session session = getConnection().openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            String createTableSQL = DROP_TABLE;
            session.createNativeQuery(createTableSQL).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table delete successfully!");
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getConnection().openSession();

        try {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User saved");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = getConnection().openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete from User where id = :userId")
                    .setParameter("userId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            System.out.println("User deleted");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = getConnection().openSession();
        List<User> emps = new ArrayList<>();
        try {
            session.beginTransaction();
            emps = session.createQuery("from User").getResultList();
            for (User e: emps)
                System.out.println(e);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            emps = new ArrayList<>();
        } finally {
            session.close();
        }
        return emps;
    }

    public User getById(long i) {
        Session session = getConnection().openSession();
       User user = new User();
        try {
            session.beginTransaction();
            user = session.get(User.class, i);
            System.out.println("ПОЛУЧАЕМ ПОЛЬЗОВАТЕЛЬЯ ПО АЙДИ");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public void cleanUsersTable() {
        Session session = getConnection().openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table cleaned");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
