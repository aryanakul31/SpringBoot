package com.nakul.user.misc

//@Repository
//class UserDaoImpl : UserDao {
//    @Autowired
//    private lateinit var entityManager: EntityManager
//    override fun create(user: UserModel): UserModel? {
//        val currentSession = entityManager.unwrap(Session::class.java)
//        currentSession.persist(user)
//        return user
//    }
//
//
//    override fun read(): List<UserModel> {
//        val currentSession = entityManager.unwrap(Session::class.java)
//
//        val query = currentSession.createQuery(
//            "from UserModel",
//            UserModel::class.java
//        )
//        return query.resultList?.filterNotNull() ?: emptyList();
//    }
//
//    override fun read(id: Int): UserModel? {
//        val currentSession = entityManager.unwrap(Session::class.java)
//        return currentSession.get(UserModel::class.java, id)
//    }
//
//    override fun update(userModel: UserModel?): UserModel? {
//        val currentSession = entityManager.unwrap(Session::class.java)
////        val user = read(userModel?.id ?: return null) ?: return null
//        currentSession.merge(userModel)
//        return userModel
//    }
//
//    override fun delete(id: Int): Boolean {
//        val currentSession = entityManager.unwrap(Session::class.java)
//        val response = currentSession.remove(read(id) ?: return false)
//        return true
//    }
//}