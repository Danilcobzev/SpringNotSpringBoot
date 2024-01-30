package org.example.repos;

import org.example.Exceptions.BadRequestException;
import org.example.config.SessionFactoryConfig;
import org.example.dto.UserDto;
import org.springframework.stereotype.Repository;
import org.example.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepo {

    private final SessionFactoryConfig sessionFactoryConfig;

    public UserRepo(SessionFactoryConfig sessionFactoryConfig) {
        this.sessionFactoryConfig = sessionFactoryConfig;
    }

    public UserDto findById(long id){
        try (Session session = sessionFactoryConfig.getSession()) {
            return new UserDto(session.get(User.class, id).getUsername(),
                session.get(User.class, id).getPassword());
        } catch (HibernateException e) {
            throw new BadRequestException("user does not exists");
        }
    }

    public void saveUser(UserDto userDto) {
        try(Session session = sessionFactoryConfig.getSession()) {
            session.beginTransaction();
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            session.save(user);
            session.flush();
        }
    }

    List<UserDto> db = new ArrayList<>();
//    public void saveUser(UserDto userDto){
//        db.add(userDto);
//    }

    public List<UserDto> findAll(){
        return db;
    }
}
