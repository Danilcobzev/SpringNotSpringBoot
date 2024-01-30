package org.example.config;


import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

//конфигурация нашего hibernate
// при вызове new Configuration().buildSessionFactory() автоматически найдет hibernate.properties
@Service
public class SessionFactoryConfig {

    private SessionFactory sessionFactory;

    @PostConstruct
    void init() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(User.class)            //найти наши сущности
                .buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
