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


        try (Session session = getConnection().openSession()) {
            session.beginTransaction();
            session.createNativeQuery(CREATE_TABLE).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table created successfully!");
        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    @Override
    public void dropUsersTable() {

//        Transaction transaction = null;
        try (Session session = getConnection().openSession()) {
            session.beginTransaction();
            String createTableSQL = DROP_TABLE;
            session.createNativeQuery(createTableSQL).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table delete successfully!");
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {


        try (Session session = getConnection().openSession()) {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = getConnection().openSession()) {
            session.beginTransaction();
            session.createQuery("delete from User where id = :userId")
                    .setParameter("userId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            System.out.println("User deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> emps = new ArrayList<>();
        try (Session session = getConnection().openSession()) {
            session.beginTransaction();
            emps = session.createQuery("from User").getResultList();
            for (User e : emps)
                System.out.println(e);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            emps = new ArrayList<>();
        }
        return emps;
    }

    public User getById(long i) {
        User user = new User();
        try (Session session = getConnection().openSession()) {
            session.beginTransaction();
            user = session.get(User.class, i);
            System.out.println("ПОЛУЧАЕМ ПОЛЬЗОВАТЕЛЬЯ ПО АЙДИ");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = getConnection().openSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table cleaned");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
