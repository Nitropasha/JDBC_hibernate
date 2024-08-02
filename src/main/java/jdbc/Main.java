package jdbc;

import jdbc.dao.UserDao;
import jdbc.dao.UserDaoHibernateImpl;

import jdbc.model.User;
import jdbc.service.UserService;
import jdbc.service.UserServiceImpl;
import jdbc.util.Util;
import org.hibernate.Session;

import static jdbc.util.Util.getConnection;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao1 = new UserDaoHibernateImpl();
        UserService userDao = new UserServiceImpl(userDao1);

        userDao.createUsersTable();
        userDao.saveUser("Name1", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 38);

        userDao.removeUserById(37);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        System.out.println(userDao.getAllUsers());

//      userDao.dropUsersTable();


    }
}
