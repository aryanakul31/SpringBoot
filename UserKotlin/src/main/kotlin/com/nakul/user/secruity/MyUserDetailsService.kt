//package com.nakul.user.secruity
//
//import com.nakul.user.repo.user.UserRepo
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.core.userdetails.UsernameNotFoundException
//import org.springframework.stereotype.Service
//
//@Service
//class MyUserDetailsService : UserDetailsService {
//
//    @Autowired
//    private lateinit var userRepo: UserRepo
//    override fun loadUserByUsername(username: String?): UserDetails {
//        val user = userRepo.findByEmail(username ?: "") ?: throw UsernameNotFoundException("User 404")
//
//        return UserPrinciple(user)
//    }
//}