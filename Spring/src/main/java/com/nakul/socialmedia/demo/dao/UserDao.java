package com.nakul.socialmedia.demo.dao;

import com.nakul.socialmedia.demo.models.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserDao {

    private SessionFactory sessionFactory;

    @Transactional
    public List<UserModel> getUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<UserModel> users = session.createQuery("from user", UserModel.class).list();
        return users;
    }
}
