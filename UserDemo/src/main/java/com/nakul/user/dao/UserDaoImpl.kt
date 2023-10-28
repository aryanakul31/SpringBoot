package com.nakul.user.dao

import com.nakul.user.model.User
import jakarta.persistence.EntityManager
import org.hibernate.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class UserDaoImpl : UserDao {

    @Autowired
    private lateinit var entityManager: EntityManager

    override fun create(user: User): User? {
        val currentSession = entityManager.unwrap(Session::class.java)
        val query = currentSession.createQuery(
            "from User",
            User::class.java
        )
        query.
        return query.getResultList()
    }

    override fun read(): List<User>? {
        val currentSession = entityManager.unwrap(Session::class.java)
        val query = currentSession.createQuery(
            "from User",
            User::class.java
        )
        return query.resultList
    }

    override fun readById(id: Int): User? {
        val currentSession = entityManager.unwrap(Session::class.java)
        val query = currentSession.createQuery(
            "from User",
            User::class.java
        )
        return query.getResultList()
    }

    override fun update(id: Int?, user: User?): User? {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }
}