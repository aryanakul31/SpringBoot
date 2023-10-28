package com.nakul.user.dao;

import com.nakul.user.model.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImplTemp implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(User.class, id);
    }

    @Override
    public User save(User user) {
    }



    @Nullable
    @Override
    public User create(@NotNull User user) {
        return null;
    }

    @Nullable
    @Override
    public List<User> read() {
        return null;
    }

    @Nullable
    @Override
    public User readById(int id) {
        return null;
    }

    @Override
    public User update(@Nullable Integer id, @Nullable User user) {
        if ()
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(user);
        return user;
    }


    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        User user = get(id);
        currentSession.delete(user);
    }
}
