//package com.nakul.user.secruity
//
//import com.nakul.user.model.UserModel
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails
//import java.util.Collections
//
//class UserPrinciple(var user:UserModel):UserDetails {
//
//    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
////        TODO("Not yet implemented")
//        return Collections.singleton(SimpleGrantedAuthority("USER"))
//    }
//
//    override fun getPassword(): String {
////        TODO("Not yet implemented")
//        return user.password
//    }
//
//    override fun getUsername(): String {
////        TODO("Not yet implemented")
//        return user.email
//    }
//
//    override fun isAccountNonExpired(): Boolean {
////        TODO("Not yet implemented")
//        return true
//    }
//
//    override fun isAccountNonLocked(): Boolean {
////        TODO("Not yet implemented")
//        return true
//    }
//
//    override fun isCredentialsNonExpired(): Boolean {
////        TODO("Not yet implemented")
//        return true
//    }
//
//    override fun isEnabled(): Boolean {
////        TODO("Not yet implemented")
//        return true
//    }
//}