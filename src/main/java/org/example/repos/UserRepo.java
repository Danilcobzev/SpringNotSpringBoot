package org.example.repos;

import org.example.Exceptions.MyHibernateException;
import org.example.config.SessionFactoryConfig;
import org.example.dto.UserDto;
import org.example.dto.UserDtoNewPassword;
import org.example.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepo {

    private final SessionFactoryConfig sessionFactoryConfig;

    public UserRepo(SessionFactoryConfig sessionFactoryConfig) {
        this.sessionFactoryConfig = sessionFactoryConfig;
    }

    public void saveUser(UserDto userDto) {
        try (Session session = sessionFactoryConfig.getSession()) {
            session.beginTransaction();
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            session.persist(user);
            session.flush();
        } catch (HibernateException e) {
            throw new MyHibernateException("Can't save");
        }
    }

    public void changePassword(UserDtoNewPassword userDtoNewPassword) {
        try (Session session = sessionFactoryConfig.getSession()) {
            session.beginTransaction();
            String sql;
            // Использование native SQL query с условием
            sql = "select id, username, password from USERS where username like :log";
            Query query = session.createSQLQuery (sql)          // SQL
                    .addEntity (User.class)        // Class
                    .setParameter ("log", userDtoNewPassword.getUsername()); // Condition
            List<User> users = query.list();
            User user = users.get(0);
            user.setPassword(userDtoNewPassword.getNewPassword());
            session.update(user);
            session.flush();
        } catch (HibernateException e) {
            throw new MyHibernateException("Can't change");
        }
    }

    public List<UserDto> findAll() {
        try (Session session = sessionFactoryConfig.getSession()) {
            session.beginTransaction();
            // HQL (Hibernate Query Language)
            String sql = "From " + User.class.getSimpleName();
            List<User> users = session.createQuery(sql).list();
            return users
                    .stream()
                    .map(user -> new UserDto(user.getUsername(), user.getPassword()))
                    .collect(Collectors.toList());
        } catch (HibernateException e) {
            throw new MyHibernateException("Can't find");
        }

    }
}
